package hello.world.messaging

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import hello.world.messaging.ApplicationMessageListContainer._
import hello.world.{ApplicationProxy, models}
import hello.world.models.{Application, Counter}
object MessageHandler {

  val system = ActorSystem("ApplicationStoreActorSystem")

  val actor = system.actorOf(Props[ApplicationActor], name = "ApplicationInfoActor")

  class ApplicationActor extends Actor with ActorLogging {
    def receive = {
      case m: ApplicationFrontEndMessage => {
        models.topModel.app = messageUpdate(m)
        ApplicationProxy.update()
      }
    }

    private def messageUpdate: PartialFunction[Any, Application] = {
      //case IncrementCounter1 =>
        //log.info(s"Increment  counter1")
        //models.topModel.app.copy(counter1 = Counter(models.topModel.app.counter1.value + 1))
        //counter1Lens.modify(c => c.copy(c.value+1))(models.topModel.app)

      //case IncrementCounter2 =>
        //log.info(s"Increment counter2")
        //models.topModel.app.copy(counter2 = Counter(models.topModel.app.counter2.value + 1))
        //counter2Lens.modify(c => c.copy(c.value+1))(models.topModel.app)

      case e: IncrementCounter =>
        log.info(s"Increment counter")
        e.lens.modify(c=> c.copy(c.value+1))(models.topModel.app)
    }
  }

}
