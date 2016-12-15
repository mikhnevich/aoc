import scala.collection.mutable

/*

--- Day 21: RPG Simulator 20XX ---

Little Henry Case got a new video game for Christmas. It's an RPG, and he's stuck on a boss. He needs to know
what equipment to buy at the shop. He hands you the controller.

In this game, the player (you) and the enemy (the boss) take turns attacking. The player always goes first.
Each attack reduces the opponent's hit points by at least 1. The first character at or below 0 hit points loses.

Damage dealt by an attacker each turn is equal to the attacker's damage score minus the defender's armor score.
An attacker always does at least 1 damage. So, if the attacker has a damage score of 8, and the defender has an
armor score of 3, the defender loses 5 hit points. If the defender had an armor score of 300, the defender
would still lose 1 hit point.

Your damage score and armor score both start at zero. They can be increased by buying items in exchange for
gold. You start with no items and have as much gold as you need. Your total damage or armor is equal to the sum of
those stats from all of your items. You have 100 hit points.

Here is what the item shop is selling:


Rings:      Cost  Damage  Armor


You must buy exactly one weapon; no dual-wielding. Armor is optional, but you can't use more than one.
You can buy 0-2 rings (at most one for each hand). You must use any items you buy. The shop only has one of
each item, so you can't buy, for example, two rings of Damage +3.

For example, suppose you have 8 hit points, 5 damage, and 5 armor, and that the boss has 12 hit points,
7 damage, and 2 armor:

The player deals 5-2 = 3 damage; the boss goes down to 9 hit points.
The boss deals 7-5 = 2 damage; the player goes down to 6 hit points.
The player deals 5-2 = 3 damage; the boss goes down to 6 hit points.
The boss deals 7-5 = 2 damage; the player goes down to 4 hit points.
The player deals 5-2 = 3 damage; the boss goes down to 3 hit points.
The boss deals 7-5 = 2 damage; the player goes down to 2 hit points.
The player deals 5-2 = 3 damage; the boss goes down to 0 hit points.
In this scenario, the player wins! (Barely.)

You have 100 hit points. The boss's actual stats are in your puzzle input. What is the least amount of gold
you can spend and still win the fight?

Boss:
Hit Points: 109
Damage: 8
Armor: 2

*/
object Day21 {

  case class Item(name: String, cost: Int, damage: Int, armor: Int)

  val ItemPattern = "\\s*(\\w+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*".r

  def main(args: Array[String]): Unit = {
    solve(Integer.MAX_VALUE, (won, current, c) => won && c < current)
    solve(Integer.MIN_VALUE, (won, current, c) => !won && c > current)
  }

  case class P(hp: Int, damage: Int, armor: Int)

  // There's no need to have a loop to simulate the battle. The amount of damage done by each contestant is constant so you can just work out the number of moves each contestant needs with something like:
  // roundup(defender.hit_points / max(attacker.damage - defender.armor, 1))

  def calcArmor(a: Int, r1: Int, r2: Int) = {
    (if (a != armors.length) armors(a).armor else 0) +
      (if (r1 != rings.length) rings(r1).armor else 0) +
      (if (r2 != rings.length) rings(r2).armor else 0)
  }

  def calcDamage(w: Int, r1: Int, r2: Int) = {
    (if (w != weapons.length) weapons(w).damage else 0) +
      (if (r1 != rings.length) rings(r1).damage else 0) +
      (if (r2 != rings.length) rings(r2).damage else 0)
  }

  def cost(w: Int, a: Int, r1: Int, r2: Int): Int = {
    (if (w != weapons.length) weapons(w).cost else 0) +
      (if (a != armors.length) armors(a).cost else 0) +
      (if (r1 != rings.length) rings(r1).cost else 0) +
      (if (r2 != rings.length) rings(r2).cost else 0)
  }

  def solve(initialCost: Int, statusUpdate: (Boolean, Int, Int) => Boolean) = {
    var currentCost = initialCost
    var itemList = (0, 0, 0, 0)
    for {
      i <- 0 until weapons.length
      j <- 0 to armors.length
      k <- 0 to rings.length
      l <- k + 1 to rings.length
    } {
      val c = cost(i, j, k, l)

      val damage = calcDamage(i, k, l)
      val armor = calcArmor(j, k, l)

      // There's no need to have a loop to simulate the battle.
      // The amount of damage done by each contestant is constant so you can just work out the number of moves each
      // contestant needs with something like:
      // roundup(defender.hit_points / max(attacker.damage - defender.armor, 1))
      //
      val youSteps = Math.ceil(109.0 / Math.max(damage - 2, 1)).toInt
      val bossSteps = Math.ceil(100.0 / Math.max(8 - armor, 1)).toInt
      val youWon = youSteps <= bossSteps
      if (statusUpdate(youWon, currentCost, c)) {
        currentCost = c
        itemList = (i, j, k, l)
      }
    }
    println(s"$currentCost, $itemList")
  }

  val weapons = parseItems(
    """Dagger        8     4       0
Shortsword   10     5       0
Warhammer    25     6       0
Longsword    40     7       0
Greataxe     74     8       0""")

  val armors = parseItems(
    """Leather      13     0       1
                            Chainmail    31     0       2
                            Splintmail   53     0       3
                            Bandedmail   75     0       4
                            Platemail   102     0       5""")

  val rings = parseItems(
    """Damage1    25     1       0
                           Damage2    50     2       0
                           Damage3   100     3       0
                           Defense1   20     0       1
                           Defense2   40     0       2
                           Defense3   80     0       3""")

  def parseItems(s: String) = {
    s.split("\n").map {
      case ItemPattern(name, cost, damage, armor) => Item(name, cost.toInt, damage.toInt, armor.toInt)
      case x => throw new RuntimeException(s"Can't parse $x")
    }.toList
  }
}
