package XsdToXmlbeta.two

import scala.collection.SortedMap

/**
 * Created by JIN Benli on 26/03/14.
 */
package object htmlTest {

  implicit class StringExtension(s: String) {
    def tag = {
      if (!Escaping.validTag(s))
        throw new IllegalArgumentException(
          s"Illegal tag name: $s is not a valid XML tag name"
        )
      HtmlTag(s, Nil, SortedMap.empty)
    }
  }

}
