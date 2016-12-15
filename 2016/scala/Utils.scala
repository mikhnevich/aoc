import javax.xml.bind.DatatypeConverter.printHexBinary

import scala.util.control.Breaks

/*

*/
object Utils {
  import java.security.MessageDigest

  val digest: MessageDigest = MessageDigest.getInstance("MD5")

  def md5(s: String): String = {
    printHexBinary(digest.digest(s.getBytes)).toLowerCase
  }

  def measure(f: => Unit): Unit = {
    val start = System.currentTimeMillis()
    f
    val end = System.currentTimeMillis()
    println(s"Execution time: ${(end - start)/1000} seconds")
  }
}
