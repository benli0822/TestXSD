package Test.XsdToXml

import scala.collection.mutable.ArrayBuffer
import org.xml.sax.helpers.DefaultHandler
import org.xml.sax.Attributes

/**
 * Created by benli on 22/01/14.
 */
class XsdHandler(xsdFile: String) extends DefaultHandler {
  var root: String = null
  var sequence: ArrayBuffer[String] = new ArrayBuffer[String]
  // buffer in scala with dynamic length
  var sequenceElements = false
  var rootElement = true

  val classList: ArrayBuffer[ArrayBuffer[String]] = new ArrayBuffer[ArrayBuffer[String]]
  val elementsList: ArrayBuffer[RootElement] = new ArrayBuffer[RootElement]

  /* adding content into correspond element */
  override def startElement(nameSpaceURI: String, localName: String, rawName: String,
                            attributs: Attributes): Unit = {
    /* if we have already read complexType element, begin to add element into sequence */
    if (localName == "complexType")
      rootElement = false
    if (localName == "sequence")
      sequenceElements = true
    var index = 0
    var classConfig: ArrayBuffer[String] = new ArrayBuffer[String]
    while (index < attributs.getLength()) {
      // on parcourt la liste des attributs
      // println(localName == "element")
      if (localName == "element") {
        // println(attributs.getLocalName(index))
        if (attributs.getLocalName(index) == "name") {
          // println(readComplexType)
          if (rootElement) {
            root = attributs.getValue(index)
          }
          else if (sequenceElements)
            sequence += attributs.getValue(index)
        }
      }
      classConfig += attributs.getLocalName(index) + ": " + attributs.getValue(index)
      index += 1
    }
    if (classConfig.length != 0)
      classList += classConfig
  }

  /* control the difference between root element and sequence element */
  override def endElement(nameSpaceURI: String, localName: String, rawName: String): Unit = {
    if (localName == "sequence")
      sequenceElements = false
    if (localName == "complexType") {
      /* means a root element has end its definition, so create a new XsdFormat object and add it into the list */
      rootElement = true
      elementsList += new RootElement(root, sequence)
      root = null
      sequence = new ArrayBuffer[String]
    }
  }

  /* for debug */
  override def endDocument(): Unit = {
    for (e <- elementsList) {
      println(e.showContent)
      // e.generateClass
    }
    for (c <- classList) {
      println("***" + c)
    }
    val ef = new ElementFactory(elementsList, classList)
    ef.buildElementsClass
  }

  /* return the element list */
  def getElementsList: ArrayBuffer[RootElement] = elementsList

  def getClassList: ArrayBuffer[ArrayBuffer[String]] = classList
}
