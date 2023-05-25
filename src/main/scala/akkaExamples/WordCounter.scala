package akkaExamples

import akka.actor.{Actor, ActorSystem, Props}

object WordCounter extends App {

  // step 1 : actor system
  val actorSystem = ActorSystem("actorSystem")

  // step 2: creating actor
  private class WordCounter extends Actor {

    // internal data
    private var wordCount = 0

    // behavior
    def receive: PartialFunction[Any, Unit] = {
      case message: String =>
        println(s"I have received message: $message")
        wordCount += message.split(" ").length
        println(s"total word in the message: $wordCount")
      case m => println(s"I got an unknown message ${m.toString}")
    }
  }

  // step instantiating the actor

  private val wordCounter = actorSystem.actorOf(Props[WordCounter], "wordCounter")

  wordCounter ! "My name is Jitendra and I am learning Akka."


  // actor with constructor arguments


  private object Person {
    def props(name: String): Props = Props(new Person(name))
  }

  private class Person(name: String) extends Actor {
    override def receive: Receive = {
      case "hi" => println(s"Hi, my name is $name .")
      case _ => println("Undefined message.")
    }
  }

  // creating actor with constructor arguments in this is not recommended.
  //  private val person = actorSystem.actorOf(Props(new Person("Jitendra")))

  // best practice to do above is by creating the companion object
  private val person = actorSystem.actorOf(Person.props("Jitendra"))
  person ! "hi"

}
