package akkaExamples

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ChangingActorsBehavior extends App {

   private object Kid {
    case object KidAccept

    case object KidReject

    private val HAPPY = "happy"
    private val SAD = "sad"

  }

  private class Kid extends Actor {

    import Kid._
    import Mom._

     private var state: String = HAPPY

    override def receive: Receive = {
      case Food(VEGETABLE) => state = SAD
      case Food(CHOCOLATE) => state = HAPPY
      case Ask(_) =>
        if (state == HAPPY) sender() ! KidAccept
        else sender() ! KidReject
    }
  }

  private object Mom {
    case class MomStart(kidRef: ActorRef)

    case class Food(food: String)

    case class Ask(message: String) // do you want to play?

    final val VEGETABLE = "veg"
    final val CHOCOLATE = "chocolate"
  }

  private class Mom extends Actor {

    import Kid._
    import Mom._

    override def receive: Receive = {
      case MomStart(ref) =>
        ref ! Food(CHOCOLATE)
        ref ! Ask("do you want to play?")
        ref ! Food(VEGETABLE)
        ref ! Ask("do you want to play?")
      case KidAccept => println("Let's play.")
      case KidReject => println("Kid does not want to play.")
    }
  }


  val system = ActorSystem("KidMom")

  import Mom._

  private val mom = system.actorOf(Props[Mom], "mom")
  private val kid = system.actorOf(Props[Kid], "kid")

  mom ! MomStart(kid)
}
