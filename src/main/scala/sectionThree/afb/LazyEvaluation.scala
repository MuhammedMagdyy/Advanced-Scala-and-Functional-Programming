package sectionThree.afb

object LazyEvaluation extends App {
  // lazy DELAYs the evaluation of value
  // lazy values evaluate once, but only when they use for the first time
  lazy val x: Int = throw new RuntimeException
  //  println(x) // crash

  def sideEffect: Boolean = {
    println("Boo")
    true
  }

  def simpleEffect: Boolean = false

  lazy val lazyEvaluation = sideEffect

  println(if (simpleEffect && lazyEvaluation) "Yes" else "No")

  def byName(n: => Int): Int = {
    // Call by need
    lazy val t = n
    t + t + t + 1
  }

  def magicValue: Int = {
    println("Wating...")
    Thread.sleep(1000)
    42
  }

  println(byName(magicValue))

  def lessThan(i: Int): Boolean = {
    println(s"$i is less than 30?")
    i < 30
  }

  def greaterThan(i: Int): Boolean = {
    println(s"$i is greater than 20?")
    i > 20
  }

  val numbers = List(1, 35, 40, 23, 5)
  val lt30 = numbers.filter(lessThan)
  val gt20 = numbers.filter(greaterThan)
  //  println(gt20)
  val lt30Lazy = numbers.withFilter(lessThan) // uses lazy values
  val gt20Lazy = lt30Lazy.withFilter(greaterThan)
  println
  gt20Lazy.foreach(println)

  for {
    a <- List(1, 2, 3) if a % 2 == 0 // use lazy values
  } yield a + 1
  List(1, 2, 3).withFilter(_ % 2 == 0).map(_ + 1) // same List[Int]
}
