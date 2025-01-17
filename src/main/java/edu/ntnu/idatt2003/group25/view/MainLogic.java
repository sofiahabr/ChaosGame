package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * The type Main logic that holds the values and attributes necessary to run the project.
 */
public class MainLogic extends View {
  /**
   * The constant height of the application, based on the individual screen size.
   */
  public static int height = (int) Math.round(Screen.getPrimary().getBounds().getHeight() * 0.85f);
  /**
   * The constant width of the application, based on the individual screen size.
   */
  public static int width = (int) Math.round(Screen.getPrimary().getBounds().getWidth() * 0.8f);
  /**
   * The Scene the view is displayed on.
   */
  private final Scene scene = new Scene(new Pane(), width, height);
  /**
   * The Screen controller, the object that controls what screen is showed.
   */
  private final ScreenController screenController = new ScreenController(scene);
  /**
   * The Initialize page the view representing the first page of the application.
   */
  private final InitializePage initializePage = new InitializePage(screenController);
  /**
   * The Factorial page, the view representing the page of the
   * application where the fractal is drawn.
   */
  private final FactorialPage factorialPage = new FactorialPage(screenController);

  /**
   * The method for starting the application.
   *
   * @param stage the stage
   */
  public void start(Stage stage) {
    screenController.addScreenContent(initializePage, "initialize page");

    screenController.gameChanged("switch page", "initialize page");
    scene.getStylesheets().add("/style/style.css");

    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.setMinHeight(height * 0.5);
    stage.setMinWidth(width * 0.5);
    stage.show();

    FactorialPageController factorialPageController =
        new FactorialPageController(screenController, factorialPage);

    addObserver(factorialPageController);

    scene.widthProperty().addListener((observable, oldValue, newValue) -> {
      updateObserver("sceneChange", "0:" + newValue.toString());
      width = newValue.intValue();
    });

    scene.heightProperty().addListener((observable, oldValue, newValue) -> {
      updateObserver("sceneChange", newValue.toString() + ":0");
      height = newValue.intValue();
    });
  }

  /**
   * Gets pane.
   *
   * @return the pane
   */

  @Override
  public BorderPane getPane() {
    return null;
  }

  /**
   * The setUp method is extended by view, but doesn't have any returns in this class.
   */
  @Override
  public void setUp() {
  }


}
