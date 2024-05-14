package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.MainView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FactorialPageController extends Controller {
  ChaosGame chaosGame = new ChaosGame(MainView.description, Math.round(MainView.width*0.7f), Math.round(MainView.height));
  ScreenController screenController;
  int steps = 0;
  Vector2D min;
  Vector2D max;
  Vector2D vector2D;
  Complex complex;
  Matrix2x2 matrix;
  Canvas pixelCanvas = new Canvas(chaosGame.getCanvas().getWidth(), chaosGame.getCanvas().getHeight());

  public FactorialPageController(ScreenController screenController) {
    this.screenController = screenController;

  }
  @Override
  public void gameChanged(String action, String info) {
    switch (action) {
      case "button clicked":
        switch (info) {
          case "play":
            this.chaosGame = new ChaosGame(MainView.description, Math.round(MainView.width * 0.7f),
                Math.round(MainView.height * 0.7f));
            chaosGame.runSteps(steps);
            draw();
            break;
          case "add":
            if (MainView.description.getTransforms().getFirst().getClass().getName()
                .contains("Julia")) {
              complex = new Complex(vector2D.getX0(), vector2D.getX1());
              MainView.description.getTransforms().add(new JuliaTransform(complex, 1));
            } else {
              MainView.description.getTransforms().add(new AffineTransform2D(matrix, vector2D));
            }
            break;
          case "reset":
            clear();
            break;
          case "save":
            ChaosGameFileHandler fileHandler =
                new ChaosGameFileHandler(new ArrayList<>(), new Vector2D(0, 0), new Vector2D(1, 1));
            fileHandler.writeToFile(MainView.description, "src/main/resources/TestSave.txt");
            break;
        }
      case "vector input":
        vector2D = registerVector2D(info);
        break;
      case "min input":
        min = registerVector2D(info);
        break;
      case "max input":
        max = registerVector2D(info);
        break;
      case "steps input":
        steps = registerInt(info);
        break;
      case "matrix input":
        matrix = registerMatrix(info);
    }
  }

  public void draw(){
    int[][] canvas = chaosGame.getCanvas().getCanvasArray();
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
    screenController.getScreenContent("factorial page").getPane().setCenter(pixelCanvas);

    MainView.mainPage.setCenter(factorialPage.getPane());
  }
}