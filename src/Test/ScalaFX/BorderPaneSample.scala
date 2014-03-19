package Test.ScalaFX

/**
 * Created by benli on 24/01/14.
 * Copyright 2013 ScalaFX Project
 * All right reserved.
 */

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

/** An example of  a BorderPane layout, with placement of children in the top,
  * left, center, right, and bottom positions.
  *
  * @see scalafx.scene.layout.BorderPane
  */
object BorderPaneSample extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "Border Pane Example"
    width = 440
    height = 300
    resizable = false
    scene = new Scene {
      root = {
        // Top content using a rectangle
        val topRectangle = new Rectangle() {
          width = 400
          height = 20
          fill = Color.DARKBLUE
          stroke = Color.BLACK
        }

        // Left content using VBox
        val leftVBox = new VBox {
          spacing = 10
          content = List(Label("Left Hand"), Label("Choice One"), Label("Choice Two"), Label("Choice Three"))
        }

        //        // Center content using Anchor Pane
        //        val centerLabel = Label("We're in the center area.")
        //        val imageButton = new ImageView {
        //          image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
        //        }
        //        AnchorPane.setTopAnchor(centerLabel, 10.0)
        //        AnchorPane.setTopAnchor(imageButton, 40.0)
        //        AnchorPane.setLeftAnchor(centerLabel, 80.0)
        //        AnchorPane.setLeftAnchor(imageButton, 80.0)
        //        val centerAnchorPane = new AnchorPane {
        //          content = List(centerLabel, imageButton)
        //        }

        // Right content using VBox
        val rightVBox = new VBox {
          spacing = 10
          content = List(Label("Right Hand"), Label("Thing A"), Label("Thing B"), Label("Thing C"))
        }

        // Right content
        val bottomLabel = Label("I am a status message. I am at the bottom")

        new BorderPane {
          maxWidth = 400
          maxHeight = 300
          padding = Insets(20)
          top = topRectangle
          left = leftVBox
          //          center = centerAnchorPane
          right = rightVBox
          bottom = bottomLabel
        }
      }
    }
  }
}