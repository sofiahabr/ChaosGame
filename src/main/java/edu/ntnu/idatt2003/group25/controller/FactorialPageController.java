package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FactorialPageController {
  FactorialPage factorialPage = new FactorialPage();
  public FactorialPageController() {

  }
  public static void clear() {
  }
  public void playGame(int steps){
    factorialPage.getChaosGame().runSteps(steps);
    draw(factorialPage.getChaosGame());
  }
  public void draw(ChaosGame chaosGame){
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
    factorialPage.borderPane.setCenter(pixelCanvas);
  }
}
