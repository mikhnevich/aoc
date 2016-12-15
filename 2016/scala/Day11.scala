import Day11.State

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

  def sort(items: Array[Int]) = {
    val pairs = for (i <- 0 until items.length / 2) yield {
      (items(2 * i), items(2 * i + 1))
    }
    val sorted = pairs.sortWith((x, y) => (if (x._1 != y._1) x._1 - y._1 else x._2 - y._2) < 0)
    for (i <- sorted.indices) {
      items(2 * i) = sorted(i)._1
      items(2 * i + 1) = sorted(i)._2
    }
  }

  def isValid(items: Array[Int]) = {
    var fried = false
    for (i <- 0 until items.length by 2) {
      if (items(i) != items(i + 1)) // chip and generator are on the different floors
        for (j <- 1 until items.length by 2) {
          if (items(j) == items(i)) fried |= true
        }
    }
    !fried
  }

  def solve(initial: Array[Int]) = {

    val finalState = List.fill(initial.length+1)('3').mkString
    val bfs = mutable.Queue[State](State(0, initial, 0))
    val visited = mutable.Map[String, Int]()

    while (bfs.nonEmpty && !visited.contains(finalState)) {
      val current = bfs.dequeue()
      sort(current.items)
      val currentStateString = current.getStateStr
      if (!visited.contains(currentStateString)) {
        visited += (currentStateString -> current.step)
        if (currentStateString != finalState) {
          for (i <- current.items.indices if current.items(i) == current.elevator) {
            move(current, 1, Seq(i), bfs)
            move(current, -1, Seq(i), bfs)

            for (j <- i +1 until current.items.length if current.items(j) == current.elevator) {
              move(current, 1, Seq(i, j), bfs)
              move(current, -1, Seq(i, j), bfs)
            }
          }
        }
      }
    }
    println(visited(finalState))
  }

  def move(s: State, direction: Int, items: Seq[Int], bfs: mutable.Queue[State]): Unit = {
    val e = s.elevator + direction
    if (e >= 0 && e <= 3) {
      val newItems = s.items.clone
      items.foreach(i => newItems(i) += direction)
      if (isValid(newItems)) {
        bfs.enqueue(State(e, newItems, s.step + 1))
      }
    }
  }

  def main(args: Array[String]): Unit = {
    // the most important thing - microchip/generators are interchangable. Doesn't matter whether it G1/M1 pair on
    // 1st floor or it's G2/M2
    solve(Array(0, 0, 1, 0, 1, 0, 2, 2, 2, 2))
    solve(Array(0, 0, 1, 0, 1, 0, 2, 2, 2, 2, 0, 0, 0, 0))
  }
}