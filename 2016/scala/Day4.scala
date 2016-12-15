import scala.io.Source

object Day4 {
  case class Room(name: String, id: Int, checksum: String)

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("d:\\work\\aoc2016\\problems\\day4_input.txt").getLines().toList
    val pattern = "([a-z|\\-]+)(\\d+)\\[(\\w+)\\]".r
    val rooms = lines.map { case pattern(name, room, checksum) => Room(name, room.toInt, checksum) }
    val realRooms = rooms.filter(isValid)
    val sum = realRooms.map(_.id).sum
    println(sum)
    val decrypted = realRooms.map { x =>
      val decrypted = x.name.map { c => if (c == '-') ' ' else rotate(c, x.id) }
      x.copy(name = decrypted)
    }

    println(decrypted.find(_.name.startsWith("north")))
  }

  def rotate(c: Char, by: Int): Char = {
    val a = by % 26
    if ((c + a).toChar > 'z') (c + a - 'z' + 'a' - 1).toChar else (c + a).toChar
  }

  def isValid(room: Room): Boolean = {
    val chars = room.name.replace("-", "")
    val byFreq = chars.groupBy(identity).mapValues(_.length).toList.sortBy(x => (-x._2, x._1))
    val frequent = byFreq.take(room.checksum.length).map(_._1).toSet
    room.checksum.toSet == frequent
  }

}