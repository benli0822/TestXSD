package XsdToXmlbeta.two


/**
 * Created by JIN Benli on 05/03/14.
 * Chain of responsibility of creating element scala class
 */

object ScGenerator {
  def main(args: Array[String]) {
    val parser = new XsdParser("address.xsd")
    parser.parse()
    for( c <- parser.childList ) {
      val scVisitor = new ScalaVisitor(c)
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
}