package Test.XsdToXml

import scala.collection.mutable.ArrayBuffer
import java.io.{File, PrintWriter}

/**
 * Created by benli on 26/01/14.
 * Class for root element represent the structure of root element and its sequences elements
 */
class RootElement(root: String, sequence: ArrayBuffer[String]) {
  /* return the root element of the class tree */
  def getRoot() = root

  /* get its sequence list */
  def getSequenceList() = sequence

  /* add an element into the list */
  def addSequenceElement(element: String) {
    sequence += element
  }

  /* remove the index element in the list */
  def deleteSequenceElement(index: Int) {
    sequence.remove(index)
  }

  def showContent {
    println("root: " + root)
    println("sequence: " + sequence)
  }

  def generateClass(primitiveList: ArrayBuffer[SequenceElements], referenceList: ArrayBuffer[SequenceElements]) {
    val GENERATE_FILE_DIRECTORY = "generated"
    val classType = new PrimitiveClass
    val theDir = new File(GENERATE_FILE_DIRECTORY);
    // if the directory does not exist, create it
    if (!theDir.exists()) {
      println("creating directory: " + GENERATE_FILE_DIRECTORY);
      val result = theDir.mkdir();
      if (result) {
        println("DIR created");
      }
    }
    val fw = new PrintWriter("generated/" + root + ".scala")
    fw.println("package Test.XsdToXml\n\n")
    fw.print("class " + root + "(")
    for (i <- 0 until primitiveList.size - 1) {
      println("Adding " + primitiveList(i).getClassName)
      fw.print(primitiveList(i).getClassName + ": " + classType.getScalaType(primitiveList(i).getClassType) + ", ")
    }
    fw.print(primitiveList(primitiveList.size - 1).getClassName + ": " + classType.getScalaType(primitiveList(primitiveList.size - 1).getClassType) + ")")
    //    for (r <- referenceList){
    //      fw.print(r.getClassName + ": " + r.getClassType + ", ")
    //    }
    /* some problem remain to solve with the , */
    fw.close()
  }
}
