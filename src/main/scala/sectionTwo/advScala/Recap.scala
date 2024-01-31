package sectionTwo.advScala

import scala.annotation.tailrec

object Recap extends App {
  val condition: Boolean = true
  val expression = if (condition) 42 else 32
  println(expression) // 42

  val codeBlock = {
    if (condition) 54 else 32
  }
  println(codeBlock)

  val unitVoid = println("Hi I'm unit")

  def amFunction(x: Int): Int = x * 2

  @tailrec def fact(n: Int, acc: Int): Int =
    if (n <= 0) acc
    else fact(n - 1, n * acc)

  class Human

  class Employee extends Human

  val anEmployee: Human = new Employee

  trait Megzz {
    def eat(m: Human): Unit
  }

  class HR extends Human with Megzz {
    override def eat(m: Human): Unit = println("I'm eating employees HAHAHA")
  }

  val anHR = new HR
  anHR.eat(anEmployee)
  anHR eat anEmployee // natural language

  println(1.+(2)) // valid expression

  abstract class MyList[+A]

  object MyList

  case class Person(name: String, age: Int)

  val exeption = throw new RuntimeException
  val failure = try {
    throw new RuntimeException
  } catch {
    case e: Exception => "I have an exception"
  } finally {
    println("Code runs ")
  }

  val inc = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  inc(1)
  val anonymousINC = (x: Int) => x + 1
  List(1, 2, 3).map(anonymousINC) // HOF

  val pairs = for {
    num <- List(1, 2, 3)
    char <- List('a', 'b', 'c')
  } yield num + '-' + char
  println(pairs)

  val map = Map(
    "Mego" -> 123,
    "Megzz" -> 456
  )

  val option = Some(2)

  val x = 2
  val order = x match
    case 1 => "F"
    case 2 => "S"
    case _ => "NONE"

  val bob = Person("Bob", 22)
  val greeting = bob match
    case Person(n, _) => s"Hi I'm $n"
    case _ => "NONE"
  println(greeting)
}
