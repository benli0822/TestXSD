package XsdToXmlbeta.two

import scala.annotation.tailrec

/**
 * Created by JIN benli on 05/03/14.
 * Chain of responsibility of creating element scala class
 */
trait Chain[A, B] {
  def handle(obj: A): Either[A, B]
}

object Chain {
  implicit def list2Chain[A, B](list: List[Chain[A, B]]) = new Chain[A, B] {
    override def handle(obj: A): Either[A, B] = fold(obj, list)

    @tailrec // check that method will be optimised
    private def fold(obj: A, handlers: List[Chain[A, B]]): Either[A, B] = {
      if (handlers.isEmpty) Left(obj)
      else handlers.head.handle(obj) match {
        case msg: Right[A, B] => msg // return result if message is handled
        case Left(msg) => fold(msg, handlers.tail) // or transfer message to next handler
      }
    }
  }
}


class ScGenerator {

}
