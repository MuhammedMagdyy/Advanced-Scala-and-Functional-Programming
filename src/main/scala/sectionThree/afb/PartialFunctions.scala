package sectionThree.afb

object PartialFunctions extends App {
  // function1[Int, Int] == Int => Int
  val aFunction = (x: Int) => x + 1

  val aFussyFunction = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  // {1, 2, 5} => Int (Accepts only those numbers)
  val nicer = (x: Int) => x match
    case 1 => 42
    case 2 => 56
    case 5 => 999
    case _ => throw new FunctionNotApplicableException

  val partialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function value

  println(partialFunction(2))

  // Utilities
  println(partialFunction.isDefinedAt(21)) // test arguments
  val lifted = partialFunction.lift // Int => Option[Int]
  println(lifted(2))
  println(lifted(98))

  val chainPF = partialFunction.orElse[Int, Int] {
    case 45 => 76
  }
  println(chainPF(2))
  println(chainPF(45))

  val totalFunction: Int => Int = {
    case 1 => 99
  } // PF extends normal functions

  val mappedList = List(1, 2, 3).map {
    case 1 => 43
    case 2 => 23
    case 3 => 12
  }
  println(mappedList)

  /*
    Notes: PF only have one parameter
  */
  scala.io.Source.stdin.getLines().foreach(line => println("You said: " + line))
}
