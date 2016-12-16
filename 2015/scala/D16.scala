/**
  * Created by user on 12/14/2015.
  */
object D16 {


  def main(args: Array[String]) {
    val msg = Map("children" -> 3, "cats" -> 7, "samoyeds" -> 2, "pomeranians" -> 3, "akitas" -> 0, "vizslas" -> 0, "goldfish" -> 5,
      "trees" -> 3, "cars" -> 2, "perfumes" -> 1)
    val aaa = parse(input)
    val k = aaa.filter(p => !p.contains("children") || p.get("children").get == 3).
    filter(t => !t.contains("cats") || t.get("cats").get > 7).
    filter(t => !t.contains("samoyeds") || t.get("samoyeds").get == 2).
    filter(t => !t.contains("pomeranians") || t.get("pomeranians").get < 3).
    filter(t => !t.contains("akitas") || t.get("akitas").get == 0).
    filter(t => !t.contains("vizslas") || t.get("vizslas").get == 0).
    filter(t => !t.contains("trees") || t.get("trees").get > 3).
    filter(t => !t.contains("goldfish") || t.get("goldfish").get < 5).
    filter(t => !t.contains("cars") || t.get("cars").get == 2).
    filter(t => !t.contains("perfumes") || t.get("perfumes").get == 1)
    println(k.mkString("\n"))
  }

  def bestMatch(msg: Map[String, Int], choices: List[Map[String, Int]]): Int = {
    var i = 0
    var bestDiff = Integer.MAX_VALUE
    var bestChoice = 0
    for (i <- 0 until choices.size) {
      val c = choices(i)
      var diff = 0
      for ((k,v) <- msg) {
        if (c.contains(k)) {
          diff = diff + Math.abs(c.get(k).get - v)
        }
      }
      if (diff < bestDiff) {
        bestDiff = diff
        bestChoice = i
      }
    }
    bestChoice
  }

  def parse(s: String): List[Map[String, Int]] = {
    var result = List[Map[String, Int]]()
    var k = 1
    s.split("\n").foreach { t =>
      val parts = t.trim.split(',')
      var m = Map[String, Int]()
      for (i <- parts) {
        val p2 = i.split(':')
        m = m + (p2(0).trim -> p2(1).trim.toInt)
      }
      m = m + ("num" -> k)
      k = k + 1
      result = result ++ List(m)
    }
    result
  }

