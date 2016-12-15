import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day13 {
  val Magic = 1364 //10
  val X = 31//7
  val Y = 39//4

  def num(x: Int, y: Int) = {
    x * x + 3 * x + 2 * x * y + y + y * y + Magic
  }

  def bitCount(n: Int): Int = {
    var i = n
    // HD, Figure 5-2
    i = i - ((i >>> 1) & 0x55555555)
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333)
    i = (i + (i >>> 4)) & 0x0f0f0f0f
    i = i + (i >>> 8)
    i = i + (i >>> 16)
    i & 0x3f
  }

  def isOpenSpace(x: Int, y: Int) = {
    val n = num(x, y)
    val count = bitCount(n)
    (count % 2) == 0
  }


  def main(args: Array[String]): Unit = {
    val q = mutable.Queue[(Int, Int, Int)]()
    var visited = mutable.Set[(Int, Int)]()
    val initial = (1, 1, 0)
    q.enqueue(initial)


    while (q.nonEmpty) {
      val (x, y, k) = q.dequeue()
      visited.add((x, y))
      if (k == 50) {
        println(s"----------------------- ${visited.size}")
      }
      val a = List((x + 1, y), (x, y + 1), (x - 1, y), (x, y - 1))
      val b = a.filter(x => x._1 >= 0 && x._2 >= 0)
      val c = b.filter(x => isOpenSpace(x._1, x._2))
      val d = c.filter(x => !visited.contains(x))
      d.foreach(x => q.enqueue((x._1, x._2, k + 1)))
    }
  }

}