import java.util

import scala.collection.mutable
import scala.io.Source

/*
--- Day 20: Firewall Rules ---

You'd like to set up a small hidden computer here so you can use it to get back into the network later. However, the corporate firewall only allows communication with certain external IP addresses.

You've retrieved the list of blocked IPs from the firewall, but the list seems to be messy and poorly maintained, and it's not clear which IPs are allowed. Also, rather than being written in dot-decimal notation, they are written as plain 32-bit integers, which can have any value from 0 through 4294967295, inclusive.

For example, suppose only the values 0 through 9 were valid, and that you retrieved the following blacklist:

5-8
0-2
4-7
The blacklist specifies ranges of IPs (inclusive of both the start and end value) that are not allowed. Then, the only IPs that this firewall allows are 3 and 9, since those are the only numbers not in any range.

Given the list of blocked IPs you retrieved from the firewall (your puzzle input), what is the lowest-valued IP that is not blocked?

Your puzzle answer was 31053880.

--- Part Two ---

How many IPs are allowed by the blacklist?

Your puzzle answer was 117.

Both parts of this puzzle are complete! They provide two gold stars: **

At this point, you should return to your advent calendar and try another puzzle.

If you still want to see it, you can get your puzzle input.
 */
object Day20 {
  val input = Source.fromFile("d:/work/aoc/scala/src/day20.txt").getLines().toList

  def parse(in: List[String]): List[(Long, Long)] = {
    in map { x =>
      val s = x.split('-')
      (s(0).toLong, s(1).toLong)
    }
  }

  def solve(in: List[(Long, Long)]): (Long, Long) = {
    val sorted = in.sortWith { case ((x1, y1), (x2, y2)) => if (x1 == x2) y1 < y2 else x1 < x2 }
    var q = mutable.Stack[(Long, Long)]()
    val a = sorted.head
    val b = sorted.tail
    q.push(a)
    b.foreach { s =>
      val x = q.pop()
      if (!overlap(x, s)) {
        q.push(x)
        q.push(s)
      } else {
        val merged = (x._1, Math.max(s._2, x._2))
        q.push(merged)
      }
    }
    val minAllowed = q.last._2 + 1
    var totalAllowed = 0L
    var high = 4294967296L
    while (q.nonEmpty) {
      val x = q.pop()
      totalAllowed += high - x._2 - 1
      high = x._1
    }
    (minAllowed, totalAllowed)
  }

  def overlap(x: (Long, Long), y: (Long, Long)): Boolean = {
    val first = if (x._1 < y._1) x else y
    val second = if (x._1 < y._1) y else x
    // so now x._1 <= y._1
    x._2 + 1 >= y._1
  }

  def main(args: Array[String]): Unit = {
    println(solve(parse(input)))
  }
}
