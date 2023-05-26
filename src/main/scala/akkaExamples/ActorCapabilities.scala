package akkaExamples

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorCapabilities extends App {
  private class SimpleActor extends Actor {
    override def receive: Receive = {
      case "Hii" => sender() ! "Hello, how are you?"
      case message: String => println(s" $self I have received a MESSAGE : $message")
      case number: Int => println(s"I have received a NUMBER : $number")
      case SpecialMessage(content) => println(s"I have received a special MESSAGE : $content")
      case SendMessageToYourSelf(content) => self ! content
      case SayHiTo(ref) => ref ! "Hii"
      case ForwardMessage(msg, ref) => ref forward msg // I keep the original sender of the message
    }
  }

  //1. messages can be of any type

  private val system = ActorSystem("simpleActor")
  val actor = system.actorOf(Props[SimpleActor])
  actor ! "Hii, I am Jitendra."
  actor ! 43
  actor ! SpecialMessage("I am learning Akka from ROCK THE JVM.")

  private case class SpecialMessage(msg: String)


  // 2. Actors have information about the context and themselves

  private case class SendMessageToYourSelf(message: String)

  actor ! SendMessageToYourSelf("I am an actor and i am proud of it.")

  // 3. actor can REPLY to message

  private val jeet = system.actorOf(Props[SimpleActor], "jeet")
  private val manish = system.actorOf(Props[SimpleActor], "manish")

  private case class SayHiTo(ref: ActorRef)

  manish ! SayHiTo(jeet)

  // 4. dead letters (The messages which are not delivered to any actors, are goes to the DEAD LETTERS)
  // dead letters is a fake actor in Akka.
  manish ! "Hii"

  // 5. forwarding the message (by keeping the original sender reference)
  private case class ForwardMessage(message: String, ref: ActorRef)

  manish ! ForwardMessage("hello", jeet)

}
