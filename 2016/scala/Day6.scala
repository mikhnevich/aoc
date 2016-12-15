import scala.io.Source

object Day6 {

  case class Room(name: String, id: Int, checksum: String)

  def main(args: Array[String]): Unit = {
//    val lines = Source.fromFile("d:\\work\\aoc2016\\problems\\day6_input_test.txt").getLines().toList
    val lines = Source.fromFile("d:\\work\\aoc2016\\problems\\day6_input.txt").getLines().toList
    var map = Map[Int, Char]()
    var m = lines.flatMap { x =>
      x.zipWithIndex
    }.groupBy(_._2).map { case (a, b) => a -> b.map(_._1) }
    val z = m.map { case (a, b) =>
      val t = b.groupBy(identity).map(r => r._1 -> r._2.size).toList.sortBy(_._2)(Ordering[Int].reverse)
      a -> t.last._1
    }
    val v = z.toList.sortBy(_._1).map(_._2).mkString
    println(v)

  }
}