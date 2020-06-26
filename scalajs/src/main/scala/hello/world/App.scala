package hello.world

import hello.world.messaging.MessageHandler
import hello.world.messaging.ApplicationMessageListContainer.{IncrementCounter1, IncrementCounter2}
import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

object ApplicationProxy {
  var update:() => Unit = () => ()
}

@react class App extends StatelessComponent {
  type Props = Unit
  private val css = AppCSS
  override def componentWillMount() = {
    ApplicationProxy.update = () => {
      this.forceUpdate()
    }
  }
  def render() = {

    div(className := "App")(
      header(className := "App-header")(
        img(src := ReactLogo.asInstanceOf[String], className := "App-logo", alt := "logo"),
        h1(className := "App-title")("Welcome to React (with Scala.js!)")
      ),
      p(className := "App-intro")(
        "To get started, edit ", code("App.scala"), " and save to reload."
      ),
      button(
        s"Click to increment counter1 ${models.topModel.app.counter1.value}",
        onClick := (_ => {
          MessageHandler.actor ! IncrementCounter1
        })
      ),
      br(),br(),
      button(
        s"Click to increment counter2 ${models.topModel.app.counter2.value}",
        onClick := (_ => {
          MessageHandler.actor ! IncrementCounter2
        })
      )
    )
  }
}
