package XsdToXmlbeta.two

import scala.xml.Node
import scala.collection.mutable.ListBuffer
import scala.collection.mutable

/**
 * Created by JIN benli on 19/02/14.
 * for all kind of element, we define the principal function
 **/
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

  def setChildLevel {
    for (c <- childList) {
      val c_level = this.level + 1
      c.level_=(c_level)
      nameList.put(c.getName, c_level)
    }
  }
}

class XsdParser(val fileName: String) {
  // save all sequence element when traverse the xsd file
  val xsdFile = scala.xml.XML.loadFile(fileName)
  // find all element node sequence
  val element = (xsdFile \\ "element")
  val parentList: ListBuffer[ParentElement] = new ListBuffer[ParentElement]()
  val childList: ListBuffer[ChildElement] = new ListBuffer[ChildElement]()

  def parse() {
    var level = 1
    var nameList: mutable.HashMap[String, Int] = new mutable.HashMap[String, Int]()

    for (e <- element) {
      val elem = new ParentElement(e)
      // if this elem has been created by a previous parent element, get his level and maintain for this loop generation
      if (nameList.contains(elem.getAttributeString("name"))) {
        level = nameList(elem.getAttributeString("name"))
      }
      elem.level_=(level)
      elem.generateChildClass
      elem.setChildLevel
      // noting all created element name
      nameList ++= elem.nameList
      level += 1
      elem match {
        case parent: ParentElement => {
          println("Creating " + parent.getAttributeString("name") + " parent level = " + parent.level)
          for (c <- parent.childList) {
            println("Creating " + c.getAttributeString("name") + " parent/child level = " + c.level)
            c.parent_=(parent)
            // noting all child element
            childList += c
          }
        }
        case child: ChildElement => {
          println("Creating " + child.getAttributeString("name") + " child level = " + child.level)
        }
      }
      // noting all parent element
      parentList += elem
    }
  }
}


