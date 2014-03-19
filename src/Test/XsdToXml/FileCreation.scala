package Test.XsdToXml

import scala.collection.mutable.ArrayBuffer
import java.io.FileWriter

/**
 * Created by benli on 22/01/14.
 */

class FileCreation(elementsList: ArrayBuffer[RootElement],
                   contentBuffer: ArrayBuffer[ArrayBuffer[String]]) {
  val fw = new FileWriter("personne.xml")
  val xsdFile = "personne.xsd"
  var i = 0
  var j = 0
  var k = 0

  def export(): Unit = {
    fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + System.getProperty("line.separator"))
    val elements = contentBuffer(k)
    fw.write("<" + elementsList(i).getRoot() + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation" +
      "=\"" + xsdFile + "\">" + System.getProperty("line.separator"))
    while (j < elementsList(i).getSequenceList().length) {
      fw.write("\t<" + elementsList(i).getSequenceList()(j) + ">" + elements(j) + "</" + elementsList(i).getSequenceList()(j) + ">" + System.getProperty("line.separator"))
      j += 1
    }
    fw.write("</" + elementsList(i).getRoot() + ">" + System.getProperty("line.separator"))
    i += 1
    k += 1
    while (i < elementsList.length) {
      /* create with the first element of xml */
      val elements = contentBuffer(k)
      fw.write("<" + elementsList(i).getRoot() + "\">" + System.getProperty("line.separator"))
      j = 0
      while (j < elementsList(i).getSequenceList().length) {
        fw.write("\t<" + elementsList(i).getSequenceList()(j) + ">" + elements(j) + "</" + elementsList(i).getSequenceList()(j) + ">" + System.getProperty("line.separator"))
        j += 1
      }
      fw.write("</" + elementsList(i).getRoot() + ">" + System.getProperty("line.separator"))
      k += 1
      i += 1
    }
    fw.close()
  }


}