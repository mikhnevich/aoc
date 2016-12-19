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

  def time[A](f: => A): A = {
    val start = System.currentTimeMillis()
    val x = f
    val end = System.currentTimeMillis()
    println(s"Execution time: ${(end - start)/1000} seconds")
    x
  }
}
