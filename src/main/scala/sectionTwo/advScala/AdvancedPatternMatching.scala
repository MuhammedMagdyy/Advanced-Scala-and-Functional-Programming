package sectionTwo.advScala

object AdvancedPatternMatching extends App {
  val numbers = List(1)
  val description = numbers match
    case head :: Nil => println(s"The only $head")
    case _ => println("Nothing")

  /*
    If you want to make pattern matching on class, make a singleton object with the same name and do it
   */
  class Person(val name: String, val age: Int)

  object Person {
    def unapply(person: Person): Option[(String, Int)] =
      if (person.age < 21) None
      else Some((person.name, person.age))

    def unapply(age: Int): Option[String] =
      Some(if (age < 21) "minor" else "major")
  }

  val megzz = new Person("Megzz", 22)
  val greeting = megzz match
    case Person(n, a) => s"I'm $n and I'm $a yo"

  println(greeting)

  val legalStatus = megzz.age match
    case Person(status) => s"my legal status is $status"

  println(legalStatus)

  // infix patterns
  case class Or[A, B](a: A, b: B)

  val either = Or(2, "two")
  val humanDesc = either match
    case number Or string => s"$number is written in $string"

  println(humanDesc)

  // decomposing sequences
  val varArgs = numbers match
    case List(1, _*) => "stating with 1"

  abstract class MyList[+A] {
    def head: A = ???

    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]

  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val decomposed = myList match
    case MyList(1, 2, _*) => "1 and 2"
    case _ => "else"

  println(decomposed)

  // custom return types for unapply
  // isEmpty: Boolean, get: something

  abstract class Wrapper[T] {
    def isEmpty: Boolean

    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String]:
      override def isEmpty: Boolean = false

      override def get: String = person.name
  }

  println(megzz match
    case PersonWrapper(n) => s"Name is $n"
    case _ => "Nooooo humans here"
  )
}



