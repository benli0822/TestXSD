package Test.SiteExemples

/**
 * Created by benli on 15/01/14.
 */

// pretty printer function for lambda calculus
abstract class Term

case class VarCase(name: String) extends Term

case class FunCase(arg: String, body: Term) extends Term

case class AppCase(f: Term, v: Term) extends Term

// class Var Fun App are defined in case class as a extend class, can be understood as the hierarchy of Term
// here change the Var Fun App added with a Case because the following
// definition overwrite the definition of Var App Fun in scala
object TermTest extends scala.App {

  // case class's behaviour definition
  def printTerm(term: Term) {
    term match {
      case VarCase(n) =>
        print(n)
      case FunCase(x, b) =>
        print("^" + x + ".")
        printTerm(b)
      case AppCase(f, v) =>
        print("(")
        printTerm(f)
        print(" ")
        printTerm(v)
        print(")")
    }
  }

  // scala all object with the equal function
  def isIdentityFun(term: Term): Boolean = term match {
    case FunCase(x, VarCase(y)) if x == y => true
    case _ => false
  }

  val id = FunCase("x", VarCase("x"))
  val t = FunCase("x", FunCase("y", AppCase(VarCase("x"), VarCase("y"))))
  printTerm(t)
  println()
  println(isIdentityFun(id))
  println(isIdentityFun(t))
}
