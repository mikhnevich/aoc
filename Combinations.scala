object Combinations {

  def combinations[A](current: List[A], available: List[A], accepted: List[A] => Boolean, bestRank: Int, rank: List[A] => Int, valid: List[A] => Boolean): List[List[A]] = {
    if (!valid(current) || rank(current) > bestRank) Nil
    else {
      var result = List[List[A]]()
      var currentRank = bestRank
      if (accepted(current)) {
        result = current :: result
        currentRank = rank(current)
      }
      for (i <- available.indices) {
        val e = available(i)
        val next = e :: current
        val nextAvailable = available.drop(i + 1)
        //        val nextAvailable = available.take(i + 1) ++ available.drop(i + 1)
        val lists = combinations(next, nextAvailable, accepted, currentRank, rank, valid)
        currentRank = lists.foldLeft(currentRank)((c, l) => if (rank(l) < c) rank(l) else c)
        result = (result ++ lists).filter(x => rank(x) <= currentRank)
      }
      result
    }
  }

}
