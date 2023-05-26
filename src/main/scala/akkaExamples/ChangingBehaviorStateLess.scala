package akkaExamples

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ChangingBehaviorStateLess extends App {

  object Mom {
    case class MomStart(kidRef: ActorRef)

    case class Food(food: String)

    case class Ask(message: String)

    val VEGETABLE = "veg"
    val CHOCOLATE = "chocolate"
  }

  class Mom extends Actor {

    import Mom._
    import Kid._

    override def receive: Receive = {
      case MomStart(ref) =>
        ref ! Food(CHOCOLATE)
        ref ! Ask("do you want to play?")
      case KidReject => println("It's ok my kid is sad but healthy.")
      case KidAccept => println("Wow!!! , Let's play!")
    }
  }

  object Kid {
    case object KidReject

    case object KidAccept
  }

  class Kid extends Actor {

    import Mom._
    import Kid._

    override def receive: Receive = happyReceive

    private def happyReceive: Receive = {
      case Food(VEGETABLE) => context.become(sadReceive)
      case Food(CHOCOLATE) =>
      case Ask(_) => sender() ! KidAccept
    }

    private def sadReceive: Receive = {
      case Food(VEGETABLE) => context.become(happyReceive)
      case Food(CHOCOLATE) =>
      case Ask(_) => sender() ! KidReject
    }
  }

  val system = ActorSystem("MomKid")

  val mom = system.actorOf(Props[Mom], "mom")
  val kid = system.actorOf(Props[Kid], "kid")

//  import Mom._
  mom ! Mom.MomStart(kid)

}
