package akkaExamples

import akka.actor.{Actor, ActorSystem, Props}

object WordCounter extends App{

  // step 1 : actor system
  val actorSystem = ActorSystem("actorSystem")

  // step 2: creating actor
  private class WordCounter extends Actor {

    private var wordCount = 0
    def receive:  PartialFunction[Any, Unit] = {
      case message: String =>
        println(s"I have received message: $message")
        wordCount += message.split(" ").length
        println(s"total word in the message: $wordCount")
      case m => println(s"I got an unknown message ${m.toString}")
    }
  }

  // step instantiating the actor

  private val wordCounter = actorSystem.actorOf(Props[WordCounter],"wordCounter")

  wordCounter ! "My name is Jitendra and I am learning Akka."

}
