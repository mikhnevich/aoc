object Day24 {
  val input = List(1, 2, 3, 5, 7, 13, 17, 19, 23, 29, 31, 37, 41, 43, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113)

  def main(args: Array[String]) {
    find()
  }

  def find() = {
    val w = input.sum / 3
    println(w)
    def isValid(x: List[Int]) = x.sum <= w

    def isTarget(x: List[Int]) = x.sum == w

    def min(a: Int, b: Int) = Math.min(a, b)

    def rank[A](x: List[A]) = x.size

    val result = Combinations.combinations(List[Int](), input, isTarget, Integer.MAX_VALUE, rank, isValid)
    val sorted = result.sortBy(a => a.foldLeft(1L)((p, e) => p * e))
    val minSorted = sorted.head
    println(minSorted)
    println(minSorted.foldLeft(1L)((p, e) => p * e))
  }

