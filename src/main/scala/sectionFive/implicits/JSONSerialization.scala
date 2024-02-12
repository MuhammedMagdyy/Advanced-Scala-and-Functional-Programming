package sectionFive.implicits

import java.util.Date

object JSONSerialization extends App {
  case class User(name: String, age: Int, email: String)

  case class Post(content: String, createdAt: Date)

  case class Feed(user: User, posts: List[Post])

  sealed trait JSONValue {
    def stringify: String
  }

  final case class JSONString(value: String) extends JSONValue {
    def stringify: String = "\"" + value + "\""
  }

  final case class JSONNumber(value: Int) extends JSONValue {
    def stringify: String = value.toString
  }

  final case class JSONArray(values: List[JSONValue]) extends JSONValue {
    def stringify: String = values.map(_.stringify).mkString("[", ",", "]")
  }

  final case class JSONObject(values: Map[String, JSONValue]) extends JSONValue {
    def stringify: String = values.map {
      case (key, value) => "\"" + key + "\":" + value.stringify
    }.mkString("{", ",", "}")
  }

  // Testing API
  val data = JSONObject(
    Map(
      "user" -> JSONString("Mohamed"),
      "posts" -> JSONArray(
        List(
          JSONString("I hate scala"),
          JSONNumber(123)
        )
      )
    )
  )
  
  println(data.stringify)
}
