package akkaExamples

import akka.actor.{Actor, ActorSystem, Props}

object CounterActor extends App {

  private object Counter {
    case object Increment

    case object Decrement

    case object Print
  }

  private class Counter extends Actor {

    import Counter._

    private var count = 0

    override def receive: Receive = {
      case Increment => count += 1
      case Decrement => count -= 1
      case Print => println(s"Current value of the COUNTER : $count")
    }
  }

  val system = ActorSystem("counterActor")
  private val counter = system.actorOf(Props[Counter], "counter")

  import Counter._

  (1 to 10).foreach(_ => counter ! Increment)
  (1 to 4).foreach(_ => counter ! Decrement)
  counter ! Print
}
