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

/**
 * The type Edit transform controller, the controller that gets information from, and controls the edit transforms- menu.
 */
public class EditTransformController extends Controller {
  /**
   * The error message for when the input is an invalid number.
   */
  String invalidNumber = " must be numbers";
  /**
   * The error message for when a number must be 1 or -1.
   */
  String oneOrNegOne = " must be 1 or -1";

  /**
   * The default value for validation and error handling.
   */
  int defaultValue = -9999;
  /**
   * The menu where its possible to edit transforms.
   */
  EditTransformsMenu editTransformsMenu;
  /**
   * The Factorial page, the view page where the game is displayed.
   */
  FactorialPage factorialPage;
  /**
   * A list of all the vectors in the ChaosGameDescription.
   */
  private List<Vector2D> vectorsInDescription = new ArrayList<>();
  /**
   * A list of all the matrices in the ChaosGameDescription.
   */
  private List<Matrix2x2> matrixInDescription = new ArrayList<>();
  /**
   * A list of all the signs in the ChaosGameDescription.
   */
  private List<Integer> signInput = new ArrayList<>();

  /**
   * Instantiates a new Edit transform controller.
   *
   * @param editTransformsMenu the menu where a user can edit transforms in the game
   * @param factorialPage      the page where the game is displayed
   */
  public EditTransformController(EditTransformsMenu editTransformsMenu,
                                 FactorialPage factorialPage) {
    this.editTransformsMenu = editTransformsMenu;
    this.factorialPage = factorialPage;
  }

  /**
   * The method that receives information from the menu and updates the game accordingly.
   *
   * @param event the action / event the user has done
   * @param info  the information linked to the event
   */
  @Override
  public void gameChanged(String event, String info) {
    switch (event) {
      case "save vectors" -> saveVectors(info);
      case "save matrix" -> saveMatrix(info);
      case "save signs" -> saveSigns(info);
      case "edit description" -> editDescription(info);
      case "min input", "max input" -> handleInputVector("InputMinMax", info, "Min and max");
      case "sign input" -> handleSignInput("InputSign", info, "Sign");
      case "matrix input" -> handleInputMatrix("InputMatrix", info, "Matrix's");
      case "vector input" -> handleInputVector("InputVector", info, "Vectors");
    }
  }

  /**
   * The method for saving vectors, based on user input received from the view.
   * The method splits the input string into a list of vectors.
   *
   * @param info the information about the vectors' values, from the view
   */
  private void saveVectors(String info) {
    String[] vectorNumbers = info.split(",");
    List<Vector2D> vectorList = new ArrayList<>();

    for (int i = 0; i < vectorNumbers.length - 2; ) {
      vectorList.add(new Vector2D(Validation.verifyDouble(vectorNumbers[i], 0),
          Validation.verifyDouble(vectorNumbers[i + 1], 0)));
      i += 2;
    }
    this.vectorsInDescription = vectorList;
  }

  /**
   * The method for saving matrices, based on user input received from the view.
   * The method splits the input string into a list of matrices.
   *
   * @param info the information about the matrices' values, from the view
   */
  private void saveMatrix(String info) {
    String[] matrixNumbers = info.split(",");
    List<Matrix2x2> matrixList = new ArrayList<>();

    for (int i = 0; i < matrixNumbers.length - 4; ) {
      matrixList.add(new Matrix2x2(Validation.verifyDouble(matrixNumbers[i], 0),
          Validation.verifyDouble(matrixNumbers[i + 1], 0),
          Validation.verifyDouble(matrixNumbers[i + 2], 0),
          Validation.verifyDouble(matrixNumbers[i + 3], 0)));
      i += 4;
    }
    this.matrixInDescription = matrixList;
  }

  /**
   * The method for saving signs, based on user input received from the view.
   * The method splits the input string into a list of signs.
   *
   * @param info the information about the signs' values, from the view
   */

  private void saveSigns(String info) {
    String[] signNumbers = info.split(",");

    List<Integer> signList = new ArrayList<>();

    for (String signNumber : signNumbers) {
      if (!signNumber.trim().isEmpty()) {
        int sign = Validation.verifyInt(signNumber, -3);
        signList.add(sign);
      }
    }
    this.signInput = signList;
  }

