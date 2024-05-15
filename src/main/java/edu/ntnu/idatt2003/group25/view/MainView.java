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

private final MainLogic mainLogic = new MainLogic();

  @Override
  public void start(Stage stage)  {
    mainLogic.start(stage);

  }

  public static void main(String[] args) {
    launch(args);
  }
}

