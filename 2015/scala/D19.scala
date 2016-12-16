import scala.collection.mutable

/*
Rudolph the Red-Nosed Reindeer is sick! His nose isn't shining very brightly, and he needs medicine.

Red-Nosed Reindeer biology isn't similar to regular reindeer biology; Rudolph is going to need custom-made medicine. Unfortunately, Red-Nosed Reindeer chemistry isn't similar to regular reindeer chemistry, either.

The North Pole is equipped with a Red-Nosed Reindeer nuclear fusion/fission plant, capable of constructing any Red-Nosed Reindeer molecule you need. It works by starting with some input molecule and then doing a series of replacements, one per step, until it has the right molecule.

However, the machine has to be calibrated before it can be used. Calibration involves determining the number of molecules that can be generated in one step from a given starting point.

For example, imagine a simpler machine that supports only the following replacements:

H => HO
H => OH
O => HH
Given the replacements above and starting with HOH, the following molecules could be generated:

HOOH (via H => HO on the first H).
HOHO (via H => HO on the second H).
OHOH (via H => OH on the first H).
HOOH (via H => OH on the second H).
HHHH (via O => HH).
So, in the example above, there are 4 distinct molecules (not five, because HOOH appears twice) after one replacement from HOH. Santa's favorite molecule, HOHOHO, can become 7 distinct molecules (over nine replacements: six from H, and three from O).

The machine replaces without regard for the surrounding characters. For example, given the string H2O, the transition H => OO would result in OO2O.

Your puzzle input describes all of the possible replacements and, at the bottom, the medicine molecule for which you need to calibrate the machine. How many distinct molecules can be created after all the different ways you can do one replacement on the medicine molecule?

Your puzzle answer was 509.

--- Part Two ---

Now that the machine is calibrated, you're ready to begin molecule fabrication.

Molecule fabrication always begins with just a single electron, e, and applying replacements one at a time, just like the ones during calibration.

For example, suppose you have the following replacements:

e => H
e => O
H => HO
H => OH
O => HH
If you'd like to make HOH, you start with e, and then make the following replacements:

e => O to get O
O => HH to get HH
H => OH (on the second H) to get HOH
So, you could make HOH after 3 steps. Santa's favorite molecule, HOHOHO, can be made in 6 steps.

How long will it take to make the medicine? Given the available replacements and the medicine molecule in your puzzle input, what is the fewest number of steps to go from e to the medicine molecule?
 */

object D19 {
  val Steps = Seq(("Al", "ThF"), ("Al", "ThRnFAr"), ("B", "BCa"), ("B", "TiB"), ("B", "TiRnFAr"), ("Ca", "CaCa"), ("Ca", "PB"), ("Ca", "PRnFAr"), ("Ca", "SiRnFYFAr"), ("Ca", "SiRnMgAr"), ("Ca", "SiTh"), ("F", "CaF"), ("F", "PMg"), ("F", "SiAl"), ("H", "CRnAlAr"), ("H", "CRnFYFYFAr"), ("H", "CRnFYMgAr"), ("H", "CRnMgYFAr"), ("H", "HCa"), ("H", "NRnFYFAr"), ("H", "NRnMgAr"), ("H", "NTh"), ("H", "OB"), ("H", "ORnFAr"), ("Mg", "BF"), ("Mg", "TiMg"), ("N", "CRnFAr"), ("N", "HSi"), ("O", "CRnFYFAr"), ("O", "CRnMgAr"), ("O", "HP"), ("O", "NRnFAr"), ("O", "OTi"), ("P", "CaP"), ("P", "PTi"), ("P", "SiRnFAr"), ("Si", "CaSi"), ("Th", "ThCa"), ("Ti", "BP"), ("Ti", "TiTi"), ("e", "HF"), ("e", "NAl"), ("e", "OMg")).map(x => (x._2, x._1)).sortBy(_._1.length).reverse
  val TestSteps = Seq(("H", "HO"), ("H", "OH"), ("O", "HH"), ("e", "H"), ("e", "O")).map(x => (x._2, x._1))

  val R = Map("Al" -> Seq("ThF", "ThRnFAr"),
    "B" -> Seq("BCa", "TiB", "TiRnFAr"),
    "Ca" -> Seq("CaCa", "PB", "PRnFAr", "SiRnFYFAr", "SiRnMgAr", "SiTh"),
    "F" -> Seq("CaF", "PMg", "SiAl"),
    "H" -> Seq("CRnAlAr", "CRnFYFYFAr", "CRnFYMgAr", "CRnMgYFAr", "HCa", "NRnFYFAr", "NRnMgAr", "NTh", "OB", "ORnFAr"),
    "Mg" -> Seq("BF", "TiMg"),
    "N" -> Seq("CRnFAr", "HSi"),
    "O" -> Seq("CRnFYFAr", "CRnMgAr", "HP", "NRnFAr", "OTi"),
    "P" -> Seq("CaP", "PTi", "SiRnFAr"),
    "Si" -> Seq("CaSi"),
    "Th" -> Seq("ThCa"),
    "Ti" -> Seq("BP", "TiTi"),
    "e" -> Seq("HF", "NAl", "OMg")
  )
var deadEndHit = 0
  val TestR = Map("H" -> Seq("HO", "OH"),
    "O" -> Seq("HH")
  )

