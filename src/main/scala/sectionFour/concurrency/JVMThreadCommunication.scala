package sectionFour.concurrency

object JVMThreadCommunication extends App {
  /*
    the producer-consumer problems
    producer -> [ ? ] -> consumer
    they are working in parallel and don't know when each other go end
   */
  class SimpleContainer {
    private var value: Int = 0

    def isEmpty: Boolean = value == 0

    def set(newValue: Int): Unit = value = newValue

    def get: Int = {
      val result: Int = value
      value = 0
      result
    }
  }

  def naiveProdCons(): Unit = {
    val container = new SimpleContainer

    val consumer = new Thread(() => {
      println("consumer is waiting...")

      while (container.isEmpty) {
        println("Actively waiting")
      }

      println("I have consumed " + container.get)
    })

    val producer = new Thread(() => {
      println("producer computing")

      Thread.sleep(500)

      val value = 42
      println("The value is " + value)

      container.set(value)
    })

    consumer.start()
    producer.start()
  }

//  naiveProdCons()

  // Wait and Notify
  def smartProdCons(): Unit = {
    val container = new SimpleContainer

    val consumer = new Thread(() => {
      println("consumer waiting")
      container.synchronized {
        container.wait()
      }
      println("I've consumed " + container.get)
    })

    val producer = new Thread(() => {
      println("producer is hard worker")
      Thread.sleep(2000)
      val value = 42

      container.synchronized {
        println("I'm producing " + value)
        container.set(value)
        container.notify()
      }
    })

    consumer.start()
    producer.start()
  }
  smartProdCons()
}
