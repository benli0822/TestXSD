package XsdToXmlbeta.two

import scala.xml.Node
import scala.collection.mutable.ListBuffer
import scala.collection.mutable

/**
 * Created by JIN Benli on 26/03/14.
 */

abstract class Element(element: Node) {
  def getChild = element.child

  def getName = element.attribute("name").toString

  def getType = element.attribute("type").toString

  def getAttributeString(s: String) = {
    element.attribute(s) match {
      case Some(s) => s.toString()
      case None => null
    }
  }

  // return the MetaData of all attributes, flexible for visiting
  def getAttributes = element.attributes

  private var _level = 0

  // Setter
  def level_=(value: Int): Unit = _level = value

  // Getter
  def level = _level
}

/** All child element */
class ChildElement(element: Node) extends Element(element: Node) {
  // parent relation
  private var _parent: ListBuffer[ParentElement] = new ListBuffer[ParentElement]()

  def parent_=(value: ParentElement): Unit = _parent += value

  def parent = _parent
}

/** All parent element */
class ParentElement(element: Node) extends Element(element: Node) {
  // the child list
  val childList: ListBuffer[ChildElement] = new ListBuffer[ChildElement]()
  // put the child name and his level on preparing for the next level element creation
  val nameList: mutable.HashMap[String, Int] = new mutable.HashMap[String, Int]()

  def generateChildClass = for (e <- element.child \\ "element") yield childList += new ChildElement(e)

  def setChildLevel: Unit = {
    for (c <- childList) {
      val c_level = this.level + 1
      c.level_=(c_level)
      nameList.put(c.getName, c_level)
    }
  }
}
