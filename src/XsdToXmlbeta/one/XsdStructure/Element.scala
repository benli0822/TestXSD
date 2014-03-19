package XsdToXmlbeta.one.XsdStructure

import scala.xml.Node

/**
 * Created by benli on 29/01/14.
 */

/** for all kind of element, we define the principal function */
abstract class Element(element: Node) {
  def getChild = element.child

  def getName = element.attribute("name")

  def getType = element.attribute("type")

  def getAttributes = element.attributes
}

/** sequenceElement for all child element */
class SequenceElement(element: Node) extends Element(element: Node) {

}

/** rootElement for all parent element */
class RootElement(element: Node) extends Element(element: Node) {
  def getChildListElem = for (e <- element.child \\ "element") yield new SequenceElement(e)
}
