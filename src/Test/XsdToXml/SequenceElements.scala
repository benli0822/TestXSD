package Test.XsdToXml

import scala.collection.mutable.ArrayBuffer
import java.io.{PrintWriter, File}

/**
 * Created by benli on 27/01/14.
 */
class SequenceElements(classList: ArrayBuffer[String]) {
  var className: String = null
  var classType: String = null
  for (c <- classList) {
    if (c.contains("name")) {
      className = c.substring(6, c.length)
    }
    if (c.contains("type")) {
      classType = c.substring(6, c.length)
    }
    /* others remain to add in */
  }

  val primitiveDataType: List[String] = List(
    "xs:string",
    "xs:date",
    "xs:decimal",
    "xs:integer",
    "xs:boolean",
    "xs:time"
  )

  var primeClass = false
  if (primitiveDataType.contains(classType)) {
    primeClass = true
  }

  def generateClass {
    val GENERATE_FILE_DIRECTORY = "generated"
    val theDir = new File(GENERATE_FILE_DIRECTORY);
    // if the directory does not exist, create it
    if (!theDir.exists()) {
      println("creating directory: " + GENERATE_FILE_DIRECTORY);
      val result = theDir.mkdir();
      if (result) {
        println("DIR created");
      }
    }
    val fw = new PrintWriter("generated/" + className + ".scala")
    fw.println("package Test.XsdToXml.generated")
    fw.println("class " + className + "(" + className + ": " + classType + ")")
  }

  def isPrimitive = primeClass

  def getClassName = className

  def getClassType = classType
}
