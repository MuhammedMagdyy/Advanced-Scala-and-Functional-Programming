package sectionFive.implicits

import java.util.Date

object TypeClasses extends App {
  /* PART 1 */
  /*
    - This only works for types we write only
    - ONE implementation only
   */
  trait HTMLWritable {
    def toHtml: String
  }

  case class User(name: String, age: Int, email: String) extends HTMLWritable {
    override def toHtml: String = s"<div> $name ($age yo) <a href=$email /> </div>"
  }

  println(User("Mohamed", 23, "Mohamedmagdy@gmail.com").toHtml)

  /* ------------------- */
  /*
    - Lose type safety (value can be anything)
    - We need to modify code every time we add new thing
    - Still one implementation
   */
  object HTMLSerializerPM {
    def serializeToHtml(value: Any): Unit = value match
      case User(n, a, e) =>
      case _ =>
  }

  /* ------------------- */
  /*
    - We can define serializers for other types
    - We can define multiple serializers
    - Type Class
   */
  trait MyClassTemplate[T] {
    def action(value: T): String
  }

  object MyClassTemplate {
    def apply[T](implicit instance: MyClassTemplate[T]): TypeClasses.MyClassTemplate[T] = instance
  }

  trait HTMLSerializer[T] {
    def serialize(value: T): String
  }

  object UserSerializer extends HTMLSerializer[User] {
    override def serialize(user: User): String = s"<div> ${user.name} (${user.age} yo) <a href=${user.email} /> </div>"
  }

  object DateSerializer extends HTMLSerializer[Date] {
    override def serialize(date: Date): String = s"<div> ${date.toString} </div>"
  }

  object PartialUserSerializer extends HTMLSerializer[User] {
    override def serialize(user: User): String = s"<div> Hi, I'm ${user.name} </div>"
  }

  val megzz = User("Mohamed", 23, "mego@gmail.com")
  println(UserSerializer.serialize(megzz))

  val date = new Date()
  println(DateSerializer.serialize(date))

  val mego = User("Mego", 22, "mo@gmail.com")
  println(PartialUserSerializer.serialize(mego))
  /* ------------------- */

  /*
      Exercise
   */
  trait Equality[T] {
    def compare(a: T, b: T): Boolean
  }

  object CompareNameSerializer extends Equality[User] {
    override def compare(a: User, b: User): Boolean = a.name == b.name
  }

  object CompareNameEmailSerializer extends Equality[User] {
    override def compare(a: User, b: User): Boolean = a.name == b.name && a.email == b.email
  }

  /* ------------------- */
  /* PART 2 */
  object HTMLSerializer {
    def serialize[T](value: T)(implicit serializer: HTMLSerializer[T]): String =
      serializer.serialize(value)

    def apply[T](implicit serializer: HTMLSerializer[T]): TypeClasses.HTMLSerializer[T] = serializer
  }

  implicit object IntToHTML extends HTMLSerializer[Int] {
    override def serialize(value: Int): String = s"<div> $value </div>"
  }

  println(HTMLSerializer.serialize(42))
  println(HTMLSerializer[Int].serialize(50)) // better
}
