package XsdToXmlbeta.one

import XsdToXmlbeta.one.XsdStructure.{SequenceElement, RootElement, Element}
import scala.collection.mutable.ArrayBuffer

/**
 * Created by benli on 29/01/14.
 */
object XsdTest {
  val parentElementList: ArrayBuffer[RootElement] = new ArrayBuffer[RootElement]()
  /* save all root element when traverse the xsd file, root element is not the sense in xsd file, root here is not
    abstract, for exemple, for different level, an element can be a parent to its child, so it's a root element, but also
    it can be a child of his parent, so now it's a sequence element.
   */
  val childElemetnList: ArrayBuffer[SequenceElement] = new ArrayBuffer[SequenceElement]()
  // save all sequence element when traverse the xsd file
  val xsdFile = scala.xml.XML.loadFile("address.xsd")
  // destination of xsd file
  val element = (xsdFile \\ "element")
  // find all element nodeseq

  /** print all element of xsd file using visitor pattern */
  def printAllElement(element: Element) {
    element match {
      case root: RootElement => {
        println("Visiting " + root.getName + " root")
        for (r <- root.getChildListElem) {
          println("Visiting " + r.getName + " root/sequence")
        }
      }
      case sequence: SequenceElement => println("Visiting " + sequence.getName + "sequence")
    }
  }

  /** create correspond scala class from the xsd file using visitor pattern */
  def createAllClass(element: Element) {
    element match {
      // for all root element, create the relation with his child and add into the correspond element list
      case root: RootElement => {
        println("Visiting " + root.getName + " root")
        parentElementList += root
        for (r <- root.getChildListElem) {
          println("Visiting " + r.getName + " root/sequence")
          childElemetnList += r
        }
      }
      // normally this part will not be worked
      case sequence: SequenceElement => {
        println("Visiting " + sequence.getName + "sequence")
        if (!childElemetnList.contains(sequence))
          childElemetnList += sequence
      }
    }
  }

  def main(args: Array[String]) {
    for (e <- element) {
      println("***********")
      val elem = new RootElement(e)
      createAllClass(elem)
      //      val elemChildList = elem.getChildListElem
      //      for(e <- elemChildList){
      //        printAllElement(e)
      //      }
      //      println("+++++++++++")
    }

    println("\nNow print all root elements")
    for (r <- parentElementList) {
      print(r.getName)
      println(r.getType)
      val rootChildList = r.getChildListElem
      for (ele <- rootChildList) {
        printAllElement(ele)
      }
    }

    /* normally, this part is to make sure all the sequence elements are placed well, also can be used to compare with
      root list to find out the real unique root of xsd file */
    println("\nNow print all sequence elements")
    for (s <- childElemetnList) {
      println(s.getName)
    }
  }
}
