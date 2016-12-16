
/*

 */

object D23 {

  class Register(var value: BigInt) {

    override def toString = s"Register($value)"
  }

  case class Instruction(val name: String, val register: String, val offset: Int)

  def process(r: Map[String, Register], instructions: List[Instruction]) = {
    var pointer = 0

    var i = instructions(pointer)
    while (pointer < instructions.size && pointer >= 0) {
      println(pointer)
      i.name match {
        case "hlf" => r(i.register).value = r(i.register).value / 2
          pointer = pointer + 1

        case "tpl" => r(i.register).value = r(i.register).value * 3
          pointer = pointer + 1

        case "inc" => r(i.register).value = r(i.register).value + 1
          pointer = pointer + 1

        case "jmp" => pointer = pointer + i.offset

        case "jie" => if (r(i.register).value % 2 == 0) pointer = pointer + i.offset else pointer = pointer + 1

        case "jio" => if (r(i.register).value == 1) pointer = pointer + i.offset else pointer = pointer + 1

        case _ => throw new RuntimeException(i.toString)
      }
      if (pointer < instructions.size && pointer >= 0) i = instructions(pointer)
    }

  }

  def main(args: Array[String]) {
    val a = new Register(1)
    val b = new Register(0)
    val registers = Map("a" -> a, "b" -> b)
    // List(Instruction("inc", "a", 0), Instruction("jio", "a", 2), Instruction("tpl", "a", 0), Instruction("inc", "a", 0))
   process(registers, parse(input))
    println(registers)
  }

  def parse(s: String): List[Instruction] = {
    var result = List[Instruction]()
    s.split("\n").foreach { t =>
      val parts = t.trim.replace(",", "").split(' ')
      var offset = 0
      if (parts.size > 2) {
        offset = Integer.parseInt(parts(2))
      }
      if (parts(0) == "jmp") offset = Integer.parseInt(parts(1))
      result = result ++ List(new Instruction(parts(0), parts(1), offset ))
    }
    result
  }




  val input = """jio a, +16
                inc a
                inc a
                tpl a
                tpl a
                tpl a
                inc a
                inc a
                tpl a
                inc a
                inc a
                tpl a
                tpl a
                tpl a
                inc a
                jmp +23
                tpl a
                inc a
                inc a
                tpl a
                inc a
                inc a
                tpl a
                tpl a
                inc a
                inc a
                tpl a
                inc a
                tpl a
                inc a
                tpl a
                inc a
                inc a
                tpl a
                inc a
                tpl a
                tpl a
                inc a
                jio a, +8
                inc b
                jie a, +4
                tpl a
                inc a
                jmp +2
                hlf a
                jmp -7"""
}
