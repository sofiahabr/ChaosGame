package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.MainView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FactorialPageController extends Controller {
  ChaosGame chaosGame =
      new ChaosGame(MainView.description, Math.round(MainView.width*0.7f), Math.round(MainView.height*0.7f));

  public FactorialPageController() {
  }
  @Override
  public void gameChanged(String steps) {
    int stepNr = Integer.parseInt(steps);
    chaosGame.runSteps(stepNr);

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
    FactorialPage factorialPage = new FactorialPage();
    factorialPage.getPane().setCenter(gc.getCanvas());

    MainView.mainPage.setCenter(factorialPage.getPane());
  }
}