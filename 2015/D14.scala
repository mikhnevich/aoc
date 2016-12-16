/**
  * Created by user on 12/14/2015.
  */
object D14 {


  case class Reindeer(name: String, speed: Int, flytime: Int, restTime: Int)

  def main(args: Array[String]) {
    val t = parse(input)
    println(score(t, 2503))
//    println(t.map(distance(_, 2503)))
  }

  val sec = 2503

  def distance(a: Reindeer, seconds: Int) = {
    var i = seconds
    var d = 0
    while (i > 0) {
      if (i >= a.flytime) {
        i = i - a.flytime
        d = d + a.flytime*a.speed
      } else {
        d = d + i*a.speed
        i = 0
      }
      i = i - a.restTime
    }
    d
  }

  def score(d: List[Reindeer], seconds: Int) = {
    val result = scala.collection.mutable.Map[Reindeer, Int]()
    d.foreach { x => result.put(x, 0)}
    for (i <- 1 to seconds) {
      val x = d.map(x => (x, distance(x, i)))
      val max = x.map(_._2).max
      for (elem <- x) {
        if (elem._2 == max) {
          result.put(elem._1, result.getOrElse(elem._1, 0) + 1)
        }
      }
    }
    result
  }

  def parse(s: String): List[Reindeer] = {
    var result = List[Reindeer]()
    s.split("\n").foreach { t =>
      val parts = t.trim.split(' ')
      result = result ++ List(Reindeer(parts(0), parts(3).toInt, parts(6).toInt, parts(13).toInt))
    }
    result
  }

  val input =
    """Vixen can fly 19 km/s for 7 seconds, but then must rest for 124 seconds.
                Rudolph can fly 3 km/s for 15 seconds, but then must rest for 28 seconds.
                Donner can fly 19 km/s for 9 seconds, but then must rest for 164 seconds.
                Blitzen can fly 19 km/s for 9 seconds, but then must rest for 158 seconds.
                Comet can fly 13 km/s for 7 seconds, but then must rest for 82 seconds.
                Cupid can fly 25 km/s for 6 seconds, but then must rest for 145 seconds.
                Dasher can fly 14 km/s for 3 seconds, but then must rest for 38 seconds.
                Dancer can fly 3 km/s for 16 seconds, but then must rest for 37 seconds.
                Prancer can fly 25 km/s for 6 seconds, but then must rest for 143 seconds."""
}
