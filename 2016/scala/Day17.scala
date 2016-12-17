import Utils.md5

import scala.collection.mutable

object Day17 {

  import java.security.MessageDigest

  val md5 = MessageDigest.getInstance("MD5")

  val input = "dmypynyp"

  def getPwCharFast(s: String) = {
    val hash = md5.digest(s.getBytes)
    hash.map("%02x".format(_)).mkString//.dropWhile(_ == '0')
  }


  def isOpen(c: Char) = {
    c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f'
  }

  val finalState = (3, 3)

  def enq(q: mutable.Queue[(Int, Int, String)], c: (Int, Int, String), visited: mutable.Set[(Int, Int)], checkVisited: Boolean = true): Unit = {
    if (c._1 >= 0 && c._1 <= 3 && c._2 >= 0 && c._2 <= 3) {
      if (checkVisited) {
        if (!visited.contains((c._1, c._2))) {
          if (c._1 == 3 && c._2 == 3) {
            println(s"${c._3.length}, $c")
          }
          q.enqueue(c)
        }
      }
      else {
        if (c._1 == 3 && c._2 == 3) {
          println(s"${c._3.length}, $c")
        } else {
          q.enqueue(c)
        }
      }
    }
  }

  def solve1() = {

    val bfs = mutable.Queue((0, 0, ""))
    val visited = mutable.Set[(Int, Int)]()
    val finalState = (3, 3)

    while (bfs.nonEmpty) {
      val current = bfs.dequeue()
      if (!(current._1 == 3 && current._2 == 3)) {
        val path = input + current._3
        val m = getPwCharFast(path)
        if (isOpen(m(0))) {
          enq(bfs, (current._1 - 1, current._2, current._3 + "U"), visited)
        }
        if (isOpen(m(1))) {
          enq(bfs, (current._1 + 1, current._2, current._3 + "D"), visited)
        }
        if (isOpen(m(2))) {
          enq(bfs, (current._1, current._2 - 1, current._3 + "L"), visited)
        }
        if (isOpen(m(3))) {
          enq(bfs, (current._1, current._2 + 1, current._3 + "R"), visited)
        }
      }
    }
  }

  def solve2() = {

    val bfs = mutable.Queue((0, 0, ""))
    val visited = mutable.Set[(Int, Int)]()
    val finalState = (3, 3)

    while (bfs.nonEmpty) {
      val current = bfs.dequeue()
      val path = input + current._3
      val m = getPwCharFast(path)
      // RDR DUU LRD URD LDU DRD
      if (isOpen(m(0))) {
        enq(bfs, (current._1 - 1, current._2, current._3 + "U"), visited, false)
      }
      if (isOpen(m(1))) {
        enq(bfs, (current._1 + 1, current._2, current._3 + "D"), visited, false)
      }
      if (isOpen(m(2))) {
        enq(bfs, (current._1, current._2 - 1, current._3 + "L"), visited, false)
      }
      if (isOpen(m(3))) {
        enq(bfs, (current._1, current._2 + 1, current._3 + "R"), visited, false)
      }
    }
  }

  def main(args: Array[String]): Unit = {
        solve1()
    solve2()
  }

}
