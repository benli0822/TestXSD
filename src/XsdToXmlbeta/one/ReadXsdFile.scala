package XsdToXmlbeta.one

/**
 * Created by benli on 29/01/14.
 */
object ReadXsdFile {

  val xsdFile = scala.xml.XML.loadFile("personne.xsd")
  val element = xsdFile \\ "element"

  def main(args: Array[String]) {
    println(element)
    println(xsdFile.getClass)
    for (e <- element) {
      //      println(e.child \\ "element")
      for (e1 <- e.child \\ "element") {
        println(e1.getClass)
      }
    }
  }

}
