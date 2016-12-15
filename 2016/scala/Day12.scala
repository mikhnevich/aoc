import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.matching.Regex

object Day12 {

  def solve1(instr: List[String]) = {
    var reg = Map[String, Int]("a" -> 0, "b" -> 0, "c" -> 1, "d" -> 0)

    val Cpy = "cpy (\\-?\\d+) ([abcd])".r
    val Cpy2 = "cpy ([abcd]) ([abcd])".r
    val Inc = "inc ([abcd])".r
    val Dec = "dec ([abcd])".r
    val Jnz = "jnz (\\-?\\d+) (\\d+)".r
    val Jnz2 = "jnz ([abcd]) (\\-?\\d+)".r

    var p = 0
    while(p < instr.size) {
      val s = instr(p)
      s match {
        case Cpy(x, y) =>
          reg += (y -> x.toInt)
          p += 1
        case Cpy2(x, y) => reg += (y -> reg(x))
          p += 1
        case Inc(x) => reg += (x -> (reg(x) + 1))
          p += 1
        case Dec(x) => reg += (x -> (reg(x) - 1))
          p += 1
        case Jnz(x, y) =>
          if (x.toInt != 0) {
            p += y.toInt
          } else {
            p += 1
          }
        case Jnz2(x, y) =>
          if (reg(x) != 0) {
            p += y.toInt
          } else {
            p += 1
          }
      }
    }
    println(reg)
  }

  def main(args: Array[String]): Unit = {
//    val lines = Source.fromFile("problems/day12_test.txt").getLines().toList
    val lines = Source.fromFile("problems/day12.txt").getLines().toList
    solve1(lines)
    //    solve2(lines)
  }


}