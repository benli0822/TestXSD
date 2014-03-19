package Test.XsdToXml

import java.io.FileWriter
import org.xml.sax.helpers.XMLReaderFactory
import scala.collection.mutable.ArrayBuffer

/**
 * Created by benli on 21/01/14.
 */
// <?xml version="1.0" encoding="UTF-8"?>
// <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
//   <xs:element name="personne">
//     <xs:complexType>
//       <xs:sequence>
//         <xs:element name="nom" type="xs:string" />
//         <xs:element name="prenom" type="xs:string" />
//         <xs:element name="date_naissance" type="xs:date" />
//         <xs:element name="etablissement" type="xs:string" />
//         <xs:element name="num_tel" type="xs:string" />
//       </xs:sequence>
//     </xs:complexType>
//   </xs:element>
// </xs:schema>
class personne(nom: String, prenom: String, dateNaissance: String, etablissement: String, numTel: String) {
  //  val xmlVersion = "1.0"
  //  val xmlEncoding = "UTF-8"
  //  val schemaLang = "http://www.w3.org/2001/XMLSchema"
  //  val factory = SchemaFactory.newInstance(schemaLang)
  //  val schema = factory.newSchema(new StreamSource(xsdFile))
  val elementBuffer: ArrayBuffer[String] = new ArrayBuffer[String]

  /* problem: limited with this format, not adapted for all kinds */
  def toXml() {
    elementBuffer += nom
    elementBuffer += prenom
    elementBuffer += dateNaissance
    elementBuffer += etablissement
    elementBuffer += numTel
  }

  def getElements(): ArrayBuffer[String] = {
    return elementBuffer
  }
}

object PersonneExample {
  val p1 = new personne(
    "JIN",
    "Benli",
    "2014-01-22",
    "Université Lille 1",
    "0320122334"
  )
  val p2 = new personne(
    "JIN",
    "Benli",
    "2014-01-22",
    "Université Lille 1",
    "0320122334"
  )
  val xsdFile = "personne.xsd"
  val saxReader = XMLReaderFactory.createXMLReader();
  val xsdHandler = new XsdHandler(xsdFile)

  def main(args: Array[String]) {
    /* analyse the xsd file, get the format of xml file */
    saxReader.setContentHandler(xsdHandler)
    saxReader.parse(xsdFile)
    val elementsList = xsdHandler.getElementsList
    /* from classes, get necessary information for filling in the xml file */
    p1.toXml()
    val element1 = p1.getElements()
    p2.toXml()
    val element2 = p2.getElements()
    println(element1)
    println(element2)
    var contentBuffer = new ArrayBuffer[ArrayBuffer[String]]
    contentBuffer += element1
    contentBuffer += element2
    /* creation of xml file with file creation class */
    val fc = new FileCreation(elementsList, contentBuffer)
    fc.export()
  }

  // <?xml version="1.0" encoding="UTF-8"?>
  // <personne xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="personne.xsd">
  //   <nom>JIN</nom>
  //   <prenom>Benli</prenom>
  //   <date_naissance>22-01-2014</date_naissance>
  //   <etablissement>Université Lille 1</etablissement>
  //   <num_tel>0320122334</num_tel>
  // </personne>
}

