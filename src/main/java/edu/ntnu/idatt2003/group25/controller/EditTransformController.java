package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Matrix2x2;
import edu.ntnu.idatt2003.group25.model.Validation;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.menus.EditTransformsMenu;
import java.util.ArrayList;
import java.util.List;

public class EditTransformController extends Controller{
  String invalidNumber = " must be numbers";

  int defaultValue = -9999;
  EditTransformsMenu editTransformsMenu;
  FactorialPage factorialPage;
    private List<Vector2D> vectorsInDescription = new ArrayList<>();
    private List<Matrix2x2> matrixInDescription = new ArrayList<>();

    public EditTransformController(EditTransformsMenu editTransformsMenu, FactorialPage factorialPage) {
        this.editTransformsMenu = editTransformsMenu;
        this.factorialPage = factorialPage;
    }

  @Override
  public void gameChanged(String event, String info) {
    switch (event) {
      case "save vectors" -> saveVectors(info);
      case "save matrix" -> saveMatrix(info);
      case "edit description" -> editDescription(info);
      case "min input", "max input" -> handleInputVector("InputMinMax", info, "Min and max");
      case "matrix input" -> handleInputMatrix("InputMatrix", info, "Matrix's");
      case "vector input" -> handleInputVector("InputVector", info, "Vectors");
    }
  }
  private void saveVectors(String info) {
      System.out.println("Vectors: " + info);
      String[] vectorNumbers = info.split(",");
      List<Vector2D> vectorList = new ArrayList<>();

      for (int i = 0; i < vectorNumbers.length-2;) {
        vectorList.add(new Vector2D(Validation.verifyDouble(vectorNumbers[i], 0) ,
            Validation.verifyDouble(vectorNumbers[i + 1], 0)));
        i += 2;
      }
      this.vectorsInDescription = vectorList;
  }
    private void saveMatrix(String info) {
      String[] matrixNumbers = info.split(",");
      List<Matrix2x2> matrixList = new ArrayList<>();

      for (int i = 0; i < matrixNumbers.length - 4;) {
        matrixList.add(new Matrix2x2(Validation.verifyDouble(matrixNumbers[i], 0) ,
            Validation.verifyDouble(matrixNumbers[i + 1], 0),
            Validation.verifyDouble(matrixNumbers[i + 2], 0),
            Validation.verifyDouble(matrixNumbers[i + 3], 0)));
        i += 4;
      }
      this.matrixInDescription = matrixList;
    }
    private void editDescription(String info) {
      System.out.println("Min and max: " + info);
      String[] minMax = info.split(",");

      List<Transform2D> transforms = new ArrayList<>();
      if(factorialPage.getGameType().equals("Affine Transform") && matrixInDescription.size() == vectorsInDescription.size()) {
        for(int i = 0; i < matrixInDescription.size(); i++) {
          transforms.add(new AffineTransform2D(matrixInDescription.get(i), vectorsInDescription.get(i)));
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
  public void handleInputVector(String key, String info, String name){
    System.out.println(info);
    System.out.println(key);
    System.out.println(name);
    String[] values = info.split(",");
    Vector2D vector = new Vector2D(Validation.verifyDouble(values[0], defaultValue),
        Validation.verifyDouble(values[1], defaultValue));

    if (vector.getX0() == defaultValue || vector.getX1() == defaultValue) {
      editTransformsMenu.showError(key, name + invalidNumber);
    } else {
      editTransformsMenu.showError(key, "");
    }
  }
  public void handleInputMatrix(String key, String info, String name){
    String[] values = info.split(",");
    Matrix2x2 matrix2x2 = new Matrix2x2(
        Validation.verifyDouble(values[0],defaultValue), Validation.verifyDouble(values[1],defaultValue),
        Validation.verifyDouble(values[2],defaultValue), Validation.verifyDouble(values[3],defaultValue));

    if (matrix2x2.getA00() == defaultValue || matrix2x2.getA01() == defaultValue ||
        matrix2x2.getA10() == defaultValue || matrix2x2.getA11() == defaultValue) {
      editTransformsMenu.showError(key, name + invalidNumber);
    } else {
      editTransformsMenu.showError(key, "");
    }
  }
}
