package Test.Swing

/**
 * Created by benli on 24/01/14.
 */
// In file swing/FirstSwingApp.scala

import scala.swing._

object FirstSwingApp extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "First Swing App"
    contents = new Button {
      text = "Click me"
    }
  }
}
