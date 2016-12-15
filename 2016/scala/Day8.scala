import scala.io.Source

object Day8 {

  class Command

  case class Rect(width: Int, height: Int) extends Command

  case class RotateRow(row: Int, by: Int) extends Command

  case class RotateColumn(column: Int, by: Int) extends Command

  val W = 50
  val H = 6
  val rrow = "rotate row y=(\\d+) by (\\d+)".r
  val rcol = "rotate column x=(\\d+) by (\\d+)".r
  val rect = "rect (\\d+)x(\\d+)".r
  val screen = Array.ofDim[Int](H, W)

  def main(args: Array[String]): Unit = {
//    val lines = Source.fromFile("d:\\work\\aoc2016\\problems\\day8_input_test.txt").getLines().toList
    //    val lines = Source.fromFile("d:\\work\\aoc2016\\problems\\day8_input_test2.txt").getLines().toList

        val lines = Source.fromFile("d:\\work\\aoc2016\\problems\\day8_input.txt").getLines().toList
    val commands = lines.map {
      case rrow(row, by) => RotateRow(row.toInt, by.toInt)
      case rcol(col, by) => RotateColumn(col.toInt, by.toInt)
      case rect(w, h) => Rect(w.toInt, h.toInt)
    }
    printScreen
    commands.foreach {
      case Rect(w, h) =>
        for (i <- 0 until h; j <- 0 until w) {
          screen(i)(j) = 1
        }
        printScreen
      case RotateRow(row, by) =>
        for (i <- 0 until by) {
          val a = screen(row)(W - 1)
          for (j <- W - 1 until 0 by -1) {
            screen(row)(j) = screen(row)(j - 1)
          }
          screen(row)(0) = a
        }
        printScreen
      case RotateColumn(col, by) =>
        for (i <- 0 until by) {
          val a = screen(H - 1)(col)
          for (j <- H - 1 until 0 by -1) {
            screen(j)(col) = screen(j - 1)(col)
          }
          screen(0)(col) = a
        }
        printScreen
    }
    var lit = 0
    for (i <- 0 to H - 1)
      for (j <- 0 to W - 1)
        lit += (if (screen(i)(j) == 1) 1 else 0)

    println(lit)
  }

  def printScreen = {
    println("------------------------")
    for (i <- 0 to H - 1) {
      for (j <- 0 to W - 1) {
        if (j % 5 == 0) print("  ")
        (if (screen(i)(j) == 1) print("#") else print(" "))
      }
      println()
    }
  }
}