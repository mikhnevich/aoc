import scala.collection.mutable

object Day19 {

  val input = "^..^^.^^^..^^.^...^^^^^....^.^..^^^.^.^.^^...^.^.^.^.^^.....^.^^.^.^.^.^.^.^^..^^^^^...^.....^....^."

  def nextRow(row: Array[Boolean]) = {
    val next = Array.ofDim[Boolean](row.length)
    for (i <- row.indices) {
      next(i) = isTrap(row, i)
    }
    next
  }

  def isTrap(row: Array[Boolean], col: Int): Boolean = {
    val left = if (col == 0) false else row(col - 1)
    val center = row(col)
    val right = if (col == row.length - 1) false else row(col + 1)
    (left && center && !right) || (!left && center && right) || (left && !center && !right) || (!left && !center && right)
  }


  def solve(input: String, rowCount: Int) = {
    var row = input.toCharArray.map(_ == '^')
    var cnt = row.count(!_)
    for (i <- 0 until rowCount - 1) {
      row = nextRow(row)
      cnt += row.count(!_)
    }
    cnt
  }

  def main(args: Array[String]): Unit = {
    Utils.time(println(solve(input, 40)))
    Utils.time(println(solve(input, 400000)))
    // 2016
    // 19998750
  }


}
