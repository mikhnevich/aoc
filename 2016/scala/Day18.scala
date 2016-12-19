import scala.collection.mutable

object Day18 {

  import java.security.MessageDigest

  val md5 = MessageDigest.getInstance("MD5")

//  val input = ".^^.^.^^^"
  val input = "^..^^.^^^..^^.^...^^^^^....^.^..^^^.^.^.^^...^.^.^.^.^^.....^.^^.^.^.^.^.^.^^..^^^^^...^.....^....^."
  val TRAPS = input.toCharArray.map(_ == '^')
  val LEN = input.length

  def solve2(s: String) = {
    val a = s.toCharArray.map(_ == '.')

  }

  var CACHE = mutable.Map[(Int, Int), Boolean]()

  def isTrap(row: Int, col: Int): Boolean = {
    if (CACHE.contains((row, col))) {
      CACHE((row, col))
    } else if (col < 0 || col >= LEN) false
    else if (row == 0) {
      TRAPS(col)
    } else {
      val pl = isTrap(row - 1, col - 1)
      val pc = isTrap(row - 1, col)
      val pr = isTrap(row - 1, col + 1)
      val a = (pl && pc && !pr) || (!pl && pc && pr) || (pl && !pc && !pr) || (!pl && !pc && pr)
      CACHE += ((row, col) -> a)
      a
    }
  }

  def main(args: Array[String]): Unit = {
    var cnt = 0L
    for (i <- 0 until 400000) {
      for (j <- 0 until LEN) {
        val a = isTrap(i, j)
//        print(if (a) '^' else '.')
        if (!a)  cnt += 1
      }
//      println
    }
      println(cnt)
  }

}