  /**
   * The method for editing the description of the game, based on user input received from the view.
   * The method splits the input string into 4 strings, the min and max values.
   *
   * @param info the information about the min and max values, from the view
   */

  private void editDescription(String info) {
    String[] minMax = info.split(",");

    List<Transform2D> transforms = new ArrayList<>();
    ChaosGameDescription description = new ChaosGameDescription(new ArrayList<>(),
        new Vector2D(Validation.verifyDouble(minMax[0], 0), Validation.verifyDouble(minMax[1], 0)),
        new Vector2D(Validation.verifyDouble(minMax[2], 0), Validation.verifyDouble(minMax[3], 0)));

    if (factorialPage.getGameType().equals("Affine Transform") &&
        matrixInDescription.size() == vectorsInDescription.size()) {
      for (int i = 0; i < matrixInDescription.size(); i++) {
        transforms.add(
            new AffineTransform2D(matrixInDescription.get(i), vectorsInDescription.get(i)));
      }
    }
    if (factorialPage.getGameType().equals("Julia Transform") &&
        vectorsInDescription.size() == signInput.size()) {
      for (int i = 0; i < vectorsInDescription.size(); i++) {
        transforms.add(new JuliaTransform(
            new Complex(vectorsInDescription.get(i).getX0(), vectorsInDescription.get(i).getX1()),
            signInput.get(i)));
      }
    }
    description.setTransforms(transforms);
    factorialPage.setDescription(description);
  }

  /**
   * The method for handling input in a vector text field.
   * The method splits the input string into 2 strings, the x0 and x1 values.
   * The method checks if the input is a valid number, and if not, sends an error message to the view.
   *
   * @param key  the key for accessing the error message in the errorMap.
   * @param info the info the user has inputted.
   * @param name the name of the input field.
   */
  public void handleInputVector(String key, String info, String name) {
    String[] values = info.split(",");
    Vector2D vector = new Vector2D(Validation.verifyDouble(values[0], defaultValue),
        Validation.verifyDouble(values[1], defaultValue));

    if (vector.getX0() == defaultValue || vector.getX1() == defaultValue) {
      editTransformsMenu.showError(key, name + invalidNumber);
    } else {
      editTransformsMenu.showError(key, "");
    }
  }

  /**
   * The method for handling input in a sign text field.
   * The method checks if the input is a valid number, and if not, sends an error message to the view.
   *
   * @param key the key for accessing the error message in the errorMap.
   * @param info the info the user has inputted.
   * @param name the name of the input field.
   */

  private void handleSignInput(String key, String info, String name) {
    int sign = Validation.verifyInt(info, -2);
    if (sign == 1 || sign == -1) {
      editTransformsMenu.showError(key, "");
    } else {
      editTransformsMenu.showError(key, name + oneOrNegOne);
    }
  }

  /**
   * The method for handling input in a matrix text field.
   * The method splits the input string into 4 strings, the a00, a01, a10 and a11 values.
   * The method checks if the input is a valid number, and if not, sends an error message to the view.
   *
   * @param key  the key for accessing the error message in the errorMap.
   * @param info the info the user has inputted.
   * @param name the name of the input field.
   */
  public void handleInputMatrix(String key, String info, String name) {
    String[] values = info.split(",");
    Matrix2x2 matrix2x2 = new Matrix2x2(
        Validation.verifyDouble(values[0], defaultValue),
        Validation.verifyDouble(values[1], defaultValue),
        Validation.verifyDouble(values[2], defaultValue),
        Validation.verifyDouble(values[3], defaultValue));

    if (matrix2x2.getA00() == defaultValue || matrix2x2.getA01() == defaultValue ||
        matrix2x2.getA10() == defaultValue || matrix2x2.getA11() == defaultValue) {
      editTransformsMenu.showError(key, name + invalidNumber);
    } else {
      editTransformsMenu.showError(key, "");
    }
  }
}
