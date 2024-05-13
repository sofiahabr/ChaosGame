package edu.ntnu.idatt2003.group25.view;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView extends Application  {
  public static int height = 900;
  public static int width = 1200;
  public static BorderPane mainPage = new BorderPane();
  public static ChaosGameDescription description = ChaosGameDescriptionFactory.createBarnsleyFern();

  public InitializePage initializePage = new InitializePage();


  @Override
  public void start(Stage stage)  {

    HBox topBar = new HBox();
    topBar.setPadding(new Insets(40));
    topBar.setStyle("-fx-background-color: #708090");

    mainPage.setCenter(initializePage.getPane());
    mainPage.setTop(topBar);
    Scene scene = new Scene(mainPage, width, height);


    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.show();


  }
  public static void main(String[] args) {
    launch(args);
  }
}

