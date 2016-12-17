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

  def solve(disks: Array[Disk]) = {
    var i = 0
    while (!valid(i, disks)) {
      i += 1
    }
    i
  }

  def valid(t: Int, disks: Array[Disk]): Boolean = {
    var i = 0
    var bounce = false
    while (i < disks.length && !bounce) {
      val d = disks(i)
      val current = d.position + t + i + 1
      if (current % d.size != 0) bounce = true
      i += 1
    }
    !bounce
  }

  def main(args: Array[String]): Unit = {
    Utils.measure(println(solve(Array(Disk(17, 5), Disk(19, 8), Disk(7, 1), Disk(13, 7), Disk(5, 1), Disk(3, 0)))))
    Utils.measure(println(solve(Array(Disk(17, 5), Disk(19, 8), Disk(7, 1), Disk(13, 7), Disk(5, 1), Disk(3, 0), Disk(11, 0)))))
    // Upping the Ante
    Utils.measure(println(solve(Array(
      Disk(43, 2),
      Disk(53, 7),
      Disk(61, 10),
      Disk(37, 2),
      Disk(127, 9)
    ))))
    
    // stuck here
    // https://www.reddit.com/r/adventofcode/comments/5ifvyc/2016_day_15_part_3_our_discs_got_larger/
    // https://en.wikipedia.org/wiki/Chinese_remainder_theorem#Search_by_sieving
    Utils.measure(println(solve(Array(
      Disk(101, 2),
      Disk(163, 7),
      Disk(263, 10),
      Disk(293, 2),
      Disk(373, 9),
      Disk(499, 0),
      Disk(577, 0)
    ))))

  }
}
