
import scala.collection.mutable
import scala.io.Source

object Day21 {

//  val input = Source.fromFile("d:\\work\\aoc\\2016\\scala\\day21_test.txt").getLines().toList
  val input = Source.fromFile("d:\\work\\aoc\\2016\\scala\\day21_input.txt").getLines().toList

//  var S = "abcde".toCharArray
  var S = "abcdefgh".toCharArray
  var S2 = "fbgdceah".toCharArray

  val swapLetters = "swap letter (.) with letter (.)".r
  val swapPosition = "swap position (\\d+) with position (\\d+)".r
  val reversePositions = "reverse positions (\\d+) through (\\d+)".r
  val rotateLeft = "rotate left (\\d+) steps?".r
  val rotateRight = "rotate right (\\d+) steps?".r
  val movePosition = "move position (\\d+) to position (\\d+)".r
  val rotateBasedOnLetter = "rotate based on position of letter (.)".r


  def scramble(in: List[String]): Unit = {
    in.foreach { m =>
      m match {
        case swapLetters(a, b) =>
          S = S.map(x => if (x == a.charAt(0)) '|' else x).map(x => if (x == b.charAt(0)) a.charAt(0) else x).map(x => if (x == '|') b.charAt(0) else x)
          println(s"$m: ${S.mkString}")

        case swapPosition(a, b) =>
          val x = S.charAt(a.toInt)
          val y = S.charAt(b.toInt)
          S(b.toInt) = x
          S(a.toInt) = y
          println(s"$m: ${S.mkString}")

        case reversePositions(a, b) =>
          val start = a.toInt
          val realEnd = b.toInt
          val end = start + (b.toInt - start) / 2
          for (i <- start to end) {
            val i1 = start + realEnd - i
            val i2 = i
            val x = S(i1)
            S(i1) = S(i2)
            S(i2) = x
          }
          println(s"$m: ${S.mkString}")

        case rotateLeft(a) =>
          for (i <- 0 until a.toInt) {
            val x0 = S(0)
            for (j <- 0 until S.length - 1) {
              S(j) = S(j + 1)
            }
            S(S.length - 1) = x0
          }
          println(s"$m: ${S.mkString}")

        case rotateRight(a) =>
          rRight(a.toInt)
          println(S.mkString)

        case movePosition(a, b) =>
          if (a.toInt < b.toInt) {
            val x = S(a.toInt)
            val y = S(b.toInt)
            for (i <- a.toInt until b.toInt) {
              S(i) = S(i + 1)
            }
            S(b.toInt) = x
          } else if (a.toInt > b.toInt) {
            val x = S(a.toInt)
            val y = S(b.toInt)
            for (i <- (b.toInt + 1 to a.toInt).reverse) {
              S(i) = S(i - 1)
            }
            S(b.toInt) = x
          }
          println(s"$m: ${S.mkString}")

        case rotateBasedOnLetter(a) =>
          val i = S.indexOf(a.charAt(0))
          val rotateBy = (if (i >= 4) 1 else 0) + i + 1
          rRight(rotateBy)
          println(s"$m: ${S.mkString}")
      }
    }
    println(s"${S.mkString}")
  }

  def unscramble(in: List[String]): Unit = {
    in.foreach { m =>
      m match {
        case swapLetters(a, b) =>
          S = S.map(x => if (x == a.charAt(0)) '|' else x).map(x => if (x == b.charAt(0)) a.charAt(0) else x).map(x => if (x == '|') b.charAt(0) else x)
          println(s"$m: ${S.mkString}")

        case swapPosition(a, b) =>
          val x = S.charAt(a.toInt)
          val y = S.charAt(b.toInt)
          S(b.toInt) = x
          S(a.toInt) = y
          println(s"$m: ${S.mkString}")

        case reversePositions(a, b) =>
          val start = a.toInt
          val realEnd = b.toInt
          val end = start + (b.toInt - start) / 2
          for (i <- start to end) {
            val i1 = start + realEnd - i
            val i2 = i
            val x = S(i1)
            S(i1) = S(i2)
            S(i2) = x
          }
          println(s"$m: ${S.mkString}")

        case rotateLeft(a) =>
          rRight(a.toInt)
          println(s"$m: ${S.mkString}")

        case rotateRight(a) =>
          rLeft(a.toInt)
          println(S.mkString)

        case movePosition(a, b) =>
          if (a.toInt < b.toInt) {
            val x = S(b.toInt)
            val y = S(a.toInt)
            for (i <- (b.toInt + 1 to a.toInt).reverse) {
              S(i) = S(i - 1)
            }
            S(b.toInt) = x
          } else if (a.toInt > b.toInt) {
            val x = S(b.toInt)
            val y = S(a.toInt)
            for (i <- a.toInt until b.toInt) {
              S(i) = S(i + 1)
            }
            S(b.toInt) = x
          }
          println(s"$m: ${S.mkString}")

        case rotateBasedOnLetter(a) =>
          val i = S.indexOf(a.charAt(0))
          val rotateBy = (if (i >= 4) 1 else 0) + i + 1
          rRight(rotateBy)
          println(s"$m: ${S.mkString}")
      }
    }
    println(s"${S.mkString}")
  }

  private def rLeft(a: Int) = {
    for (i <- 0 until a) {
      val x0 = S(0)
      for (j <- 0 until S.length - 1) {
        S(j) = S(j + 1)
      }
      S(S.length - 1) = x0
    }
  }

  private def rRight(a: Int) = {
    for (i <- 0 until a) {
      val x0 = S.last
      for (j <- (1 until S.length).reverse) {
        S(j) = S(j - 1)
      }
      S(0) = x0
    }
  }

  def main(args: Array[String]): Unit = {
    unscramble(input.reverse)
  }
}
