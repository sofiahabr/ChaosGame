package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainLogic extends View{
  public static int height = 700;
  public static int width = 1200;
  public static BorderPane mainPage = new BorderPane();
  public static ChaosGameDescription description = ChaosGameDescriptionFactory.createBarnsleyFern();
  public Scene scene = new Scene(new Pane(), width, height);
  public ScreenController screenController = new ScreenController(scene);
  public InitializePage initializePage = new InitializePage(screenController);
  public FactorialPage factorialPage = new FactorialPage(screenController);

  public void start(Stage stage) {
//    screenController.addScreenContent(factorialPage, "factorial page");
//    screenController.addScreenContent(initializePage, "initialize page");
//
//    screenController.gameChanged("switch page", "initialize page");
//    scene.getStylesheets().add("/style.css");
//
//    screenController.gameChanged("switch page", "initialize page");

    screenController.addScreenContent(initializePage, "initialize page");

    screenController.gameChanged( "switch page","initialize page");
    scene.getStylesheets().add("/style/style.css");

    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.show();

    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.setMinHeight(500);
    stage.setMinWidth(800);
    stage.show();

    FactorialPageController factorialPageController = new FactorialPageController(screenController, factorialPage);

    addObserver(factorialPageController);

    scene.widthProperty().addListener((observable, oldValue, newValue)-> {
      updateObserver("sceneChange", "0:" + newValue.toString());
      MainLogic.width = newValue.intValue();
    });

    scene.heightProperty().addListener((observable, oldValue, newValue)-> {
      updateObserver("sceneChange", newValue.toString() + ":0");
      MainLogic.height = newValue.intValue();
    });
  }

  @Override
  public BorderPane getPane() {
    return null;
  }

  @Override
  public void setUp() {
  }

  @Override
  public void update() {

  }
}
