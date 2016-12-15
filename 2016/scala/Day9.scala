import scala.io.Source

object Day9 {

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("d:\\work\\aoc2016\\problems\\day9.txt").getLines().toList.head
    solve1(lines)
    solve2(lines)
  }

  def solve2(s: String) = {
    println(decompress(s))
  }

  def decompress(s: String): Long = {
    var len = 0L
    var i = 0
    while (i < s.length) {
      if (s.charAt(i) == '(') {
        var j = i + 1
        while (s.charAt(j) != ')') j += 1
        val cmd = s.substring(i + 1, j)
        var parts = cmd.split("x")
        var lenToRepeat = parts(0).toInt
        val count = parts(1).toInt
        val s2 = s.substring(j + 1, j + lenToRepeat + 1)
        len += count * decompress(s2)
        i = j + lenToRepeat + 1
      } else {
        len += 1
        i += 1
      }
    }
    len
  }

  def solve1(s: String) = {
    var i = 0
    var len = 0
    while (i < s.length) {
      if (s.charAt(i) == '(') {
        var j = i + 1
        while (s.charAt(j) != ')') j += 1
        val cmd = s.substring(i + 1, j)
        var parts = cmd.split("x")
        var lenToRepeat = parts(0).toInt
        val count = parts(1).toInt
        len += lenToRepeat * count
        i = j + lenToRepeat + 1
      } else {
        len += 1
        i += 1
      }
    }
    println(len)
  }

}