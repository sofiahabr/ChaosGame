package edu.ntnu.idatt2003.group25.view;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainView extends Application  {
  public static int height = 900;
  public static int width = 1200;
  public Scene scene = new Scene(new Pane(), width, height);
  public ScreenController screenController = new ScreenController(scene);
  public InitializePage initializePage = new InitializePage(screenController);


  @Override
  public void start(Stage stage)  {

    screenController.addScreenContent(initializePage, "initialize page");

    screenController.gameChanged( "switch page","initialize page");
    scene.getStylesheets().add("/style/style.css");

    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.show();


  }
  public static void main(String[] args) {
    launch(args);
  }
}

