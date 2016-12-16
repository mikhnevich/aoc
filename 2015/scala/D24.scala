import scala.io.Source

object D24 {

  //  val weights = List[Int](1, 2, 3, 4, 5, 7, 9, 10, 11).reverse
  val weights = List[Int](1, 2, 3, 5, 7, 13, 17, 19, 23, 29, 31, 37, 41, 43, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113).reverse

  def main(args: Array[String]): Unit = {
    val w = weights.sum / 4
    println(s"$w")
    val firstPackage = choose(w, Integer.MAX_VALUE, List(), weights)
    val sorted = firstPackage.sortBy{f =>
      f.foldLeft(1L)((p, element) => p*element)
    }
    var i = 0
    var found = false
    while (!found && i < sorted.length) {
      val available = weights diff sorted(i)
      if (choose(w, Integer.MAX_VALUE, List(), available).nonEmpty) {
        found = true
        println(sorted(i))
        println(sorted(i).foldLeft(1L)((p, element) => p*element))
        println(sorted(i).sum)
      }
    }
  }
  // 2133972311 too low

  def choose(target: Int, minFound: Int, current: List[Int], available: List[Int]): List[List[Int]] = {
    if (current.size > minFound || current.sum > target) Nil
    else if (current.sum == target) {
//      println(s"found: $current")
      List(current)
    }
    else {
      var i = 0
      var result = List[List[Int]]()
      var minFoundResult = minFound
      while (i < available.length) {
        val elem = available(i)
        if (current.isEmpty || elem < current(0)) {
          val newAvailable = available.take(i - 1) ++ available.drop(i + 1)
          val a = choose(target, minFoundResult, available(i) :: current, newAvailable)
          if (a != Nil) {
            var sz = minFoundResult
            a.foreach(k => if (k.length < sz) sz = k.length)
            minFoundResult = sz
            result = a ++ result
          }
        }
        i = i + 1
      }
      result
    }
  }


  def f1() = {

  }


  def parse(s: String) = {
    Source.fromFile("D:\\ds\\D24_input.txt").getLines().map { x => x

    }
  }

}
