package akkaExamples

import akka.actor.{Actor, ActorSystem, Props}

object ActorCapabilities extends App {
  private class SimpleActor extends Actor {
    override def receive: Receive = {
      case message: String => println(s"I have received a MESSAGE : $message")
      case number: Int => println(s"I have received a NUMBER : $number")
      case SpecialMessage(content) => println(s"I have received a special MESSAGE : $content")
    }
  }

  //1. messages can be of any type

  private val system = ActorSystem("simpleActor")
  val actor = system.actorOf(Props[SimpleActor])
  actor ! "Hii, I am Jitendra."
  actor ! 43
  actor ! SpecialMessage("I am learning Akka from ROCK THE JVM.")

  private case class SpecialMessage(msg: String)
}
