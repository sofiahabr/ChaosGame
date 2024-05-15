package edu.ntnu.idatt2003.group25.view;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainView extends Application  {
  public static int height = 900;
  public static int width = 1200;
  public static BorderPane mainPage = new BorderPane();
  public static ChaosGameDescription description = ChaosGameDescriptionFactory.createBarnsleyFern();


  public Scene scene = new Scene(new Pane(), width, height);
  public ScreenController screenController = new ScreenController(scene);
  public InitializePage initializePage = new InitializePage(screenController);
  public FactorialPage factorialPage = new FactorialPage(screenController);


  @Override
  public void start(Stage stage)  {

    screenController.addScreenContent(factorialPage, "factorial page");
    screenController.addScreenContent(initializePage, "initialize page");

    screenController.gameChanged( "switch page","initialize page");
    scene.getStylesheets().add("/style.css");

    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.show();


  }
  public static void main(String[] args) {
    launch(args);
  }
}

