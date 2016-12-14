import scala.collection.mutable

/*

The first floor contains a thulium generator, a thulium-compatible microchip, a plutonium generator, and a strontium generator.
The second floor contains a plutonium-compatible microchip and a strontium-compatible microchip.
The third floor contains a promethium generator, a promethium-compatible microchip, a ruthenium generator, and a ruthenium-compatible microchip.
The fourth floor contains nothing relevant.

based on https://github.com/exoji2e/aoc2016/blob/master/11/A.java
*/
object Day11 {

  case class State(elevator: Int, items: Array[Int], step: Int) {
    def getStateStr: String = elevator + items.mkString("")
  }

  def sort(items: Array[Int]): Array[Int] = {

  }

  def solve(initial: Array[Int]) = {

    val finalState = List.fill(initial.length)('3').mkString
    val bfs = mutable.Queue[State](State(0, initial, 0))
    val visited = mutable.Map[String, Int]()

    while (bfs.nonEmpty && !visited.contains(finalState)) {
      val current = bfs.dequeue()
      if (current.elevator >= 0 && current.elevator <= 3) {
        sort(current.items)
        if (isValid(current)) {
          val currentStateString = current.getStateStr
          if (!visited.contains(currentStateString)) {
            visited += (currentStateString -> current.step)
            if (currentStateString != finalState) {
              for (i <- current.items.indices if current.items(i) == current.elevator) {
                current.items(i) -= 1
                bfs.enqueue(State(current.elevator - 1, current.items, current.step + 1))
                current.items(i) += 2
                bfs.enqueue(State(current.elevator + 1, current.items, current.step + 1))
                current.items(i) -= 1

                for (j <- current.items.indices if j > i && current.items(j) == current.elevator) {
                  current.items(i) -= 1
                  current.items(j) -= 1
                  bfs.enqueue(State(current.elevator - 1, current.items, current.step + 1))
                  current.items(i) += 2
                  current.items(j) += 2
                  bfs.enqueue(State(current.elevator + 1, current.items, current.step + 1))
                  bfs.enqueue(State(current.elevator + 1, current.items, current.step + 1))
                  current.items(i) -= 1
                  current.items(j) -= 1

                }
              }
            }
          }
        }
      }
    }
    println(visited(finalState))

  }

  def main(args: Array[String]): Unit = {
    solve(Array(0, 0, 1, 0, 1, 0, 2, 2, 2, 2))
  }
}
