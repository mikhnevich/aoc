import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day10 {

  //  val V = (2, 5)
  val V = (17, 61)

  var bots = Map[Int, Bot]()
  var output = Map[Int, Int]()

  def addOutput(o: Int, value: Int): Unit = {
    output += (o -> value)
    if (output.contains(0) && output.contains(1) && output.contains(2)) {
      println(s"----------------------${output(0)*output(1)*output(2)}")
    }
  }

  case class Bot(id: Int) {
    val values = ListBuffer[Int]()
    var lowBot: Int = 0
    var highBot: Int = 0
    var lowOut: Int = -1
    var highOut: Int = -1

    def lowValue = values.min

    def highValue = values.max

    def addValue(v: Int): Unit = {
      values += v
      if (values.length == 2 && values.contains(V._1) && values.contains(V._2)) {
        println(s"----------------------- $id")
      }
      if (values.length == 2) {
        val lb = bots(this.lowBot)
        lb.addValue(lowValue)
        val hb = bots(this.highBot)
        hb.addValue(highValue)
        if (lowOut != -1) {
          addOutput(lowOut, lowValue)
        }
        if (highOut != -1) {
          addOutput(highOut, highValue)
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    //    val lines = Source.fromFile("problems/day10_test.txt").getLines().toList
    val lines = Source.fromFile("problems/day10.txt").getLines().toList
    solve1(lines)
    //    solve2(lines)
  }

  def solve1(s: List[String]) = {
    val Init = "value (\\d+) goes to bot (\\d+)".r
    val PassToBot = "bot (\\d+) gives low to bot (\\d+) and high to bot (\\d+)".r
    val Output = "bot (\\d+) gives low to output (\\d+) and high to output (\\d+)".r
    val OutputBot = "bot (\\d+) gives low to output (\\d+) and high to bot (\\d+)".r
    val initCommands = s.filter(_.startsWith("value "))
    val allBots = s.flatMap {
      case Init(v, bot) => Seq(bot.toInt)
      case PassToBot(bot, b1, b2) => Seq(bot.toInt, b1.toInt, b2.toInt)
      case Output(bot, _, _) => Seq(bot.toInt)
      case OutputBot(bot, _, b) => Seq(bot.toInt, b.toInt)
    }.toSet
    allBots.foreach(b => bots += (b -> Bot(b)))

    println(bots.keys.toList.sorted)

    def passHigh(bot: String, hb: String) = {
      val existing = bots(bot.toInt)
      val highBot = bots(hb.toInt)
      highBot.addValue(existing.highValue)
      existing.values.remove(existing.highValue)
    }

    def passLow(bot: String, lb: String) = {
      val existing = bots(bot.toInt)
      val lowBot = bots(lb.toInt)
      lowBot.addValue(existing.lowValue)
      existing.values.remove(existing.lowValue)
    }

    def initBot(v: String, bot: String) = {
      val existing = bots(bot.toInt)
      existing.addValue(v.toInt)
    }

    def outputHigh(bot: String) = {
      val existing = bots(bot.toInt)
      existing.values.remove(existing.highValue)
    }

    def outputLow(bot: String) = {
      val existing = bots(bot.toInt)
      existing.values.remove(existing.lowValue)
    }

    val instr = s.filter(!_.startsWith("value "))

    instr.foreach { s =>
      println(s)
      s match {
        case PassToBot(bot, lb, hb) =>
          bots(bot.toInt).lowBot = lb.toInt
          bots(bot.toInt).highBot = hb.toInt

        case Output(bot, lowOut, highOut) =>
          bots(bot.toInt).lowOut = lowOut.toInt
          bots(bot.toInt).highOut = highOut.toInt


        case OutputBot(bot, lowOut, hb) =>
          bots(bot.toInt).highBot = hb.toInt
          bots(bot.toInt).lowOut = lowOut.toInt
      }
    }

    initCommands.foreach {
      case Init(v, bot) =>
        initBot(v, bot)
    }

  }

}