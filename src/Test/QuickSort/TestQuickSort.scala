package Test.QuickSort

import Util.PrintList

/**
 * Created by benli on 15/01/14.
 */
object TestQuickSort {
  var list = Array(1, 2, 100, 2, 5, 7)
  var quickSort = new QuickSort()
  var printList = new PrintList()

  def main(args: Array[String]) {
    quickSort.sort(list)
    printList.printList(list)
  }
}
