package Test.XsdToXml

/**
 * Created by benli on 29/01/14.
 */
class PrimitiveClass {

  val primitiveDataType: List[String] = List(
    "xs:string",
    "xs:date",
    "xs:decimal",
    "xs:integer",
    "xs:boolean",
    "xs:time"
  )

  def getScalaType(xmlType: String): String = {
    if (xmlType == "xs:string")
      "String"
    else if (xmlType == "xs:date")
      "Date"
    else if (xmlType == "xs:integer")
      "BigInteger"
    else
      ""
  }

}
