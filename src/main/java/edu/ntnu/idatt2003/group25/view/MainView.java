package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.model.ChaosCanvas;
import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainView extends Application {
  static int height = 900;
  static int width = 1200;

  @Override
  public void start(Stage stage) throws Exception {
    FactorialPage factorialPage = new FactorialPage();
    InitializePage initializePage = new InitializePage();


    BorderPane mainPage = new BorderPane();

    mainPage.setCenter(factorialPage.factorialPane());
    Scene scene = new Scene(mainPage, width, height);


    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.show();


  }
  public static void main(String[] args) {
    launch(args);
  }
}
