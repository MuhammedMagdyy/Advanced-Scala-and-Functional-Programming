package sectionThree.afb

object CurriesPAF extends App {
  val superAdd: Int => Int => Int =
    x => y => x + y

  val adder = superAdd(3) // Int => Int = y => 3 + y
  println(adder(5)) // same to superAdd(3)(5)
  println(superAdd(3)(5)) // curried function

  def curriedAdder(x: Int)(y: Int): Int = x + y // curried method

  // lifting - ETA Expansion
  val add: Int => Int = curriedAdder(4)

  def inc(x: Int): Int = x + 1

  List(1, 2, 3).map(x => inc(x)) // ETA-Expansion
  List(1, 2, 3).map(inc) // same

  val add5 = curriedAdder(5) _ // _ converts to Int => Int (curried functions without lifting)

  // underscores are powerful
  def concat(a: String, b: String, c: String): String = a + b + c

  val insertName = concat("Hi, I'm ", _: String, ", Where are you from?") // Do an eta expansion
  println(insertName("Megzz")) // x: String => concat("Hi, I'm ", x, "Where are you from?")

  val filling = concat("Hello, ", _: String, _: String) // (x, y) => concat("string", x, y)
  println(filling("Megzz", " I hate scala"))
}
