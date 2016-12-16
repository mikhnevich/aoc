/**
  * Created by user on 12/14/2015.
  */
object D15 {


  case class Ingridient(name: String, capacity: Int, durability: Int, flavor: Int, texture: Int, calories: Int)

  def main(args: Array[String]) {
    //    val t = parse(input)
//    val ings = List(Ingridient("Butterscotch", -1, -2, 6, 3, 8), Ingridient("Sugar", 2, 3, -2, -1, 3))
    val ings = List(Ingridient("Frosting", 4, -2, 0, 0, 5), Ingridient("Candy", 0, 5, -1, 0, 8), Ingridient("Butterscotch", -1, 0, 5, 0, 6), Ingridient("Sugar", 0, 0, -2, 2, 1))
    var max = 0
    var max1 = 0
    var max2 = 0
    for (a <- recipies(100)) {
//      val t = score(List((ings(0), a._1), (ings(1), a._2)))
      val t = score(List((ings(0), a._1), (ings(1), a._2), (ings(2), a._3), (ings(3), a._4)))
      if (max < t) {
        max = t
//        max1 = a._1
//        max2 = a._2
      }
    }
    println(s"$max $max1 $max2")
  }

/*

  def recipies(n: Int): List[(Int, Int)] = {
    var result = List[(Int, Int)]()
    for (i <- 1 to n) {
          result = (i, n - i) :: result
    }
    result
  }
*/

  def recipies(n: Int): List[(Int, Int, Int, Int)] = {
    var result = List[(Int, Int, Int, Int)]()
    for (i <- 1 to n) {
      for (j <- 1 to (n - i)) {
        for (k <- 1 to (n - i - j)) {
          result = (i, j, k, n - i - j - k) :: result
        }
      }
    }
    result
  }

  def score(xs: List[(Ingridient, Int)]): Int = {
    var capacity = xs.filter(_._2 > 0).map(x => x._1.capacity * x._2).sum
    if (capacity < 0) capacity = 0
    var durability = xs.filter(_._2 > 0).map(x => x._1.durability * x._2).sum
    if (durability < 0) durability = 0
    var flavor = xs.filter(_._2 > 0).map(x => x._1.flavor * x._2).sum
    if (flavor < 0) flavor = 0
    var texture = xs.filter(_._2 > 0).map(x => x._1.texture * x._2).sum
    if (texture < 0) texture = 0
    var calories = xs.filter(_._2 > 0).map(x => x._1.calories * x._2).sum
    if (calories == 500)    capacity * durability * flavor * texture else 0
  }

  /*
    def parse(s: String): List[Reindeer] = {
      var result = List[Reindeer]()
      s.split("\n").foreach { t =>
      }
      result
    }
  */

  val input =
    """"""
}
