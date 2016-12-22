import java.util

import scala.collection.mutable
import scala.io.Source

object Day20 {

  import java.security.MessageDigest

  val md5 = MessageDigest.getInstance("MD5")

  val input = Source.fromFile("d:\\work\\aoc\\2016\\scala\\day20_input.txt").getLines().toList

  val r = "(\\d+)\\-(\\d+)\\s*".r

  def split(in: List[String]): Unit = {
    val ranges = in map { x =>
      val s = x.split('-')
      (s(0).toLong, s(1).toLong)
    }
//    in map {
//      case r(start, end) => (start.toInt, end.toInt)
//    }
    val sorted = ranges.sortWith{case ((x1, y1), (x2, y2)) => if (x1 == x2) y1 < y2 else x1 < x2}
    var q = mutable.Stack[(Long, Long)]()
    val a = sorted.head
    val b = sorted.tail
    q.push(a)
    b.foreach{ s=>
      val x = q.pop()
      if (!overlap(x, s)) {
        println(s"No overlap: $x $s    ${q.size}")
        q.push(x)
        q.push(s)
      } else {
        q.push((x._1, s._2))
//        println(s"merging $x with $s: (${x._1}, ${s._2})")
      }
    }
    println(q.last)
    println(q.last)
  }
  def overlap(x: (Long, Long), y: (Long, Long)): Boolean = {
    x._2+1 >= y._1
  }

  def main(args: Array[String]): Unit = {
split(input)
  }
}
