package hello.world.messaging

import hello.world.models.{Application, Counter}
import monocle.Optional

trait ApplicationFrontEndMessage

object ApplicationMessageListContainer {
  //object IncrementCounter1 extends ApplicationFrontEndMessage
  //object IncrementCounter2 extends ApplicationFrontEndMessage

  case class IncrementCounter(lens:Optional[Application, Counter]) extends ApplicationFrontEndMessage
}
