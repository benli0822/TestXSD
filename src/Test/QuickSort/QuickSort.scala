package Test.QuickSort

/**
 * Created by benli on 15/01/14.
 */
class QuickSort {
  // use Tab to distinguish end of phase like python
  // son-function within function declaration
  def sort(xs: Array[Int]) {
    def swap(i: Int, j: Int) {
      val t = xs(i)
      xs(i) = xs(j)
      xs(j) = t // if there is more than one variable declaration, use ";" to separate
    }

    def sort1(l: Int, r: Int) {
      val pivot = xs((l + r) / 2)
      var i = l
      var j = r
      while (i <= j) {
        while (xs(i) < pivot)
          i += 1
        while (xs(j) > pivot)
          j -= 1
        if (i <= j) {
          swap(i, j)
          i += 1
          j -= 1
        }
      }
      if (l < j)
        sort1(l, j)
      if (j < r)
        sort1(i, r)
    }
    sort1(0, xs.length - 1)
  }
}
