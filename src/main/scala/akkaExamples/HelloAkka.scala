package akkaExamples

import akka.actor.{Actor, ActorSystem, Props}
import org.slf4j.LoggerFactory
class HelloAkka extends Actor {
  def receive: Receive = {
    case str: String => println(str)
    case _ => println("Unknown message")
  }
}

object Main extends App {
  private val logger = LoggerFactory.getLogger("")
  private val actorSystem  = ActorSystem("ActorSystem")
  private val actor = actorSystem.actorOf(Props[HelloAkka], "HelloAkka")
  logger.info("Akka actor working!")
  actor!"Hello Akka"
  actor!200

  Thread.sleep(2000)
  System.exit(0)
}