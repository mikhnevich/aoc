import Utils._

import scala.annotation.tailrec

object Day14 {


  def stretchedHash(hash: String): String = Iterator.iterate(hash)(md5).drop(2017).next

  def firstTriple(hash: String): Option[Char] = hash.toSeq.sliding(3).map(_.toSet).find(_.size == 1).map(_.head)

  def containsFive(hash: String, c: Char) = hash.contains(List.fill(5)(c).mkString)

  def headIsKey(s: Stream[String]): Boolean =
    firstTriple(s.head).exists(c => s.tail.take(1000).exists(containsFive(_, c)))

  @tailrec
  def findKey(s: Stream[String], index: Int, keysLeft: Int): Int = {
    val isKey: Boolean = headIsKey(s)
    if (isKey && keysLeft == 1) index
    else findKey(s.tail, index + 1, if (isKey) keysLeft - 1 else keysLeft)
  }

  def main(args: Array[String]): Unit = {
    measure(println(findKey(Stream.from(0).map("jlmsuwbz" + _).map(md5), 0, 64)))
    measure(println(findKey(Stream.from(0).map("jlmsuwbz" + _).map(stretchedHash), 0, 64)))
  }

}
