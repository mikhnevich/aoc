import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Day19 {
// 5 - 2
  // 6 - 1
  // 7 - 3
  // 8 - 5
  // 9 - 3


//  val N = 9
  val N = 3018458
  val elfs = List.range(1, N + 1).to[ListBuffer]


  def main(args: Array[String]): Unit = {
    var i = 0
    var lastOne = false
    while (elfs.size > 1) {
      val a = find2(elfs, i)
//      println(s"removing ${elfs(a)}")
      elfs.remove(a)
      i = (i+1) % elfs.length
    }
    println(elfs)
  }

  def find2(a: ListBuffer[Int], starting: Int): Int = {
    var i = starting
    i = (i + (a.length-1) / 2) % (a.length)
    i
  }

  def find(a: ListBuffer[Boolean], starting: Int): Int = {
    var i = starting + 1
    var found = false
    var foundIndex = starting
    while (i < starting + N && !found) {
      val k = i % N
      if (a(k)) {
        found = true
        foundIndex = k
      }
      i += 1
    }
    foundIndex
  }


}
