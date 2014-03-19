package Test.UI

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{CheckMenuItem, Menu, MenuBar, MenuItem}
import scalafx.scene.layout.BorderPane

/**
 * Created by benli on 25/01/14.
 */
object Container extends JFXApp {
  val fooMenuItem = new MenuItem("foo")

  stage = new JFXApp.PrimaryStage {
    title = "HTML5 Presentation Maker"
    width = 800
    height = 500
    scene = new Scene {
      root = new BorderPane {
        top = new MenuBar {
          useSystemMenuBar = false
          menus = List(
            new Menu("File") {
              items = List(
                new Menu("Open") {
                  items = List(
                    new MenuItem("Object Oriented"),
                    new MenuItem("Functional"),
                    fooMenuItem,
                    new CheckMenuItem( """Show "foo" item""") {
                      selected = true
                      selected.onInvalidate {
                        fooMenuItem.setVisible(selected())
                        println( """Menu item "foo" is now """ + (if (fooMenuItem.visible()) "" else "not") + " visible")
                      }
                    }
                  )
                },
                new MenuItem("ScalaFX")
              )
            })
        }
      }
    }
  }
}
