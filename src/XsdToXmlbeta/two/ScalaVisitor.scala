package XsdToXmlbeta.two

import scala.collection.mutable.ListBuffer

/**
 * Created by JIN benli on 19/02/14.
 * Scala Visitor for providing a scala class generation interface
 */
class ScalaVisitor(val elem: Element) {

  // noting all class created by factory method and return itself for chain of responsibility generation
  val _elemBuffer: ListBuffer[ScClass] = new ListBuffer[ScClass]()

  _elemBuffer += ScFunction("body", List("getLevel", elem.level))

  elem match {
    case parent: ParentElement => {
      val childList = parent.childList
      _elemBuffer += ScFunction("body", List("getChildList", childList.toList))
    }
    case children: ChildElement => {
      val parentList = children.parent
      _elemBuffer += ScFunction("body", List("getParent", parentList.toList))
    }
  }

  for (att <- elem.getAttributes) att.key match {
    case "name" => _elemBuffer += ScFunction("name", List(att.value.toString()))
    case "type" => _elemBuffer += ScFunction("param", List(att.value.toString()))
    case _ =>
  }

  def elemBuffer = _elemBuffer
}

object Test1 {
  def main(args: Array[String]) {
    val parser = new XsdParser("address.xsd")
    parser.parse()
    val scVisitor = new ScalaVisitor(parser.childList(1))
    for (e <- scVisitor.elemBuffer)
      println(e.write.toString)
  }
}
