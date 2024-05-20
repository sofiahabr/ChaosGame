package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Matrix2x2;
import edu.ntnu.idatt2003.group25.model.Validation;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.view.MainLogic;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.menus.EditTransformsMenu;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.stage.FileChooser;

/**
 * FactorialPageController function as the FactorialPage's controller class.
 */
public class FactorialPageController extends Controller {
  ScreenController screenController;
  FactorialPage factorialPage;
  int steps = 0;
  Vector2D min = new Vector2D(0,0);
  Vector2D max = new Vector2D(0,0);
  Vector2D vector2D = new Vector2D(0,0);
  Complex complex = new Complex(0,0);
  Matrix2x2 matrix = new Matrix2x2(0,0,0,0);
  int sign = 0;
  String invalidPositiveNumber = "Please enter a positive \nnumber 0 -1000 000 000";
  String invalidNumber = "Please enter numbers";
  String oneOrNegOne = "Number must be 1 or -1";
  int defaultValue = -9999;

  /**
   * The FactorialPageController takes in instances of ScreenController and FactorialPage.
   * @param factorialPage
   */

  public FactorialPageController(ScreenController screenController, FactorialPage factorialPage) {
    this.screenController = screenController;
    this.factorialPage = factorialPage;

  }

  @Override
  public void gameChanged(String action, String info) {
    switch (action) {
      case "button clicked" -> buttonClicked(info);
      case "vector input" -> vector2D = handleInputVector(info, "InputVector");
      case "min input" -> min = handleInputVector(info, "InputMinMax");
      case "max input" -> max = handleInputVector(info, "InputMinMax");
      case "sign input" -> sign = handleInputSign(info);
      case "register steps" -> steps = handleInputSteps(info);
      case "matrix input" -> matrix = handleInputMatrix(info);
    }
  }
  public void buttonClicked(String info){
    switch (info) {
      case "play"-> {
        factorialPage.getChaosGame().runSteps(steps);
        factorialPage.draw();
      }
      case "add"-> addAction();
      case "reset"-> resetAction();
      case "save" -> saveAction();
      case "edit" -> new EditTransformsMenu(screenController, factorialPage);
    }
  }
    public int handleInputSign(String input) {
      int sign = Validation.verifyInt(input, 0);
      if (sign == 1 || sign == -1) {
        factorialPage.showError("InputSign", "");
        return sign;
      } else {
        factorialPage.showError("InputSign", oneOrNegOne);
      }
      return 0; //Default value
    }
  public int handleInputSteps(String input) {
    int inputSteps = Validation.verifyPositiveInt(input,0);
    if (inputSteps == 0) {
      factorialPage.showError("InputSteps", invalidPositiveNumber);
    }
    else {
      factorialPage.showError("InputSteps", "");
    }
    return inputSteps;
  }
  public Vector2D handleInputVector(String info, String key){
    String[] values = info.split(",");
    Vector2D vector = new Vector2D(Validation.verifyDouble(values[0], defaultValue),
        Validation.verifyDouble(values[1], defaultValue));

    if (vector.getX0() == defaultValue || vector.getX1() == defaultValue) {
      factorialPage.showError(key, invalidNumber);
    } else {
      factorialPage.showError(key, " ");
      return vector;
    }
    return null;
  }
  public Matrix2x2 handleInputMatrix(String info){
    String[] values = info.split(",");
    Matrix2x2 matrix2x2 = new Matrix2x2(
        Validation.verifyDouble(values[0],defaultValue), Validation.verifyDouble(values[1],defaultValue),
        Validation.verifyDouble(values[2],defaultValue), Validation.verifyDouble(values[3],defaultValue));

    if (matrix2x2.getA00() == defaultValue || matrix2x2.getA01() == defaultValue ||
        matrix2x2.getA10() == defaultValue || matrix2x2.getA11() == defaultValue) {
      factorialPage.showError("InputMatrix", invalidNumber);
    } else {
      factorialPage.showError("InputMatrix", "");
      return matrix2x2;
    }
    return null;
  }
//  public void handleInputSceneChange(String info) {
//    String[] newValue = info.split(":");
//    double providedHeight = Validation.verifyDouble(newValue[0], 0);
//    if (providedHeight > 0) {
//      this.height = (int) providedHeight;
//    }
//    double providedWidth = Validation.verifyDouble(newValue[1], 0);
//    if (providedWidth > 0) {
//      this.width = (int) providedWidth;
//    }
//  }
private void addAction() {
  if (factorialPage.getGameType().equals("Julia Transform")) {
    complex = new Complex(vector2D.getX0(), vector2D.getX1());
    factorialPage.getDescription().getTransforms().add(new JuliaTransform(complex, sign));
  } else if (factorialPage.getGameType().equals("Affine Transform")) {
    factorialPage.getDescription().getTransforms().add(new AffineTransform2D(matrix, vector2D));
  }
}
  private void resetAction() {
    factorialPage.reset();
  }
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
  public void handleScreenResize(double newWidth, double newHeight) {
    int canvasWidth = (int) (newWidth * 0.7);
    int canvasHeight = (int) (newHeight * 0.7);
    ChaosGameDescription description = factorialPage.getDescription();
    ChaosGame newChaosGame = new ChaosGame(description, canvasWidth, canvasHeight);
    factorialPage.setDescription(description);

    factorialPage.reset();
  }
}