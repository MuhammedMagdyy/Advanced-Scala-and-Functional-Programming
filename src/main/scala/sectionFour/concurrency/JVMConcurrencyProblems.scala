package sectionFour.concurrency

object JVMConcurrencyProblems {
  def runInParallel(): Unit = {
    var x = 0

    val thread1 = new Thread(() => {
      x = 1
    })

    val thread2 = new Thread(() => {
      x = 2
    })

    thread1.start()
    thread2.start()
    println(x) // race condition
  }

  case class BankAccount(var amount: Int)

  def buy(bankAccount: BankAccount, thing: String, price: Int): Unit = {
    bankAccount.amount -= price
  }

  def buySafe(bankAccount: BankAccount, thing: String, price: Int): Unit = {
    bankAccount.synchronized { // Doesn't allow to run multiple threads to run critical section at the same time
      bankAccount.amount -= price // critical section
    }
  }

  def demoBankingProblem(): Unit = {
    (1 to 10000).foreach { _ =>
      val account = new BankAccount(50000)
      val thread1 = new Thread(() => buySafe(account, "shoes", 3000))
      val thread2 = new Thread(() => buySafe(account, "Samsung", 4000))

      thread1.start()
      thread2.start()
      thread1.join()
      thread2.join()

      if (account.amount != 43000) println(s"I've broken tha bank: ${account.amount}")
    }
  }

  def inceptionThread(maxValue: Int, i: Int = 1): Thread = {
    new Thread(() => {
      if (i < maxValue) {
        val newThread = inceptionThread(maxValue, i + 1)
        newThread.start()
        newThread.join()
      }
      println(s"Hello from thread $i")
    })
  }

  def main(args: Array[String]): Unit = {
    inceptionThread(30).start()
  }
}
