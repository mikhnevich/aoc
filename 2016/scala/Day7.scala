import scala.io.Source

object Day7 {

  import java.security.MessageDigest

  val md5 = MessageDigest.getInstance("MD5")

  val input = Source.fromFile("d7_input.txt").getLines().toList

  val r = "\\[(.*?)\\]".r
  val r2 = "\\[.*?\\]"

  def split(in: String): (List[String], List[String]) = {
    val inner = (for (i <- r.findAllIn(in)) yield i).toList
    val outer = in.replaceAll(r2, "|").split('|').toList
    (outer, inner)
  }

  def supportsSSL(s: String): Boolean = {
    val (outer, inner) = split(s)
    val abas = outer.flatMap(getABA)
    val inverted = abas.map(x => "" + x(1) + x(0) + x(1))
    inverted.exists(x => inner.exists(y => y.contains(x)))
  }

  def supportsTLS2(s: String): Boolean = {
    val (outer, inner) = split(s)
    outer.exists(hasABBA) && !inner.exists(hasABBA)
  }

  def getABA(s: String) = {
    val a = s.toCharArray
    var i = 0
    var result = Set[String]()
    while (i < a.length - 2) {
      if (a(i) == a(i + 2) && a(i + 1) != a(i)) {
        result += ("" + a(i) + a(i + 1) + a(i + 2))
      }
      i += 1
    }
    result
  }


  def hasABBA(s: String): Boolean = {
    val a = s.toCharArray
    var i = 0
    var found = false
    while (i < a.length - 3 && !found) {
      if (a(i) == a(i + 3) && a(i + 1) == a(i + 2) && a(i + 1) != a(i)) {
        found = true
      }
      i += 1
    }
    found
  }

  def supportsTLS(s: String): Boolean = {
    val a = s.toCharArray
    var i = 0
    var found = false
    var foundInner = false
    var level = 0
    while (i < a.length - 3 && !foundInner) {
      if (a(i) == ']') {
        level -= 1
        if (level < 0) {
          println(s"!!!!!!!!!!!!! $level")
        }
      }
      if (level == 0) {
        if (a(i) == a(i + 3) && a(i + 1) == a(i + 2) && a(i + 1) != a(i)) {
          found = true
        }
      } else if (level > 0) {
        if (a(i) == a(i + 3) && a(i + 1) == a(i + 2) && a(i + 1) != a(i)) {
          foundInner = true
        }
      }
      if (a(i) == '[') {
        level += 1
        if (level > 1) {
          println(s"!!!!!!!!!!!!! $level")
        }
      }
      i += 1
    }
    found && !foundInner
  }

  def main(args: Array[String]): Unit = {
//    val a = input.filter(supportsTLS)
//    val b = input.filter(supportsTLS2)
    val c = input.filter(supportsSSL)
    println(s"${c.size}")
  }
}
