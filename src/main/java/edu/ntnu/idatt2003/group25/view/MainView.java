package edu.ntnu.idatt2003.group25.view;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainView extends Application {

  private final MainLogic mainLogic = new MainLogic();

  @Override
  public void start(Stage stage) {
    mainLogic.start(stage);
  }

    public static void main (String[]args){
      launch(args);
    }
  }


