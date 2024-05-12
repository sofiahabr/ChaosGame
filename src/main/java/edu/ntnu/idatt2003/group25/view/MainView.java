package edu.ntnu.idatt2003.group25.view;
import edu.ntnu.idatt2003.group25.model.ChaosGameObserver;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView extends Application implements ChaosGameObserver {
  static int height = 900;
  static int width = 1200;
  BorderPane mainPage = new BorderPane();
  FactorialPage factorialPage = new FactorialPage();

  @Override
  public void start(Stage stage)  {
    InitializePage initializePage = new InitializePage();

    HBox topBar = new HBox();
    topBar.setPadding(new Insets(40));
    topBar.setStyle("-fx-background-color: #708090");

    mainPage.setCenter(initializePage.getInitPane());
    mainPage.setTop(topBar);
    Scene scene = new Scene(mainPage, width, height);


    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.show();


  }
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void gameChanged() {

  }
}

