package sectionFive.implicits

object ImplicitsIntro extends App {
  val pair  = "Mohamed" -> "123"
  
  case class Person(name: String) {
    def greet(): String = s"Hi, My name is $name"
  }
  
  implicit def stringToPerson(str: String): Person = Person(str)
  
  println("Mego".greet()) // println(stringToPerson("Mego").greet())
}
