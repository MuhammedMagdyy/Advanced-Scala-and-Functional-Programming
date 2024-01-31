package sectionTwo.advScala

import scala.util.Try

object DarkSugars extends App {
  // #1 - Methods with single parameters
  def singleParamMethod(args: Int): String = s"$args are so cool"

  val description = singleParamMethod {
    // Code and Code and Code
    90
  }
  val tryInstance = Try {
    throw new RuntimeException
  }

  List(1, 2, 3).map { x =>
    x + 1
  }

  // #2 - Single abstract method
  trait Action {
    def act(x: Int): Int
  }

  val instance: Action = new Action:
    override def act(x: Int): Int = x * 6 // convert to lambda
  val lambda: Action = (x: Int) => x * 6

  val thread = new Thread(new Runnable:
    override def run(): Unit = println("Hi Scala")
  )
  val absThread = new Thread(() => println("Hi Scala")) // convert to lambda

  abstract class AnAbsType {
    def implemented: Int = 23

    def f(a: Int): Unit
  }

  val absInstance: AnAbsType = (a: Int) => println("Heey")

  // #3 - The (::) and (#::) methods
  val pre = 2 :: List(3, 4)
  println(pre)
  1 :: 2 :: 3 :: 4 :: List(5, 6)
  List(5, 6).::(4).::(3).::(2).::(1) // same

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this
  }

  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  // #4 - Multi-word method naming
  class Boy(name: String) {
    def `and then said`(n: String): Unit = println(s"$name said $n")
  }

  val megoz = new Boy("Megoz")
  megoz `and then said` ("I hate Scala")

  // #5 - Infix types
  class Composite[A, B]

  // same val composite: Composite[Int, String]
  val composite: Int Composite String = null

  class -->[A, B]

  val towards: Int --> String = null

  // #6 - Update() -> like apply() - Special
  val array = Array(1, 2, 3)
  array(2) = 7 // array.update(2, 7) (same)

  // #7 -  Setters for mutable containers
  class Mutable {
    private var internalMember: Int = 0
    def member:Int = internalMember // getter
    def member_=(value: Int): Unit = internalMember = value // setter (_=)
  }
  val mutable = new Mutable
  mutable.member = 45 // mutable.member_=(45) same
}
