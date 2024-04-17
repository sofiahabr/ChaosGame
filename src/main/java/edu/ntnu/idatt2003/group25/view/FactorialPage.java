package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FactorialPage {
  public BorderPane borderPane =  new BorderPane();
  ChaosGameDescription description = ChaosGameDescriptionFactory.createJuliaTransformation(new Complex(-0.74543, 0.11301));
  ChaosGame chaosGame = new ChaosGame(description, Math.round(MainView.width*0.8f), Math.round(MainView.height*0.8f));
  public FactorialPage()  {
    createLayout();
  }
  public BorderPane factorialPane() {
    return borderPane;
  }
  public void draw(){
    int[][] canvas = chaosGame.getCanvas().getCanvasArray();
    Canvas pixelCanvas = new Canvas(chaosGame.getCanvas().getWidth(), chaosGame.getCanvas().getHeight());
    GraphicsContext gc = pixelCanvas.getGraphicsContext2D();
    for (int i = 0; i < canvas.length; i++) {
      for (int j = 0; j < canvas[i].length; j++) {
        double number = canvas[i][j] / 10f;
        if ((number) > 0) {
          if ((number) < 1){
            gc.setFill(Color.color(1,0,number));
          }
          else if ((number) < 2){
            gc.setFill(Color.color(2 - number, 0,1));
          }
          else {
            gc.setFill(Color.color(0,0,1));
          }
          gc.fillRect(j, i, 1, 1); // Draw a single pixel
        }
      }
    }
    borderPane.setCenter(pixelCanvas);
  }

  public void createLayout() {
    borderPane.setStyle("-fx-background-color: white; ");

    Rectangle topBar = new Rectangle(800, 60);
    topBar.setFill(Paint.valueOf("lightblue"));


    Button playButton = new Button("play");
    playButton.setStyle("-fx-background-color: lightblue; -fx-font-size: 20; -fx-min-height: 60; -fx-min-width: 200; -fx-max-width: 200; -fx-max-height: 60; -fx-background-radius: 0");

    Button restartButton = new Button("restart");
    restartButton.setStyle("-fx-background-color: lightblue; -fx-font-size: 20; -fx-min-height: 60; -fx-min-width: 200; -fx-max-width: 200; -fx-max-height: 60; -fx-background-radius: 0");

    playButton.setOnAction(e-> playGame());
    restartButton.setOnAction(e -> {
      chaosGame.getCanvas().clear();
      draw();
    });

    HBox topBox = new HBox(0);
    topBox.getChildren().addAll(topBar,restartButton, playButton);
    borderPane.setTop(topBox);
  }

  public void playGame(){
    chaosGame.runSteps(100000);
    draw();
  }
}
