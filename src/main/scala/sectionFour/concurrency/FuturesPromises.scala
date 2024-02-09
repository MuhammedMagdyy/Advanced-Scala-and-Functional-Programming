package sectionFour.concurrency

import scala.concurrent.Future
import scala.util.Success
import scala.util.Failure

// important for futures
import scala.concurrent.ExecutionContext.Implicits.global

object FuturesPromises extends App {
  def calculateMeaningOfLife: Int = {
    Thread.sleep(2000)
    42
  }

  val future: Future[Int] = Future {
    calculateMeaningOfLife
  } // (global) which is passed by the compiler
  println(future.value) // Option[Try[Int]]
  println("Waiting future")
  future.onComplete {
    case Success(meaningOfLife) => println(s"Meaning of life $meaningOfLife")
    case Failure(exception) => println(s"I have failed wih $exception")
  }

  Thread.sleep(3000)
}
