package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.view.menus.AffineConsole;
import edu.ntnu.idatt2003.group25.view.menus.JuliaConsole;
import edu.ntnu.idatt2003.group25.view.menus.SideMenu;
import edu.ntnu.idatt2003.group25.view.menus.TopMenu;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class FactorialPage extends View {

  public BorderPane borderPane = new BorderPane();
  public VBox sidebarMenu;
  public ScreenController screenController;
  public ChaosGameDescription description;
  FactorialPageController controller;
  ChaosGame chaosGame;
  private Canvas pixelCanvas = new Canvas(MainView.width, MainView.height);

  public FactorialPage(ScreenController screenController) {
    this.controller = new FactorialPageController(screenController, this);
    this.screenController = screenController;
    addObserver(controller);
  }

  @Override
  public void setUp() {

    TopMenu topBarMenu = new TopMenu(screenController);
    topBarMenu.setUp();


    SideMenu console;
    if(MainView.description.getTransforms().getFirst().getClass().getName().contains("JuliaTransform")){
      console = new JuliaConsole(screenController, this);
    } else {
      console = new AffineConsole(screenController, this);
    }
    console.setUp();

    borderPane.setTop(topBarMenu.getMenu());
    borderPane.setLeft(console.getMenu());
  }

  @Override
  public BorderPane getPane() {
    return borderPane;
  }

  public void draw(int steps) {

    chaosGame.setDescription(description);
    Canvas pixelCanvas =
        new Canvas(chaosGame.getCanvas().getWidth(), chaosGame.getCanvas().getHeight());

    chaosGame.runSteps(steps);

    int[][] canvas = chaosGame.getCanvas().getCanvasArray();
    GraphicsContext gc = pixelCanvas.getGraphicsContext2D();

    for (int i = 0; i < canvas.length; i++) {
      for (int j = 0; j < canvas[i].length; j++) {
        double number = canvas[i][j] / 10f;
        if ((number) > 0) {
          if ((number) < 1) {
            gc.setFill(Color.color(1, 0, number));
          } else if ((number) < 2) {
            gc.setFill(Color.color(2 - number, 0, 1));
          } else {
            gc.setFill(Color.color(0, 0, 1));
          }
          gc.fillRect(j, i, 1, 1); // Draw a single pixel
        }
      }
    }
    borderPane.setCenter(pixelCanvas);
  }
  public void reset() {
    GraphicsContext gc = pixelCanvas.getGraphicsContext2D();
    gc.clearRect(0, 0, pixelCanvas.getWidth(), pixelCanvas.getHeight());
    borderPane.setCenter(pixelCanvas);
  }
  public ChaosGameDescription getDescription() {
    return description;
  }
  public String getGameType(){
    return gameType;
  }

  public void setGameType(String gameType) {
    this.gameType = gameType;
  }
  public void setDescription(ChaosGameDescription description) {
    System.out.println("im here");
    this.description = description;

  }
}
