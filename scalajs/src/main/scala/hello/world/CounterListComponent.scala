package hello.world


import hello.world.messaging.MessageHandler
import hello.world.messaging.ApplicationMessageListContainer._
import hello.world.models.{Application, Counter}
import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import monocle.{Iso, Lens, Optional, POptional}
import monocle.function.At
import monocle.macros.GenLens
import monocle.function.all._
import monocle.function.At.at
import monocle.std.list._

import scala.collection.SortedMap



@react class CounterListComponent extends StatelessComponent {
  type Props = Optional[Application, Vector[Counter]]
  private val css = AppCSS


  def render() = {

    val counterListLens = props  // for doc purposes

    // our lens gives two way access to Vector[Counter], get and update
    val counterListOption = counterListLens.getOption(models.topModel.app)

    val counterDomElementList = counterListOption match {
      case Some(list) => list.zipWithIndex.map(c => {
        val counterLens: Optional[Application, Counter] = props composeOptional index(c._2)
        div(
          style := js.Dynamic.literal(
            padding = "10px",
          ),
          button(
            s"click here to increment: ${
              c._1.value
            }",
            onClick := (_ => {
              MessageHandler.actor ! IncrementCounter(counterLens)
            })
          )
        )
      }
      )
    }


    div(
      className := "CounterList",
      style := js.Dynamic.literal(
        padding = "0px 150px 0px 150px",
      )
    )(
      div(
        style := js.Dynamic.literal(
          background = "deeppink",
          padding = "10px",
        ),
        counterDomElementList
      )
    )
  }
}
