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
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.MainView;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.stage.FileChooser;

public class FactorialPageController extends Controller {
  ScreenController screenController;
  FactorialPage factorialPage;
  ChaosGame chaosGame;
  int steps = 0;
  Vector2D min = null;
  Vector2D max = null;
  Vector2D vector2D = null;
  Complex complex = null;
  Matrix2x2 matrix = null;
  Canvas pixelCanvas;
  public FactorialPageController(ScreenController screenController, FactorialPage factorialPage) {
    this.screenController = screenController;
    this.factorialPage = factorialPage;
    this.chaosGame = new ChaosGame(ChaosGameDescriptionFactory.createSierpinski(), Math.round(MainView.width*0.7f), Math.round(MainView.height));
    this.pixelCanvas = new Canvas(chaosGame.getCanvas().getWidth(), chaosGame.getCanvas().getHeight());



  }
  @Override
  public void gameChanged(String action, String info) {
    switch (action) {
      case "button clicked" -> buttonClicked(info);
      case "vector input" -> vector2D = registerVector2D(info);
      case "min input"-> min = registerVector2D(info);
      case "max input"-> max = registerVector2D(info);
      case "register steps" -> steps = registerInt(info);
      case "matrix input" -> matrix = registerMatrix(info);
    }
  }

  public int registerInt(String input) {
    System.out.println(steps);
    return steps = Validation.verifyPositiveInt(input, 0);
  }
  public Vector2D registerVector2D(String info){
    String[] values = info.split(",");
    return new Vector2D(Validation.verifyDouble(values[0], 0), Validation.verifyDouble(values[0],0 ));

  }
  public Matrix2x2 registerMatrix(String info){
    String[] values = info.split(",");
    return new Matrix2x2(
        Validation.verifyDouble(values[0],0), Validation.verifyDouble(values[1],0),
        Validation.verifyDouble(values[2],0), Validation.verifyDouble(values[3],0));
  }
  public void buttonClicked(String info){
    switch (info) {
      case "play"-> factorialPage.draw(steps);
      case "add"-> addAction();
      case "reset"-> resetAction();
      case "save" -> saveAction();
      case "edit" -> editAction();
    }
  }
  private void addAction() {
    if (factorialPage.getDescription().getTransforms().getFirst().getClass().getName()
        .contains("Julia")) {
      complex = new Complex(vector2D.getX0(), vector2D.getX1());
      factorialPage.getDescription().getTransforms().add(new JuliaTransform(complex, 1));
    } else {
      factorialPage.getDescription().getTransforms().add(new AffineTransform2D(matrix, vector2D));
    }
  }
  private void resetAction() {
    factorialPage.reset();
  }
  private void saveAction() {
    ChaosGameFileHandler fileHandler =
        new ChaosGameFileHandler(new ArrayList<>(), new Vector2D(0, 0),
            new Vector2D(MainView.width * 0.7f, MainView.height * 0.7f));
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

  private void editAction() {
    System.out.println("edit action");
    ChaosGameDescription description = factorialPage.getDescription();

    if(min != null) {
      System.out.println("Min: " + min);
      description.setMinCoords(min);
    } if (max != null) {
      System.out.println("Max: " + max);
      description.setMaxCoords(max);
    } if (vector2D != null) {
      System.out.println("Vector: " + vector2D);
      description.getTransforms().forEach(transform -> {
        if (transform instanceof JuliaTransform) {
          complex = new Complex(vector2D.getX0(), vector2D.getX1());
          ((JuliaTransform) transform).setComplex(complex);
        } else if (transform instanceof AffineTransform2D) {
          ((AffineTransform2D) transform).setVector(vector2D);
        }
      });
    } if (matrix != null) {
      System.out.println(matrix);
      description.getTransforms().forEach(transform -> {
        if (transform instanceof AffineTransform2D) {
          ((AffineTransform2D) transform).setMatrix(matrix);
        }
      });
    }
    factorialPage.setDescription(description);
  }
}