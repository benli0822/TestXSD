package Test.ScalaFX

/**
 * Created by benli on 25/01/14.
 * Copyright 2013 ScalaFX Project
 * All right reserved.
 */

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.Includes._
import scalafx.geometry.{HPos, Pos, Insets}
import scalafx.scene.control.{Button, TextField, Label, Separator}
import scalafx.scene.layout.ColumnConstraints
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.Priority
import scalafx.scene.layout.RowConstraints
import scalafx.scene.layout.VBox

/**
 * An example of a GridPane layout. There is more than one approach to using a
 * GridPane. First, the code can specify which rows and/or columns should
 * contain the content. Second, the code can alter the constraints of the
 * rows and/or columns themselves, either by specifying the preferred minimum
 * or  maximum heights or widths, or by specifying the percentage of the
 * GridPane that belongs to certain rows or columns.
 *
 * @see scalafx.scene.layout.GridPane
 */
object GridPaneSample extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "Grid Pane Example"
    scene = new Scene {
      root = {
        // grid1 places the children by specifying the rows and columns in GridPane.setConstraints()
        val grid1Caption = new Label {
          text = "The example below shows GridPane content placement by specifying rows and columns:"
          wrapText = true
        }

        val label1 = new Label("Name:") {
          style = "-fx-font-weight:bold"
          alignmentInParent = Pos.BASELINE_RIGHT
        }
        GridPane.setConstraints(label1, 0, 0, 1, 1)

        val label11 = new Label("John Q. Public") {
          alignmentInParent = Pos.BASELINE_LEFT
        }
        GridPane.setConstraints(label11, 1, 0, 2, 1)

        val label21 = new Label("Address:") {
          style = "-fx-font-weight:bold"
          alignmentInParent = Pos.BASELINE_RIGHT
        }
        GridPane.setConstraints(label21, 0, 1, 1, 1)

        val label22 = new Label("12345 Main Street, Some City, CA") {
          alignmentInParent = Pos.BASELINE_LEFT
        }
        GridPane.setConstraints(label22, 1, 1, 5, 1)

        val grid1 = new GridPane {
          hgap = 4
          vgap = 6
          margin = Insets(18)
          children ++= Seq(label1, label11, label21, label22)
        }

        // grid2 places the child by influencing the rows and columns themselves
        // via GridRowInfo and GridColumnInfo. This grid uses the preferred
        // width/height and max/min width/height.
        val grid2Caption = new Label {
          text = "The example below shows GridPane content placement by influencing the rows and columns themselves."
          wrapText = true
        }

        val category = new Label {
          text = "Category:"
          style = "-fx-font-weight:bold"
          alignmentInParent = Pos.BASELINE_RIGHT
        }
        GridPane.setConstraints(category, 0, 0)

        val categoryValue = new Label {
          text = "Wines"
          alignmentInParent = Pos.BASELINE_LEFT
        }
        GridPane.setConstraints(categoryValue, 1, 0)

        val company = new Label {
          text = "Company:"
          style = "-fx-font-weight:bold"
          alignmentInParent = Pos.BASELINE_RIGHT
        }
        GridPane.setConstraints(company, 0, 1)

        val companyValue = new Label {
          text = "Acme Winery"
          alignmentInParent = Pos.BASELINE_LEFT
        }
        GridPane.setConstraints(companyValue, 1, 1)

        val rating = new Label {
          text = "Rating:"
          style = "-fx-font-weight:bold"
          alignmentInParent = Pos.BASELINE_RIGHT
        }
        GridPane.setConstraints(rating, 0, 2)

        val ratingValue = new Label {
          text = "Excellent"
          alignmentInParent = Pos.BASELINE_LEFT
        }
        GridPane.setConstraints(ratingValue, 1, 2)

        //        val imageView = new ImageView {
        //          image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
        //          alignmentInParent = Pos.CENTER
        //        }
        //        GridPane.setConstraints(imageView, 2, 1)

        val rowInfo = new RowConstraints(minHeight = 50, prefHeight = 50, maxHeight = 50)
        val colInfo = new ColumnConstraints(minWidth = 140, prefWidth = 140, maxWidth = 140)

        val grid2 = new GridPane {
          padding = Insets(18)
          for (i <- 0 until 2) {
            rowConstraints.add(rowInfo)
            columnConstraints.add(colInfo)
          }
          children ++= Seq(category, categoryValue, company, companyValue, /*imageView,*/ rating, ratingValue)
        }

        // grid3 places the child by influencing the rows and columns
        // via GridRowInfo and GridColumnInfo. This grid uses the percentages
        val grid3Caption = new Label {
          text = "The example below shows GridPane content placement by " +
            "influencing row and column percentages.  " +
            "Also, grid lines are made visible in this example.  " +
            "The lines can be helpful in debugging."
          wrapText = true
        }

        val grid3 = new GridPane {
          padding = Insets(18)
          gridLinesVisible = true
          content ++= Seq(grid3Caption)
          val rowConstr50Perc = new RowConstraints {
            percentHeight = 50
          }
          val colConstr25Perc = new ColumnConstraints {
            percentWidth = 25
          }
          val colConstr50Perc = new ColumnConstraints {
            percentWidth = 50
          }
          // 2*50 percent
          rowConstraints ++= Seq(rowConstr50Perc, rowConstr50Perc)
          // 25 percent
          columnConstraints += colConstr25Perc
          // 50 percent
          columnConstraints += colConstr50Perc
          // 25 percent
          columnConstraints += colConstr25Perc
        }

        val condLabel = new Label(" Member Name:")
        GridPane.setHalignment(condLabel, HPos.RIGHT)
        GridPane.setConstraints(condLabel, 0, 0)
        val condValue = new Label("MyName")
        GridPane.setMargin(condValue, Insets(0, 0, 0, 10))
        GridPane.setConstraints(condValue, 1, 0)

        val acctLabel = new Label("Member Number:")
        GridPane.setHalignment(acctLabel, HPos.RIGHT)
        GridPane.setConstraints(acctLabel, 0, 1)
        val textBox = new TextField {
          text = "Your number"
        }
        GridPane.setMargin(textBox, Insets(10, 10, 10, 10))
        GridPane.setConstraints(textBox, 1, 1)

        val button = new Button("Help")
        GridPane.setConstraints(button, 2, 1)
        GridPane.setMargin(button, Insets(10, 10, 10, 10))
        GridPane.setHalignment(button, HPos.CENTER)

        GridPane.setConstraints(condValue, 1, 0)
        grid3.children ++= Seq(condLabel, condValue, button, acctLabel, textBox)

        new VBox {
          vgrow = Priority.ALWAYS
          hgrow = Priority.ALWAYS
          spacing = 10
          padding = Insets(20)
          content = List(
            new VBox {
              content = List(grid1Caption, grid1)
            },
            new Separator(),
            new VBox {
              content = List(grid2Caption, grid2)
            },
            new Separator(),
            new VBox {
              content = List(grid3Caption, grid3)
            }
          )
        }
      }
    }
  }
}