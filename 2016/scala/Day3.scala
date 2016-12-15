import scala.io.Source

object Day3 {
  def main(args: Array[String]): Unit = {
    def isValid(a: Array[Int]) = (a(0) + a(1) > a(2)) && (a(0) + a(2) > a(1)) && (a(1) + a(2) > a(0))

    val lines = Source.fromFile("d:\\work\\aoc2016\\problems\\day3_input.txt").getLines().toList
    val count1 = lines.map { s =>
      s.trim.split("\\s+").map(_.toInt)
    }.count(isValid)
    println(count1)

    val count2 = lines.map { s => s.trim.split("\\s+").map(_.toInt)}
      .grouped(3).toList.flatMap(_.transpose)
      .count(x => isValid(x.toArray))
    println(count2)
  }
}