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
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.MainView;
import edu.ntnu.idatt2003.group25.view.menus.EditTransformsMenu;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
  List<Vector2D> vectorsInDescription = null;
  List<Matrix2x2> matrixesInDescription = null;
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
      case "save matrix" -> matrixesInDescription = saveMatrix(info);
      case "save vectors" -> vectorsInDescription = saveVectors(info);
      case "edit description" -> editDescription(info);
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

  private List<Vector2D> saveVectors(String info) {
    System.out.println("Vectors: " + info);
    String[] vectorNumbers = info.split(",");
    List<Vector2D> vectorList = new ArrayList<>();

    for (int i = 0; i < vectorNumbers.length-2;) {
      vectorList.add(new Vector2D(Validation.verifyDouble(vectorNumbers[i], 0) ,
          Validation.verifyDouble(vectorNumbers[i + 1], 0)));
      i += 2;
    }
    return vectorList;
  }
  private List<Matrix2x2> saveMatrix(String info) {
    String[] matrixNumbers = info.split(",");
    List<Matrix2x2> matrixList = new ArrayList<>();

    for (int i = 0; i < matrixNumbers.length - 4;) {
      matrixList.add(new Matrix2x2(Validation.verifyDouble(matrixNumbers[i], 0) ,
          Validation.verifyDouble(matrixNumbers[i + 1], 0),
          Validation.verifyDouble(matrixNumbers[i + 2], 0),
          Validation.verifyDouble(matrixNumbers[i + 3], 0)));
      i += 4;
    }
    return matrixList;
  }
  private void editDescription(String info) {
    System.out.println("Min and max: " + info);
    String[] minMax = info.split(",");

    List<Transform2D> transforms = new ArrayList<>();
    if(factorialPage.getGameType().equals("Affine Transform") && matrixesInDescription.size() == vectorsInDescription.size()) {
      for(int i = 0; i < matrixesInDescription.size(); i++) {
        transforms.add(new AffineTransform2D(matrixesInDescription.get(i), vectorsInDescription.get(i)));
      }
    }
    if(factorialPage.getGameType().equals("Julia Transform")) {
      for(int i = 0; i < vectorsInDescription.size(); i++) {
        transforms.add(new JuliaTransform(new Complex(vectorsInDescription.get(i).getX0(), vectorsInDescription.get(i).getX1()),1));
      }
    }
    factorialPage.setDescription(new ChaosGameDescription(transforms,
        new Vector2D(Validation.verifyDouble(minMax[0], 0), Validation.verifyDouble(minMax[1], 0)),
        new Vector2D(Validation.verifyDouble(minMax[2], 0), Validation.verifyDouble(minMax[3], 0))));
  }
}