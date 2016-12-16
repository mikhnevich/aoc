import scala.io.Source

object D25 {
  // To continue, please consult the code grid in the manual.  Enter the code at row 2947, column 3029.

  val input = Array.ofDim[BigInt](7, 7)
  input(0) = Array(0, 0, 0, 0, 0, 0, 0, 0)
  input(1) = Array(0, 20151125, 18749137, 17289845, 30943339, 10071777, 33511524)
  input(2) = Array(0, 31916031, 21629792, 16929656, 7726640, 15514188, 4041754)
  input(3) = Array(0, 16080970, 8057251, 1601130, 7981243, 11661866, 16474243)
  input(4) = Array(0, 24592653, 32451966, 21345942, 9380097, 10600672, 31527494)
  input(5) = Array(0, 77061, 17552253, 28094349, 6899651, 9250759, 31663883)
  input(6) = Array(0, 33071741, 6796745, 25397450, 24659492, 1534922, 27995004)

  val codes = scala.collection.mutable.Map[(Int, Int), BigInt]()

  val M = 252533
  val R = 33554393



  def getN(row: Int, col: Int) = {
    var n = 0
    val k = row + col - 1
    for (i <- 1 until k) n = n + i
    val num = n + col
    var start = 20151125L
    for (i <- 1 until num) start = (start * M) % R
    start
  }

  def getPrevious(row: Int, col: Int): (Int, Int) = {
    if (col == 0) (0, row - 1)
    else  (row + 1, col - 1)
  }

  def getNext(n: BigInt): BigInt = n*M%R



  def getCode(row: Int, col: Int): BigInt = {
    if (row <= 5 && col <= 5) input(row)(col)
    else if (codes.contains((row, col))) codes.get(row, col).get
    else {
      val (prevRow, prevCol) = getPrevious(row, col)
      val prevCode = getCode(prevRow, prevCol)
      codes += (prevRow, prevCol) -> prevCode
      (prevCode * M) % R
    }
  }

  def main(args: Array[String]): Unit = {
    println(getN(2947, 3029))
//    println(getCode(2946,3028))
  }


  def parse(s: String) = {
    Source.fromFile("D:\\ds\\D24_input.txt").getLines().map { x => x

    }
  }

}
