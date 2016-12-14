

object Day5 {
  import java.security.MessageDigest
  val md5 = MessageDigest.getInstance("MD5")

  val input = "wtnhxymk"

  def getPwCharFast(s: String): Option[Char] = {
    val hash = md5.digest(s.getBytes)
    if (hash(0) == 0
      && hash(1) == 0
      && (hash(2) & 0xF0) == 0
    ) Some(hash.map("%02x".format(_)).mkString.charAt(5)) else None
  }

  def getPwCharSlow(s: String): Option[Char] = {
    val hash = md5.digest(s.getBytes).map("%02x".format(_)).mkString
    if (hash.startsWith("00000")) {
      Some(hash.charAt(5))
    } else None
  }

    def solve(input: String, getPwChar: String => Option[Char]): Unit = {

    val ans = Iterator.from(1)
      .flatMap {n => getPwChar(input + n.toString)}
      .take(8)

    println(ans.mkString)
  }

  def main(args: Array[String]): Unit = {
    // prewarm
    solve(input, getPwCharFast)
    solve(input, getPwCharSlow)

    var start = System.currentTimeMillis()
    solve(input, getPwCharSlow)
    var end = System.currentTimeMillis()
    var diff = (end - start)/1000
    println(diff)

    start = System.currentTimeMillis()
    solve(input, getPwCharFast)
    end = System.currentTimeMillis()
    diff = (end - start)/1000
    println(diff)
  }
}
