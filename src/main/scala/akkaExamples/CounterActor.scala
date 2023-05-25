package akkaExamples

import akka.actor.{Actor, ActorSystem, Props}

object CounterActor extends App {

  private class Counter extends Actor {
    private var count = 0
    override def receive: Receive = {
      case "Increment" => count += 1
      case "Decrement" => count -= 1
      case "Print" => println(s"Current value of the COUNTER : $count")
    }
  }

  val system = ActorSystem("counterActor")
  private val counter = system.actorOf(Props[Counter], "counter")
  counter ! "Increment"
  counter ! "Increment"
  counter ! "Increment"
  counter ! "Increment"
  counter ! "Decrement"
  counter ! "Print"
}
