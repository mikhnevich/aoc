import java.security.MessageDigest

object Day5_3 {
  val doorId = "ffykfhsq"

  def main(args: Array[String]): Unit = {
    runSecond()
  }

  def runFirst(): Unit = {
    val password = hashes().filter(_.startsWith("00000")).take(8).map(_.charAt(5)).mkString
    println(password)
  }

  def hashes(): Iterator[String] = {
    Iterator.iterate(("", 0)) {
      case (hash, index) =>
        val toHash = doorId + index
        val hexString = MessageDigest.getInstance("MD5").digest(toHash.getBytes).take(6).map("%02X" format _).mkString
        (hexString, index + 1)
    }.map(_._1)
  }

  def runSecond(): Unit = {
    val password = hashes()
      .filter(_.startsWith("00000"))
      .scanLeft(Array.fill[Option[Char]](8)(None)) {
        case (pw, validHash) =>
          try {
            val pos = validHash(5).asDigit
            if (pos < pw.length && pw(pos).isEmpty) {
              pw(pos) = Some(validHash(6))
            }
            pw
          } catch {
            case e: NumberFormatException =>
              //ignore
              pw
          }
      }.dropWhile(_.exists(_.isEmpty)).map(_.flatten)

    println(password.take(1).toList.map(_.mkString).head)
  }
}