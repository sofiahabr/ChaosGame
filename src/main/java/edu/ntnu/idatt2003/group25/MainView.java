package edu.ntnu.idatt2003.group25;

import edu.ntnu.idatt2003.group25.view.MainLogic;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The MainView class is the main class for the application.
 */
public class MainView extends Application {
  /**
   * The Main logic, the main logic for the application.
   */
  private final MainLogic mainLogic = new MainLogic();

  /**
   * The method that starts the application.
   *
   * @param stage the stage where the application is displayed.
   */
  @Override
  public void start(Stage stage) {
    mainLogic.start(stage);
  }

  /**
   * The main method that starts the application.
   * @param args the arguments.
   */

  public static void main(String[] args) {
    launch(args);
  }
}


