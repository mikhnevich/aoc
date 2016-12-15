import javax.xml.bind.DatatypeConverter.printHexBinary

import scala.annotation.tailrec
import Utils._
object Day15 {
/*
Disc #1 has 17 positions; at time=0, it is at position 5.
Disc #2 has 19 positions; at time=0, it is at position 8.
Disc #3 has 7 positions; at time=0, it is at position 1.
Disc #4 has 13 positions; at time=0, it is at position 7.
Disc #5 has 5 positions; at time=0, it is at position 1.
Disc #6 has 3 positions; at time=0, it is at position 0.
 */
  case class Disk(size: Int, position: Int)


var disks = Array(Disk(17,5), Disk(19, 8), Disk(7, 1), Disk(13, 7), Disk(5, 1), Disk(3, 0), Disk(11, 0))
//var disks = Array(Disk(17,5), Disk(19, 8), Disk(7, 1), Disk(13, 7), Disk(5, 1), Disk(3, 0))
//  var disks = Array(Disk(5, 4), Disk(2, 1))

  def valid(t: Int): Boolean = {
    var i = 0
    var bounce = false
    while (i < disks.length && !bounce) {
      val d = disks(i)
      val current = d.position + t+i + 1
      if (current % d.size != 0) bounce = true
      i += 1
    }
    !bounce
  }

  def main(args: Array[String]): Unit = {
    var i = 0
    while (!valid(i)) {
      i += 1
    }
    println(i)
  }

}
