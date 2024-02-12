package sectionFive.implicits

object OrganizingImplicits extends App {
  /*
      Implicits (use as implicit parameter):
      - val/var
      - objects
      - accessor methods = defs with no parenthesis
   */

  /*
      Implicits scope
      - Normal scope = Local scope
      - Imported scope
   */

  // this overrides ordering implicit
  implicit val reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  println(List(1, 2, 6, 4).sorted)


  case class Person(name: String, age: Int)

  val persons = List(
    Person("Mohamed", 20),
    Person("Ahmed", 30)
  )

  object SortByName {
    implicit val sortByName: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
  }

  object SortByAge {
    implicit val sortByAge: Ordering[Person] = Ordering.fromLessThan((a, b) => a.age < b.age)
  }

  // If you want to use any implicit injected into objects just import like this

  import SortByAge._

  println(persons.sorted)

  case class Purchase(nUnits: Int, unitPrice: Double)


  object Purchase {
    implicit val sortByTotalPrice: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.nUnits * a.unitPrice < b.nUnits * b.unitPrice)
  }

  object SortByUnitCount {
    implicit val sortByUnitCount: Ordering[Purchase] = Ordering.fromLessThan(_.nUnits < _.nUnits)
  }

  object SortByUnitPrice {
    implicit val sortByUnitPrice: Ordering[Purchase] = Ordering.fromLessThan(_.unitPrice < _.unitPrice)
  }
}
