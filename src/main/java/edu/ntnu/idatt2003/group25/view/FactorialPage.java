package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class FactorialPage extends View {
  public BorderPane factorialPane = new BorderPane();

  public FactorialPage(){
    addObserver(new FactorialPageController());
    setUp();
  }
  @Override
  public  BorderPane getPane() {
    return factorialPane;
  }
  @Override
  public void setUp() {
    VBox sidebarMenu = new VBox(40);
    sidebarMenu.setPadding(new Insets(60));

    Text stepsLabel = new Text("Choose steps");
    stepsLabel.setStyle("-fx-font-size: 14");

    TextField steps = new TextField();
    steps.setPromptText("Ex. 500...");
    steps.setMinWidth(180);
    steps.setMinHeight(40);
    steps.setStyle("-fx-font-size: 12");

    Button startButton = new Button("Start");
    startButton.setOnAction(e-> {
      updateObserver(steps.getText());
    });

    sidebarMenu.setStyle("-fx-background-color: #D9D9D9; ");

    sidebarMenu.getChildren().addAll(stepsLabel, steps, startButton);
    sidebarMenu.setAlignment(Pos.TOP_CENTER);
    factorialPane.setLeft(sidebarMenu);
  }
}