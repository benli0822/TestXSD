package Test

/**
 * Created by benli on 05/03/14.
 */

import annotation.tailrec

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

case class CoinCount(coinValue: Int, coinCount: Int)

case class CoinState(current: Int, acc: List[CoinCount])

case class CountChain(coin: Int) extends Chain[CoinState, List[CoinCount]] {
  override def handle(obj: CoinState) = {
    if (obj.current % coin == 0) Right(CoinCount(coin, obj.current / coin) :: obj.acc)
    else Left(CoinState(obj.current % coin, CoinCount(coin, obj.current / coin) :: obj.acc))
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    // define Chain of Responsibility classes list
    // convert it to Chain "composite"
    // and call handle function with defined parameters
    val ret = CountChain(5) :: CountChain(3) :: CountChain(1) :: Nil handle CoinState(14, Nil)
    println(ret)
  }
}
