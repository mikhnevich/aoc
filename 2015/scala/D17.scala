/*
The elves bought too much eggnog again - 150 liters this time. To fit it all into your refrigerator, you'll need to move it into smaller containers. You take an inventory of the capacities of the available containers.

For example, suppose you have containers of size 20, 15, 10, 5, and 5 liters. If you need to store 25 liters, there are four ways to do it:

15 and 10
20 and 5 (the first 5)
20 and 5 (the second 5)
15, 5, and 5
Filling all containers entirely, how many different combinations of containers can exactly fit all 150 liters of eggnog?

Puzzle input: 11,30,47,31,32,36,3,1,5,3,32,36,15,11,46,26,28,1,19,3

Your puzzle answer was 4372.

--- Part Two ---

While playing with all the containers in the kitchen, another load of eggnog arrives! The shipping and receiving department is requesting as many containers as you can spare.

Find the minimum number of containers that can exactly fit all 150 liters of eggnog. How many different ways can you fill that number of containers and still hold exactly 150 litres?

In the example above, the minimum number of containers was two. There were three ways to use that many containers, and so the answer there would be 3.

Your puzzle answer was 4.
 */

object D17 {
  def main(args: Array[String]) {
    val k = combinations2(150, List(), List(11, 30, 47, 31, 32, 36, 3, 1, 5, 3, 32, 36, 15, 11, 46, 26, 28, 1, 19, 3))
    println(k.size)
  }

  def combinations(n: Int, current: List[Int], available: List[Int]): Seq[List[Int]] = {
    if (n == 0) Seq(current)
    else if (n < 0 || available.isEmpty) Nil
    else {
      available.indices.flatMap { i =>
        combinations(n - available(i), current ++ List(available(i)), available.drop(i + 1))
      }
    }
  }

  def combinations2(n: Int, current: List[Int], available: List[Int]): Seq[List[Int]] = {
    if (n == 0) Seq(current)
    else if (n < 0 || available.isEmpty) Nil
    else {
      available.tails.flatMap { t =>
        if (t.nonEmpty) combinations(n - t.head, current ++ List(t.head), t.tail) else Nil
      }.toSeq
    }
  }
}
