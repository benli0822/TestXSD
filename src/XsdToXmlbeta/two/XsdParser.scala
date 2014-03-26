package XsdToXmlbeta.two

import scala.collection.mutable.ListBuffer
import scala.collection.mutable

/**
 * Created by JIN benli on 19/02/14.
 * for all kind of element, we define the principal function
 **/
class XsdParser(val fileName: String) {
  // save all sequence element when traverse the xsd file
  lazy val xsdFile = scala.xml.XML.loadFile(fileName)
  // find all element node sequence
  val element = xsdFile \\ "element"
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
//        case child: ChildElement =>
//          println("Creating " + child.getAttributeString("name") + " child level = " + child.level)
      }
      // noting all parent element
      parentList += elem
    }
  }
}


