package Util

/**
 * Created by benli on 15/01/14.
 */
class PrintList {
  def printList(args: TraversableOnce[_]): Unit = {
    args.foreach(println)
  }
}