  val Molecule = "CRnCaSiRnBSiRnFArTiBPTiTiBFArPBCaSiThSiRnTiBPBPMgArCaSiRnTiMgArCaSiThCaSiRnFArRnSiRnFArTiTiBFArCaCaSiRnSiThCaCaSiRnMgArFYSiRnFYCaFArSiThCaSiThPBPTiMgArCaPRnSiAlArPBCaCaSiRnFYSiThCaRnFArArCaCaSiRnPBSiRnFArMgYCaCaCaCaSiThCaCaSiAlArCaCaSiRnPBSiAlArBCaCaCaCaSiThCaPBSiThPBPBCaSiRnFYFArSiThCaSiRnFArBCaCaSiRnFYFArSiThCaPBSiThCaSiRnPMgArRnFArPTiBCaPRnFArCaCaCaCaSiRnCaCaSiRnFYFArFArBCaSiThFArThSiThSiRnTiRnPMgArFArCaSiThCaPBCaSiRnBFArCaCaPRnCaCaPMgArSiRnFYFArCaSiThRnPBPMgAr"
  val TestMolecule = "HOH"

  def main(args: Array[String]) {
    //    println(replace(TestR, TestMolecule).toSet.size)
    //    val x = step(Steps, Seq(), "e", Molecule)
    //    println(x.map(_.size).min)
    //    println(findMinWidth(TestSteps, "HOHOHO", "e"))
    //    println(findMinWidth(Steps, Molecule, "e"))
    println(findMinDepth(Steps, Molecule, "e", 0, scala.collection.mutable.Set[String]()))
  }


  def step(available: Seq[(String, String)], steps: Seq[(String, String)], currentText: String, target: String): Seq[Seq[(String, String)]] = {
    if (currentText == target) Seq(steps)
    else {
      available.flatMap { case (from, to) =>
        val replacements = replaceAll(from, to, currentText)
        replacements.flatMap(x => if (x.length <= target.length) step(available, steps :+(from, to), x, target) else Nil)
      }
    }
  }

  def findMinDepth(available: Seq[(String, String)], currentText: String, target: String, stepCount: Int, deadend: mutable.Set[String]): (Boolean, Int) = {
    if (currentText == target) (true, stepCount)
    else {
      var result = -1
      var found = false
      var i = 0
      do {
        val a = available(i)
        val replacements = replaceAll(a._1, a._2, currentText)
        var j = 0
        if (replacements.nonEmpty) {
          do {
            val r = replacements(j)
            if (!deadend.contains(r)) {
              val (foundD, resultD) = findMinDepth(available, r, target, stepCount + 1, deadend)
//              if (!foundD) deadend.add(r)
              found = foundD
              if (found) result = resultD
            } else {
              deadEndHit = deadEndHit + 1
              println(deadEndHit)
            }
            j = j+1
          } while (!found && j < replacements.size)
        }
        i = i + 1
      } while (!found && i < available.size)
      (found, result)
    }
  }


  def findMinWidth(available: Seq[(String, String)], start: String, target: String): Int = {
    var q = scala.collection.mutable.Queue(start)
    var found = false
    var min = 0
    var stepCount = -1
    while (q.nonEmpty && !found) {
      stepCount = stepCount + 1
      val count = q.size
      println(s"stepCount=$stepCount, count=$count")
      var i = 0
      while (i < count && !found) {
        val c = q.dequeue()
        if (c == target) {
          found = true
        } else {
          available.foreach { move =>
            q.enqueue(replaceAll(move._1, move._2, c): _*)
          }
        }
        i = i + 1
      }
    }
    stepCount
  }

  def replace(mapping: Map[String, Seq[String]], text: String): Seq[String] = {
    mapping.flatMap { case (key, values) => values.flatMap(v => replaceAll(key, v, text)) }.toSeq
  }

  def replaceAll(from: String, to: String, text: String): Seq[String] = {
    var beginIndex = 0
    var idx = text.indexOf(from, beginIndex)
    var result = Seq[String]()
    while (idx != -1) {
      val news = text.take(idx) + to + text.substring(idx + from.length)
      result = result :+ news
      idx = text.indexOf(from, idx + from.length)
    }
    result
  }


}
