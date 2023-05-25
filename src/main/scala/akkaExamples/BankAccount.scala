package akkaExamples

import akka.actor.{Actor, ActorRef, ActorSystem, Props, actorRef2Scala}

object BankAccount extends App {

  private object BankAccountActor {
    case class Deposit(amount: Int)

    case class Withdraw(amount: Int)

    case object Statement

    case class TransactionSuccess(message: String)

    case class TransactionFailure(reason: String)
  }

  private class BankAccountActor extends Actor {

    import BankAccountActor._

    private var balance = 0

    override def receive: Receive = {
      case Deposit(amount) =>
        if (amount < 0) sender() ! TransactionFailure("Invalid deposit amount!")
        else {
          balance += amount
          sender() ! TransactionSuccess(s"Transaction successful, Deposited $amount")
        }
      case Withdraw(amount) =>
        if (amount > balance) sender() ! TransactionFailure("Insufficient balance!")
        else if (amount < 0) sender() ! TransactionFailure("Invalid withdraw amount!")
        else {
          balance -= amount
          sender() ! TransactionSuccess(s"Transaction successful, withdrawn $amount")
        }
      case Statement => sender() ! s"Current balance : $balance"
    }
  }

  object Person {
    case class BankTransaction(account: ActorRef)
  }
  class Person extends  Actor {
    import BankAccountActor._
    import Person._
    override def receive: Receive = {
      case BankTransaction(account) =>
        account ! Deposit(10000)
        account ! Withdraw(-10000)
        account ! Withdraw(2000)
        account ! Statement
      case message => println(message.toString)
    }
  }

  val system = ActorSystem("bankAccount")

  import BankAccountActor._
  import Person._

  val account = system.actorOf(Props[BankAccountActor], "account")
  val person = system.actorOf(Props[Person], "jeet")

  person ! BankTransaction(account)




}
