package hello.world

import hello.world.models.{Application, ApplicationContainer}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import scala.scalajs.LinkingInfo
import slinky.core._
import slinky.web.ReactDOM
import slinky.hot
import org.scalajs.dom
import slinky.core.facade.ReactElement

@JSImport("resources/index.css", JSImport.Default)
@js.native
object IndexCSS extends js.Object

object Main {
  val css = IndexCSS


  //var app: slinky.core.KeyAndRefAddingStage[hello.world.App] = null


  @JSExportTopLevel("main")
  def main(): Unit = {
    if (LinkingInfo.developmentMode) {
      hot.initialize()
    }

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }
    ReactDOM.render(App(), container)
  }
}
