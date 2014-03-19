package XsdToXmlbeta.two

/**
 * Created by JIN benli on 19/02/14.
 * Visitor visit the pointed level elements
 */
class Visitor {
  val parser: XsdParser = new XsdParser("address.xsd")
  parser.parse()

  /**
   * Visit the pointed level parent element
   * @param level
   */
  def visitParent(level: Int) {
    println("**Begin to visit level " + level + " parents")
    for (p <- parser.parentList) {
      if (p.level == level) {
        println("Visiting " + p.getAttributeString("name") + " Parent level = " + p.level)
        println("Visiting " + p.getAttributes)
      }
    }
  }

  /**
   * Visit the pointed level child elements
   * @param level
   */
  def visitChild(level: Int) {
    println("**Begin to visit level " + level + " children")
    for (c <- parser.childList) {
      if (c.level == level) {
        println("Visiting " + c.getAttributeString("name") + " Children level = " + c.level)
        println("Visiting " + c.getAttributes)
        println(c.getAttributes.toString())
        print(c.getAttributeString("name") + "'s parent is/are ");
        for (p <- c.parent) {
          println(p.getAttributeString("name"))
        }
      }
    }
  }
}

// link the visitor and scala visitor need to be done by iterating different level

object Test {
  def main(args: Array[String]) {
    val visitor = new Visitor
    visitor.visitParent(1)
    visitor.visitChild(2)
  }
}
