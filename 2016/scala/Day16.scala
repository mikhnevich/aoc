import scala.collection.{BitSet, mutable}

object Day16 {

  def mutate(s: String): String = {
    mutate(s.toCharArray).mkString
  }

  def mutate(s: Array[Char]): Array[Char] = {
    var a = s
    var b = a.clone()
    var c = b.reverse.map(x => if (x == '1') '0' else '1')
    a ++ Array[Char]('0') ++ c
  }

  def checksum(c: Array[Char]): Array[Char] = {
    var t = c
    while (t.length % 2 == 0) {
      println(t.length)
      t = t.grouped(2).map(x => if (x(0) == x(1)) '1' else '0').toArray
    }
    t
  }

  def checksum(s: String): String = {
    var c = s.toCharArray
    while (c.length % 2 == 0) {
      var i = 0
      var t = ""
      while (i < c.length) {
        t += (if (c(i) == c(i + 1)) "1" else "0")
        i += 2
      }
      c = t.toCharArray
    }
    c.mkString
  }

  def main(args: Array[String]): Unit = {
    var s = "11110010111001001".toCharArray
//    val size = 272
    val size = 35651584
    while (s.length < size) {
      s = mutate(s)
    }
    var t = s.slice(0, size)
    println(checksum(t).mkString)
  }

}
