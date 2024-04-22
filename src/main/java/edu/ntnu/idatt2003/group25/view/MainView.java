package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.model.ChaosGameObserver;
import edu.ntnu.idatt2003.group25.model.Complex;
import java.util.Observer;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainView extends Application implements ChaosGameObserver {
  static int height = 900;
  static int width = 1200;
  BorderPane mainPage = new BorderPane();
  FactorialPage factorialPage = new FactorialPage();

  @Override
  public void start(Stage stage)  {
//    InitializePage initializePage = new InitializePage();

    HBox topBar = new HBox();
    topBar.setPadding(new Insets(40));
    topBar.setStyle("-fx-background-color: #708090");

    mainPage.setCenter(factorialPage.factorialPane());
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

