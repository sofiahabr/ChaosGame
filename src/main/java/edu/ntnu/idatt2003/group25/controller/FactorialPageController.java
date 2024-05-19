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
  int height = MainLogic.height;
  int width = MainLogic.width;
  ChaosGame chaosGame = new ChaosGame(MainLogic.description,MainLogic.width, MainLogic.height);
  ScreenController screenController;
  FactorialPage factorialPage;
  int steps = 0;
  Vector2D min = null;
  Vector2D max = null;
  Vector2D vector2D = null;
  Complex complex = null;
  Matrix2x2 matrix = null;
  Canvas pixelCanvas = new Canvas(chaosGame.getCanvas().getWidth(), chaosGame.getCanvas().getHeight());
  String invalidPositiveNumber = "Please enter a positive number\n 0 -1000 000 000";
  String invalidNumber = "Please enter numbers";
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
      case "vector input" -> {
        vector2D = registerVector2D(info);
        System.out.println(vector2D.toString());
        boolean hasError = false;
        if (vector2D.getX0() == defaultValue) {
          factorialPage.showError("InputVector", invalidNumber);
          hasError = true;
        }
        if (vector2D.getX1() == defaultValue) {
          factorialPage.showError("InputVector", invalidNumber);
          hasError = true;
        }
        if (!hasError) {
          factorialPage.showError("InputVector", " ");
        }
      }
      case "min input" -> {
        min = registerVector2D(info);
        if (min.getX0() == defaultValue || min.getX1() == defaultValue) {
          factorialPage.showError("InputMinMax", invalidNumber);
        } else {
          factorialPage.showError("InputMinMax", " ");
        }
      }
      case "max input" -> {
        max = registerVector2D(info);
        if (max.getX0() == defaultValue || max.getX1() == defaultValue) {
          factorialPage.showError("InputMinMax", invalidNumber);
          factorialPage.showError("InputMinMax", invalidNumber);
        } else {
          factorialPage.showError("InputMinMax", " ");
        }
      }
      case "register steps" -> {
        int input = registerInt(info);
        if (input == 0) { //Default value
          factorialPage.showError("InputSteps", invalidPositiveNumber);
        } else {
          factorialPage.showError("InputSteps", "");
          steps = input;
        }
      }
      case "matrix input" -> {
        matrix = registerMatrix(info);
        if (matrix.getA00() == defaultValue || matrix.getA01() == defaultValue ||
            matrix.getA10() == defaultValue || matrix.getA11() == defaultValue) {
          factorialPage.showError("InputMatrix", invalidNumber);
        } else {
          factorialPage.showError("InputMatrix", "");
        }
      }
      case "sceneChange" -> {
        String[] newValue = info.split(":");
        double providedHeight = Validation.verifyDouble(newValue[0], 0);
        if (providedHeight > 0) {
          this.height = (int) providedHeight;
        }
        double providedWidth = Validation.verifyDouble(newValue[1], 0);
        if (providedWidth > 0) {
          this.width = (int) providedWidth;
        }
        this.pixelCanvas = new Canvas(width, height);
        this.chaosGame = new ChaosGame(MainLogic.description, width, height);
        break;
      }
      case "save vectors" -> saveVectors(info);
      case "save matrix" -> saveMatrix(info);
      case "edit description" -> editDescription(info);
    }
  }

  public int registerInt(String input) {
    steps = Validation.verifyPositiveInt(input,0);
    return steps;
  }
  public Vector2D registerVector2D(String info){
    String[] values = info.split(",");
    return new Vector2D(Validation.verifyDouble(values[0], defaultValue), Validation.verifyDouble(values[1],defaultValue));

  }
  public Matrix2x2 registerMatrix(String info){
    String[] values = info.split(",");
    return new Matrix2x2(
        Validation.verifyDouble(values[0],defaultValue), Validation.verifyDouble(values[1],defaultValue),
        Validation.verifyDouble(values[2],defaultValue), Validation.verifyDouble(values[3],defaultValue));
  }
  public void updateChaosGame(ChaosGame chaosGame, int width, int height) {
    chaosGame.setWidth(width);
    chaosGame.setHeight(height);
  }
  public void updateChaosCanvas(int width, int height) {
    this.pixelCanvas = new Canvas(width, height);

    //this.pixelCanvas.setHeight(height);
    //this.pixelCanvas.setWidth(width);
  }
  public void buttonClicked(String info){
    switch (info) {
      case "play"-> factorialPage.draw(steps);
      case "add"-> addAction();
      case "reset"-> resetAction();
      case "save" -> saveAction();
      case "edit" -> new EditTransformsMenu(screenController, factorialPage);
    }
  }
  private void addAction() {
    if (factorialPage.getGameType().equals("Julia Transform")) {
      complex = new Complex(vector2D.getX0(), vector2D.getX1());
      factorialPage.getDescription().getTransforms().add(new JuliaTransform(complex, 1));


    } else if (factorialPage.getGameType().equals("Affine Transform")){
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
}