  val input =
    """goldfish: 9, cars: 0, samoyeds: 9
      perfumes: 5, trees: 8, goldfish: 8
      pomeranians: 2, akitas: 1, trees: 5
      goldfish: 10, akitas: 2, perfumes: 9
      cars: 5, perfumes: 6, akitas: 9
      goldfish: 10, cats: 9, cars: 8
      trees: 2, samoyeds: 7, goldfish: 10
      cars: 8, perfumes: 6, goldfish: 1
      cats: 4, pomeranians: 0, trees: 0
      trees: 2, children: 10, samoyeds: 10
      akitas: 10, perfumes: 4, vizslas: 1
      akitas: 1, trees: 0, goldfish: 3
      perfumes: 6, goldfish: 10, cars: 8
      cats: 8, akitas: 5, vizslas: 0
      cars: 8, trees: 3, samoyeds: 5
      vizslas: 6, cats: 6, pomeranians: 10
      akitas: 6, cats: 2, perfumes: 9
      children: 9, goldfish: 2, akitas: 10
      trees: 3, perfumes: 0, goldfish: 6
      vizslas: 3, akitas: 0, trees: 1
      vizslas: 3, cars: 7, akitas: 3
      perfumes: 7, children: 1, pomeranians: 7
      trees: 10, cars: 9, akitas: 10
      akitas: 5, goldfish: 6, vizslas: 6
      samoyeds: 3, trees: 8, vizslas: 5
      vizslas: 4, pomeranians: 2, trees: 1
      cars: 9, goldfish: 2, trees: 4
      vizslas: 6, goldfish: 10, perfumes: 7
      vizslas: 6, pomeranians: 3, akitas: 6
      trees: 0, samoyeds: 5, akitas: 9
      vizslas: 1, perfumes: 0, trees: 6
      cars: 7, vizslas: 1, children: 10
      vizslas: 1, cars: 1, perfumes: 7
      vizslas: 9, trees: 10, akitas: 9
      akitas: 3, vizslas: 5, cars: 10
      cats: 3, children: 9, samoyeds: 3
      vizslas: 5, pomeranians: 7, cars: 6
      cars: 10, akitas: 5, vizslas: 8
      akitas: 5, trees: 9, children: 2
      vizslas: 0, cats: 7, akitas: 0
      cars: 9, trees: 10, perfumes: 8
      akitas: 4, trees: 2, goldfish: 3
      goldfish: 1, cats: 1, akitas: 8
      goldfish: 8, akitas: 9, vizslas: 4
      perfumes: 3, goldfish: 4, trees: 0
      trees: 7, perfumes: 1, goldfish: 8
      pomeranians: 10, cars: 7, trees: 2
      trees: 2, akitas: 1, cars: 4
      goldfish: 5, perfumes: 7, akitas: 8
      akitas: 9, vizslas: 9, trees: 2
      cars: 0, samoyeds: 0, vizslas: 8
      trees: 0, perfumes: 6, pomeranians: 4
      vizslas: 1, cats: 6, akitas: 3
      samoyeds: 8, akitas: 1, vizslas: 4
      goldfish: 10, perfumes: 2, pomeranians: 10
      trees: 9, perfumes: 3, goldfish: 5
      akitas: 3, perfumes: 0, cats: 2
      perfumes: 4, vizslas: 4, cars: 8
      goldfish: 7, children: 5, pomeranians: 8
      cars: 1, trees: 1, perfumes: 10
      trees: 4, samoyeds: 4, cars: 6
      akitas: 10, trees: 2, vizslas: 6
      goldfish: 3, perfumes: 7, vizslas: 10
      pomeranians: 5, children: 10, cars: 0
      vizslas: 10, cars: 8, perfumes: 3
      children: 5, vizslas: 4, akitas: 10
      children: 6, perfumes: 7, cars: 3
      goldfish: 8, cars: 6, children: 1
      vizslas: 5, perfumes: 3, cars: 9
      goldfish: 0, cats: 6, perfumes: 0
      trees: 2, samoyeds: 3, cars: 1
      cats: 3, akitas: 8, vizslas: 7
      akitas: 3, vizslas: 2, goldfish: 6
      pomeranians: 10, samoyeds: 9, cats: 8
      vizslas: 7, cars: 7, akitas: 10
      children: 3, cats: 6, vizslas: 3
      goldfish: 7, pomeranians: 10, trees: 0
      vizslas: 9, children: 7, trees: 10
      trees: 6, pomeranians: 8, samoyeds: 1
      vizslas: 5, children: 6, pomeranians: 5
      cars: 9, vizslas: 9, akitas: 9
      vizslas: 3, cars: 8, akitas: 1
      vizslas: 4, trees: 2, cats: 1
      children: 3, akitas: 0, vizslas: 1
      cats: 6, vizslas: 5, akitas: 2
      cars: 3, akitas: 7, goldfish: 8
      samoyeds: 8, vizslas: 3, goldfish: 8
      vizslas: 4, children: 0, cats: 7
      goldfish: 9, pomeranians: 10, samoyeds: 0
      trees: 6, akitas: 3, cars: 7
      samoyeds: 3, akitas: 7, perfumes: 10
      cars: 7, pomeranians: 10, trees: 2
      samoyeds: 1, children: 3, cars: 3
      samoyeds: 8, akitas: 7, vizslas: 0
      goldfish: 7, children: 2, cars: 6
      cars: 3, perfumes: 9, akitas: 10
      akitas: 9, cars: 10, vizslas: 10
      trees: 4, goldfish: 8, pomeranians: 7
      samoyeds: 6, pomeranians: 0, vizslas: 7
      akitas: 7, perfumes: 8, vizslas: 3
      cars: 5, perfumes: 1, trees: 0
      akitas: 6, pomeranians: 10, trees: 0
      trees: 3, perfumes: 5, cats: 9
      goldfish: 10, perfumes: 8, akitas: 0
      goldfish: 6, vizslas: 5, trees: 2
      pomeranians: 9, samoyeds: 10, perfumes: 10
      cars: 8, vizslas: 4, akitas: 2
      cats: 0, goldfish: 7, trees: 0
      cars: 3, pomeranians: 6, trees: 2
      perfumes: 4, goldfish: 5, akitas: 10
      cars: 3, perfumes: 4, pomeranians: 4
      cats: 2, goldfish: 10, akitas: 0
      cats: 10, children: 0, trees: 1
      akitas: 10, vizslas: 3, goldfish: 0
      samoyeds: 3, goldfish: 6, vizslas: 1
      cars: 3, perfumes: 5, trees: 6
      akitas: 9, samoyeds: 8, goldfish: 8
      pomeranians: 5, perfumes: 10, trees: 1
      goldfish: 6, perfumes: 3, children: 1
      trees: 1, children: 3, pomeranians: 6
      akitas: 7, cars: 10, vizslas: 9
      trees: 4, akitas: 8, samoyeds: 10
      cats: 4, cars: 8, vizslas: 9
      cars: 10, children: 1, trees: 0
      goldfish: 5, pomeranians: 5, trees: 2
      goldfish: 1, vizslas: 8, akitas: 10
      vizslas: 4, cars: 9, akitas: 1
      goldfish: 8, perfumes: 3, cars: 9
      goldfish: 9, pomeranians: 9, perfumes: 1
      trees: 1, vizslas: 9, perfumes: 3
      children: 6, trees: 8, vizslas: 8
      cars: 1, vizslas: 3, children: 7
      cars: 7, children: 1, perfumes: 6
      trees: 8, vizslas: 3, samoyeds: 2
      cats: 9, perfumes: 4, pomeranians: 7
      perfumes: 0, akitas: 8, vizslas: 6
      goldfish: 5, trees: 0, vizslas: 7
      trees: 1, perfumes: 2, cars: 10
      samoyeds: 8, goldfish: 8, trees: 0
      vizslas: 10, perfumes: 9, goldfish: 0
      perfumes: 7, cars: 9, cats: 5
      trees: 2, samoyeds: 2, cars: 0
      cars: 1, perfumes: 1, akitas: 1
      vizslas: 9, cars: 7, pomeranians: 10
      pomeranians: 2, samoyeds: 7, children: 7
      vizslas: 6, cars: 9, goldfish: 7
      trees: 2, vizslas: 1, cats: 9
      perfumes: 9, trees: 4, pomeranians: 5
      samoyeds: 8, children: 1, vizslas: 9
      cats: 3, trees: 2, vizslas: 4
      goldfish: 7, akitas: 10, trees: 3
      perfumes: 4, vizslas: 7, cars: 4
      pomeranians: 4, akitas: 0, vizslas: 3
      samoyeds: 8, trees: 2, vizslas: 10
      vizslas: 7, cats: 7, pomeranians: 5
      goldfish: 10, pomeranians: 1, vizslas: 1
      cars: 6, perfumes: 7, trees: 9
      trees: 5, samoyeds: 9, goldfish: 3
      pomeranians: 4, akitas: 6, vizslas: 8
      goldfish: 7, children: 0, cats: 0
      vizslas: 5, akitas: 0, samoyeds: 2
      akitas: 4, children: 0, vizslas: 3
      samoyeds: 2, perfumes: 0, goldfish: 9
      cars: 9, vizslas: 8, akitas: 6
      samoyeds: 9, vizslas: 9, perfumes: 5
      cars: 5, pomeranians: 4, samoyeds: 8
      cars: 10, perfumes: 3, samoyeds: 6
      pomeranians: 8, goldfish: 9, trees: 9
      vizslas: 7, akitas: 3, samoyeds: 4
      cats: 2, goldfish: 0, vizslas: 4
      perfumes: 3, goldfish: 10, cats: 3
      goldfish: 7, akitas: 6, cars: 0
      cars: 9, goldfish: 7, akitas: 5
      goldfish: 6, cats: 0, vizslas: 8
      perfumes: 7, cats: 10, cars: 10
      samoyeds: 9, vizslas: 4, pomeranians: 10
      perfumes: 0, trees: 0, cars: 10
      vizslas: 6, children: 7, samoyeds: 1
      vizslas: 8, children: 6, trees: 0
      cars: 1, vizslas: 6, trees: 1
      vizslas: 10, perfumes: 3, cars: 1
      trees: 8, samoyeds: 9, cars: 7
      cars: 6, vizslas: 2, perfumes: 7
      trees: 5, samoyeds: 9, akitas: 0
      cars: 8, goldfish: 8, trees: 4
      samoyeds: 6, goldfish: 1, trees: 2
      perfumes: 1, trees: 2, akitas: 7
      samoyeds: 5, cars: 6, perfumes: 2
      samoyeds: 8, goldfish: 3, perfumes: 5
      akitas: 2, cats: 1, samoyeds: 1
      trees: 5, akitas: 1, goldfish: 7
      vizslas: 3, trees: 0, perfumes: 4
      cars: 3, perfumes: 4, akitas: 3
      perfumes: 4, vizslas: 8, children: 4
      vizslas: 1, samoyeds: 3, cars: 6
      cars: 5, perfumes: 6, vizslas: 2
      vizslas: 8, akitas: 8, cats: 6
      cars: 9, akitas: 2, pomeranians: 7
      cats: 9, akitas: 6, cars: 10
      vizslas: 10, pomeranians: 2, goldfish: 9
      vizslas: 9, samoyeds: 4, akitas: 3
      akitas: 5, cats: 2, vizslas: 0
      perfumes: 1, children: 3, akitas: 10
      trees: 4, vizslas: 7, akitas: 9
      trees: 8, perfumes: 9, cars: 1
      goldfish: 6, trees: 5, cars: 8
      akitas: 3, vizslas: 8, trees: 8
      vizslas: 4, perfumes: 7, akitas: 10
      cars: 9, perfumes: 7, goldfish: 9
      vizslas: 2, cats: 2, akitas: 10
      akitas: 1, trees: 3, cars: 2
      goldfish: 5, trees: 0, vizslas: 7
      akitas: 3, perfumes: 1, vizslas: 5
      perfumes: 3, pomeranians: 6, cars: 0
      goldfish: 1, cats: 9, cars: 3
      goldfish: 9, pomeranians: 6, samoyeds: 0
      cars: 6, trees: 2, perfumes: 2
      vizslas: 3, goldfish: 8, akitas: 5
      cats: 9, perfumes: 7, cars: 5
      pomeranians: 5, vizslas: 4, cats: 5
      trees: 0, akitas: 7, goldfish: 10
      akitas: 2, cars: 3, vizslas: 5
      goldfish: 3, perfumes: 7, akitas: 4
      samoyeds: 2, cars: 4, vizslas: 7
      trees: 5, cars: 0, perfumes: 0
      trees: 2, goldfish: 10, perfumes: 6
      cars: 8, trees: 9, akitas: 6
      goldfish: 10, trees: 10, perfumes: 0
      children: 7, samoyeds: 4, goldfish: 6
      vizslas: 9, perfumes: 1, children: 10
      vizslas: 8, trees: 5, akitas: 9
      akitas: 5, goldfish: 9, trees: 1
      vizslas: 3, trees: 2, children: 9
      samoyeds: 8, perfumes: 0, cats: 0
      perfumes: 4, vizslas: 3, akitas: 5
      pomeranians: 5, vizslas: 3, akitas: 9
      cats: 1, trees: 7, vizslas: 5
      children: 5, cats: 4, samoyeds: 5
      trees: 3, akitas: 2, goldfish: 6
      goldfish: 9, trees: 1, perfumes: 1
      cars: 2, pomeranians: 1, samoyeds: 2
      akitas: 2, trees: 3, cars: 4
      vizslas: 6, akitas: 2, samoyeds: 7
      trees: 0, perfumes: 5, cars: 7
      goldfish: 10, perfumes: 5, vizslas: 8
      akitas: 0, perfumes: 0, cars: 1
      samoyeds: 8, goldfish: 0, cars: 6
      perfumes: 0, children: 10, trees: 10
      perfumes: 6, akitas: 5, cats: 5
      vizslas: 7, akitas: 4, cats: 5
      samoyeds: 4, akitas: 1, trees: 8
      perfumes: 8, pomeranians: 5, cars: 1
      akitas: 10, trees: 4, cats: 3
      perfumes: 2, cats: 2, goldfish: 9
      cars: 4, trees: 1, akitas: 4
      samoyeds: 9, goldfish: 0, akitas: 9
      vizslas: 9, perfumes: 2, goldfish: 2
      perfumes: 1, cars: 9, samoyeds: 1
      trees: 0, goldfish: 0, samoyeds: 3
      perfumes: 7, cars: 1, goldfish: 0
      cars: 0, trees: 5, goldfish: 6
      akitas: 7, vizslas: 3, pomeranians: 5
      trees: 1, vizslas: 3, goldfish: 3
      akitas: 7, vizslas: 4, children: 0
      samoyeds: 5, trees: 0, akitas: 4
      perfumes: 9, goldfish: 9, cars: 8
      cars: 7, perfumes: 10, pomeranians: 8
      cars: 0, akitas: 7, perfumes: 4
      pomeranians: 0, cars: 9, perfumes: 10
      samoyeds: 10, perfumes: 10, cars: 9
      akitas: 2, vizslas: 8, cats: 5
      akitas: 3, children: 9, samoyeds: 10
      perfumes: 2, cars: 10, goldfish: 8
      cars: 3, children: 10, perfumes: 10
      cats: 9, akitas: 5, trees: 0
      akitas: 6, children: 2, vizslas: 1
      pomeranians: 6, trees: 10, samoyeds: 3
      cars: 7, perfumes: 10, trees: 1
      cars: 6, pomeranians: 8, trees: 2
      pomeranians: 9, cats: 0, perfumes: 7
      vizslas: 10, goldfish: 9, pomeranians: 5
      perfumes: 4, samoyeds: 7, cars: 9
      cars: 9, vizslas: 6, trees: 5
      cars: 7, trees: 1, vizslas: 4
      samoyeds: 4, goldfish: 10, cats: 4
      samoyeds: 0, akitas: 4, children: 5
      trees: 1, perfumes: 3, goldfish: 10
      pomeranians: 10, akitas: 3, cars: 2
      trees: 7, pomeranians: 4, goldfish: 10
      samoyeds: 10, perfumes: 0, cars: 9
      akitas: 0, pomeranians: 7, vizslas: 4
      cats: 2, vizslas: 8, goldfish: 5
      vizslas: 6, pomeranians: 9, perfumes: 0
      akitas: 6, cars: 7, vizslas: 5
      goldfish: 0, akitas: 9, cats: 0
      goldfish: 1, trees: 0, cars: 6
      perfumes: 6, cats: 8, pomeranians: 6
      cats: 0, goldfish: 6, perfumes: 2
      cars: 4, akitas: 1, samoyeds: 10
      goldfish: 9, samoyeds: 6, cats: 5
      cars: 0, vizslas: 7, trees: 0
      goldfish: 9, samoyeds: 1, children: 6
      cars: 6, perfumes: 7, samoyeds: 8
      trees: 8, goldfish: 9, children: 9
      perfumes: 0, cars: 5, goldfish: 4
      cats: 3, cars: 7, vizslas: 7
      pomeranians: 4, perfumes: 6, cars: 2
      cars: 9, akitas: 6, goldfish: 4
      pomeranians: 2, vizslas: 10, goldfish: 10
      children: 0, cats: 4, akitas: 7
      children: 10, akitas: 8, vizslas: 2
      children: 5, cars: 0, vizslas: 4
      perfumes: 10, trees: 3, pomeranians: 9
      samoyeds: 3, goldfish: 2, trees: 9
      cars: 2, cats: 5, pomeranians: 10
      cats: 6, pomeranians: 6, children: 9
      cats: 2, vizslas: 3, perfumes: 1
      akitas: 1, perfumes: 3, vizslas: 10
      cars: 7, perfumes: 0, trees: 0
      goldfish: 6, samoyeds: 6, pomeranians: 4
      trees: 2, goldfish: 6, children: 0
      goldfish: 0, trees: 2, akitas: 8
      pomeranians: 2, samoyeds: 9, vizslas: 1
      trees: 4, goldfish: 6, pomeranians: 6
      trees: 2, pomeranians: 3, goldfish: 1
      perfumes: 4, goldfish: 6, trees: 5
      akitas: 3, cars: 8, cats: 2
      cats: 6, vizslas: 0, akitas: 2
      perfumes: 3, goldfish: 10, akitas: 3
      goldfish: 3, vizslas: 1, akitas: 6
      perfumes: 4, trees: 1, goldfish: 5
      goldfish: 7, vizslas: 9, akitas: 1
      children: 8, cars: 8, trees: 4
      cars: 1, vizslas: 6, trees: 0
      goldfish: 2, cars: 2, akitas: 1
      goldfish: 5, akitas: 5, trees: 9
      cars: 5, vizslas: 6, goldfish: 6
      cats: 9, akitas: 3, goldfish: 9
      akitas: 3, cats: 2, children: 7
      goldfish: 0, pomeranians: 8, perfumes: 9
      trees: 0, pomeranians: 1, goldfish: 5
      goldfish: 10, trees: 3, vizslas: 4
      cats: 3, samoyeds: 1, children: 6
      perfumes: 3, children: 4, samoyeds: 2
      children: 6, trees: 2, goldfish: 1
      trees: 2, pomeranians: 3, goldfish: 5
      akitas: 10, vizslas: 7, trees: 1
      perfumes: 4, akitas: 2, vizslas: 7
      perfumes: 8, goldfish: 3, vizslas: 5
      trees: 4, pomeranians: 5, akitas: 10
      perfumes: 5, cars: 9, trees: 0
      akitas: 6, children: 8, trees: 10
      samoyeds: 7, akitas: 6, vizslas: 4
      children: 9, goldfish: 7, perfumes: 5
      trees: 1, perfumes: 4, cars: 1
      samoyeds: 1, perfumes: 4, pomeranians: 8
      trees: 7, goldfish: 10, akitas: 0
      akitas: 1, vizslas: 6, cars: 7
      vizslas: 3, goldfish: 8, trees: 4
      akitas: 10, vizslas: 2, trees: 3
      samoyeds: 6, pomeranians: 1, perfumes: 0
      samoyeds: 3, cars: 1, trees: 0
      vizslas: 0, pomeranians: 9, akitas: 4
      perfumes: 9, pomeranians: 8, vizslas: 9
      vizslas: 7, cars: 4, perfumes: 10
      cars: 0, samoyeds: 5, goldfish: 10
      children: 4, vizslas: 5, akitas: 4
      samoyeds: 9, perfumes: 4, vizslas: 6
      perfumes: 5, cars: 4, samoyeds: 5
      akitas: 3, vizslas: 2, perfumes: 1
      cars: 8, cats: 7, children: 5
      vizslas: 9, perfumes: 2, akitas: 10
      trees: 10, pomeranians: 9, goldfish: 3
      children: 4, cars: 10, perfumes: 2
      children: 7, samoyeds: 5, cats: 0
      akitas: 10, samoyeds: 5, vizslas: 5
      goldfish: 8, trees: 3, perfumes: 3
      goldfish: 10, vizslas: 0, perfumes: 2
      trees: 1, vizslas: 7, pomeranians: 4
      samoyeds: 8, vizslas: 3, trees: 2
      goldfish: 2, perfumes: 5, samoyeds: 9
      cats: 3, vizslas: 10, akitas: 5
      cars: 7, goldfish: 5, akitas: 8
      children: 6, goldfish: 10, trees: 1
      cats: 2, akitas: 6, samoyeds: 7
      cars: 10, children: 4, goldfish: 2
      cats: 0, perfumes: 5, akitas: 9
      pomeranians: 7, akitas: 0, samoyeds: 9
      trees: 0, akitas: 9, vizslas: 8
      cars: 0, trees: 10, perfumes: 9
      cats: 9, goldfish: 10, perfumes: 10
      cars: 3, vizslas: 6, cats: 3
      vizslas: 10, perfumes: 4, goldfish: 5
      perfumes: 4, akitas: 10, trees: 2
      pomeranians: 5, cars: 4, perfumes: 3
      pomeranians: 9, vizslas: 5, akitas: 2
      cars: 10, goldfish: 8, trees: 2
      perfumes: 7, children: 9, goldfish: 9
      akitas: 6, cats: 2, goldfish: 7
      goldfish: 9, perfumes: 0, cars: 2
      children: 4, vizslas: 0, trees: 2
      akitas: 4, cars: 8, pomeranians: 4
      vizslas: 8, perfumes: 7, goldfish: 1
      goldfish: 10, samoyeds: 7, vizslas: 3
      akitas: 1, vizslas: 6, perfumes: 6
      pomeranians: 8, goldfish: 6, cats: 3
      goldfish: 2, vizslas: 4, akitas: 7
      cars: 10, perfumes: 10, vizslas: 3
      vizslas: 7, pomeranians: 4, perfumes: 4
      goldfish: 4, vizslas: 7, trees: 5
      cars: 8, trees: 0, goldfish: 4
      cars: 8, perfumes: 5, vizslas: 4
      vizslas: 3, akitas: 7, samoyeds: 6
      trees: 0, perfumes: 6, cars: 10
      pomeranians: 4, trees: 1, perfumes: 6
      cars: 10, perfumes: 6, akitas: 2
      perfumes: 6, samoyeds: 0, akitas: 0
      children: 1, perfumes: 9, vizslas: 3
      goldfish: 9, samoyeds: 3, perfumes: 8
      goldfish: 4, cars: 10, vizslas: 7
      samoyeds: 7, vizslas: 7, cats: 2
      trees: 1, goldfish: 8, perfumes: 0
      cars: 3, perfumes: 2, trees: 3
      samoyeds: 6, vizslas: 0, akitas: 6
      trees: 3, akitas: 7, goldfish: 1
      cars: 9, trees: 1, perfumes: 0
      pomeranians: 0, children: 5, perfumes: 8
      cars: 0, perfumes: 6, children: 4
      akitas: 7, pomeranians: 9, cats: 6
      cats: 6, trees: 1, cars: 0
      children: 8, akitas: 5, perfumes: 9
      perfumes: 5, akitas: 10, trees: 9
      akitas: 4, perfumes: 10, vizslas: 7
      trees: 3, children: 10, samoyeds: 4
      vizslas: 5, goldfish: 2, akitas: 2
      samoyeds: 3, trees: 2, cars: 6
      children: 9, akitas: 0, pomeranians: 3
      perfumes: 10, akitas: 2, cars: 7
      perfumes: 10, samoyeds: 6, akitas: 10
      vizslas: 10, trees: 2, akitas: 8
      perfumes: 8, akitas: 2, pomeranians: 7
      cars: 8, trees: 3, goldfish: 6
      cars: 1, goldfish: 5, vizslas: 5
      vizslas: 2, akitas: 10, samoyeds: 4
      vizslas: 2, akitas: 10, perfumes: 9
      akitas: 3, vizslas: 8, goldfish: 1
      vizslas: 7, pomeranians: 5, trees: 10
      cats: 6, perfumes: 10, children: 6
      trees: 2, cars: 5, goldfish: 8
      trees: 0, goldfish: 6, samoyeds: 3
      perfumes: 0, cars: 8, trees: 1
      akitas: 4, trees: 8, perfumes: 9
      goldfish: 1, perfumes: 7, akitas: 6
      vizslas: 3, cars: 1, perfumes: 6
      trees: 1, akitas: 7, goldfish: 10
      samoyeds: 4, vizslas: 2, cars: 9
      perfumes: 10, children: 1, trees: 8
      perfumes: 0, vizslas: 9, cars: 8
      cats: 0, children: 7, trees: 3
      vizslas: 4, cats: 6, perfumes: 2
      trees: 3, children: 5, cars: 8
      goldfish: 7, vizslas: 7, children: 5
      cars: 5, akitas: 3, goldfish: 5
      vizslas: 0, pomeranians: 5, cars: 0
      goldfish: 4, akitas: 0, cats: 5
      cars: 5, trees: 1, goldfish: 6
      perfumes: 10, trees: 8, cars: 1
      perfumes: 4, akitas: 3, cars: 0
      vizslas: 3, cars: 7, pomeranians: 1
      perfumes: 1, vizslas: 7, akitas: 8
      goldfish: 10, samoyeds: 10, pomeranians: 5
      goldfish: 6, trees: 0, perfumes: 0
      goldfish: 5, vizslas: 0, children: 5
      cars: 3, vizslas: 7, perfumes: 10
      vizslas: 5, trees: 9, goldfish: 8
      akitas: 2, goldfish: 6, children: 7
      samoyeds: 0, perfumes: 1, pomeranians: 5
      trees: 2, goldfish: 9, vizslas: 0
      perfumes: 1, cars: 6, goldfish: 9
      pomeranians: 3, perfumes: 5, trees: 9
      cats: 3, akitas: 0, vizslas: 8
      pomeranians: 10, akitas: 8, trees: 5
      goldfish: 6, akitas: 10, perfumes: 2
      cats: 0, goldfish: 0, children: 9
      children: 4, akitas: 10, vizslas: 8
      vizslas: 3, goldfish: 9, children: 10
      children: 8, cats: 6, vizslas: 10
      cars: 7, akitas: 10, samoyeds: 5
      vizslas: 9, akitas: 6, trees: 2
      vizslas: 5, akitas: 1, children: 5
      vizslas: 8, goldfish: 3, perfumes: 6
      trees: 3, samoyeds: 1, pomeranians: 6
      akitas: 1, vizslas: 5, cars: 8
      akitas: 4, cars: 4, vizslas: 9
      vizslas: 1, akitas: 2, cats: 2
      trees: 7, vizslas: 5, akitas: 6
      akitas: 8, trees: 2, perfumes: 6
      akitas: 1, trees: 1, samoyeds: 4
      cars: 0, akitas: 5, vizslas: 3
      cats: 2, goldfish: 9, children: 8"""
}
