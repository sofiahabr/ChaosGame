package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Matrix2x2;
import edu.ntnu.idatt2003.group25.model.Validation;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.MainLogic;
import edu.ntnu.idatt2003.group25.view.menus.EditTransformsMenu;
import java.io.File;
import java.util.ArrayList;
import javafx.stage.FileChooser;

/**
 * FactorialPageController functions as the FactorialPage's controller class.
 */
public class FactorialPageController extends Controller {
  /**
   * The Screen controller, the controller that controls what view is displayed on the screen.
   */
  ScreenController screenController;
  /**
   * The Factorial page, the view page where the game is displayed.
   */
  FactorialPage factorialPage;
  /**
   * The steps the game will run.
   */
  int steps = 0;
  /**
   * The Min coordinates for a transformation.
   */
  Vector2D min = new Vector2D(0, 0);
  /**
   * The Max coordinates for a transformation.
   */
  Vector2D max = new Vector2D(0, 0);
  /**
   * The Vector2D for the transformation.
   */
  Vector2D vector2D = new Vector2D(0, 0);
  /**
   * The Complex, the complex vector for the transformation.
   */
  Complex complex = new Complex(0, 0);
  /**
   * The Matrix the transformation will use.
   */
  Matrix2x2 matrix = new Matrix2x2(0, 0, 0, 0);
  /**
   * The sign for the transformation.
   */
  int sign = 0;
  /**
   * The error message displayed when the input in not a number or a negative number.
   */
  String invalidPositiveNumber = "Please enter a positive \nnumber 0 -1000 000 000";
  /**
   * The error message displayed when the input in not a number.
   */
  String invalidNumber = "Please enter numbers";
  /**
   * The error message displayed when the input in not 1 or -1.
   */
  String oneOrNegOne = "Number must be 1 or -1";
  /**
   * The default value for validation and error handling.
   */
  int defaultValue = -9999;

  /**
   * The FactorialPageController takes in instances of ScreenController and FactorialPage.
   * The FactorialPageController is responsible for handling the user input and updating the FactorialPage.
   *
   * @param screenController the screen controller
   * @param factorialPage    the factorial page
   */
  public FactorialPageController(ScreenController screenController, FactorialPage factorialPage) {
    this.screenController = screenController;
    this.factorialPage = factorialPage;

  }

  /**
   * The method for handling the user input and handling the game accordingly.
   * The method is called when the user interacts with the view.
   *
   * @param event the action / event the user has done
   * @param info the information linked to the event
   */

  @Override
  public void gameChanged(String event, String info) {
    switch (event) {
      case "button clicked" -> buttonClicked(info);
      case "register steps" -> steps = handleInputSteps(info);
    }
  }

  /**
   * The method for handling the users interaction when it has clicked on a button.
   *
   * @param info the button clicked
   */
  public void buttonClicked(String info) {
    switch (info) {
      case "play" -> {
        factorialPage.getChaosGame().runSteps(steps);
        factorialPage.draw();
      }
      case "add" -> addAction();
      case "reset" -> resetAction();
      case "save" -> saveAction();
      case "edit" -> new EditTransformsMenu(screenController, factorialPage);
    }
  }

  /**
   * The method for handling the user input in steps text field, and validating the input.
   *
   * @param input the input received from the view.
   * @return the amount of steps the user wants to run, or 0 if the input is invalid.
   */
  public int handleInputSteps(String input) {
    int inputSteps = Validation.verifyPositiveInt(input, 0);
    if (inputSteps == 0) {
      factorialPage.showError("InputSteps", invalidPositiveNumber);
    } else {
      factorialPage.showError("InputSteps", "");
    }
    return inputSteps;
  }

  /**
   * The method for adding a transformation to the game's description.
   * The method is called when the user clicks the add button.
   * The method adds an empty transformation to the game's description.
   */

  private void addAction() {
    if (factorialPage.getGameType().equals("Julia Transform")) {
      factorialPage.getDescription().getTransforms().add(new JuliaTransform(complex, sign));
      factorialPage.setUp();
    } else if (factorialPage.getGameType().equals("Affine Transform")) {
      factorialPage.getDescription().getTransforms().add(new AffineTransform2D(matrix, vector2D));
      factorialPage.setUp();
    }
  }

  /**
   * The method for resetting the canvas where the factorials are drawn.
   * It also resets the canvas' registered pixels.
   */

  private void resetAction() {
    factorialPage.reset();
    factorialPage.getChaosGame().getCanvas().clear();
  }

  /**
   * The method for saving the game's description to a file.
   */

  private void saveAction() {
    ChaosGameFileHandler fileHandler =
        new ChaosGameFileHandler(new ArrayList<>(), new Vector2D(0, 0),
            new Vector2D(MainLogic.width * 0.7f, MainLogic.height * 0.7f));
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter(
            "files (*.txt)", "*.txt"));
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
      try {
        fileHandler.writeToFile(factorialPage.getDescription(), selectedFile.getPath());
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }

  /**
   * The method for handling the screen resize event.
   */
  public void handleScreenResize(double newWidth, double newHeight) {
    ChaosGameDescription description = factorialPage.getDescription();
    factorialPage.setDescription(description);

    factorialPage.reset();
  }
}