package sectionFour.concurrency

import java.util.concurrent.Executors

object JVMConcurrencyIntro extends App {
  /*
    in Java
      interface Runnable {
          public void run()
      }
   */
  // JVM threads
  val runnable = new Runnable:
    override def run(): Unit = println("Running in parallel")
  val thread = new Thread(runnable)
  // run it as parallel
  thread.start() // creates a JVM thread => OS thread (gives a signal to JVM to start a thread)
  runnable.run() // no parallel here (above one is parallel)
  thread.join() // blocks until thread finishes running

  val helloThread = new Thread(() => (1 to 5).foreach(_ => println("Hello world")))
  val goodbyeThread = new Thread(() => (1 to 5).foreach(_ => println("Good Bye")))

  helloThread.start()
  goodbyeThread.start()

  val pool = Executors.newFixedThreadPool(10)
  pool.execute(() => println("Something executes in pool"))

  pool execute (() => {
    Thread.sleep(1000)
    println("Done after 1 second")
  })

  pool execute (() => {
    Thread.sleep(1000)
    println("almost done")
    Thread.sleep(1000)
    println("Done after 2 seconds")
  })

  // pool.shutdown() // This shutdown pool (no more actions)
  // pool execute(() => println("Something will not happen")) // throws an exception
  // pool.shutdownNow() // any thread will throws an exception
  println(pool.isShutdown) // true (cuz above -remove comment-)
}
