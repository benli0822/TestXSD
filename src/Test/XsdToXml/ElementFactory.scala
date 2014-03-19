package Test.XsdToXml

import scala.collection.mutable.ArrayBuffer

/**
 * Created by benli on 27/01/14.
 */
class ElementFactory(rootElementList: ArrayBuffer[RootElement], classList: ArrayBuffer[ArrayBuffer[String]]) {
  val sequenceElementsList = new ArrayBuffer[SequenceElements]

  def buildElementsClass {
    /* first treat all element as sequence element */
    val cl = classList.distinct
    for (c <- cl) {
      sequenceElementsList += new SequenceElements(c)
    }
    /* check all dependence of root element */
    for (r <- rootElementList) {
      val sequenceList = r.getSequenceList()
      val primitiveList = new ArrayBuffer[SequenceElements]
      val referenceList = new ArrayBuffer[SequenceElements]
      for (s <- sequenceList) {
        for (se <- sequenceElementsList) {
          if (se.getClassName == s) {
            if (se.isPrimitive && !primitiveList.contains(se))
              primitiveList += se
            else if (!referenceList.contains(se)) {
              referenceList += se
              se.generateClass
            }
          }
        }
      }
      for (p <- primitiveList)
        println(p.getClassName)
      r.generateClass(primitiveList, referenceList)
    }
  }
}
