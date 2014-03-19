package XsdToXmlbeta.two


/**
 * Created by JIN benli on 05/03/14.
 * Chain of responsibility of creating element scala class
 */

object ScGenerator {
  def main(args: Array[String]) {
    val parser = new XsdParser("address.xsd")
    parser.parse()
    val scVisitor = new ScalaVisitor(parser.childList(1))
    val bufferList = scVisitor.elemBuffer.toList
    var buffer = ""
    for (b <- bufferList) {
      if (b.getPos == 1) {
        buffer += b.write + " ("
      }
    }
    for (b <- bufferList) {
      if (b.getPos == 3) {
        buffer += b.write + ") {"
      }
    }

    for (b <- bufferList) {
      if (b.getPos == 4) {
        buffer += "\n\t" + b.write + "\n"
      }
    }
    buffer += "}"
    println(buffer)
  }
}