package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.EditTransformController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.MainLogic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * The EditTransformsMenu class creates a menu for editing the transforms of a ChaosGameDescription.
 */
public class EditTransformsMenu extends Menu {

  /**
   * FactorialPage object representing the page where the transforms are edited.
   */
  private final FactorialPage factorialPage;
  /**
   * HashMap containing error messages and their placement.
   */
  private final HashMap<String, String> errorMap = new HashMap<>();
  /**
   * Height and width of the buttons in the menu.
   */
  private final int buttonHeight = Math.round(MainLogic.height * 0.03f);
  private static final int buttonWidth = 190;
  /**
   * VBox object representing the menu.
   */
  private VBox menu = new VBox(20);
  /**
   * Label object representing the error label.
   */
  private final Label errorLabel = new Label();
  /**
   * List of TextField objects representing the vector input fields.
   */
  private final List<TextField> vectorTextFields = new ArrayList<>();
  /**
   * List of TextField objects representing the matrix input fields.
   */
  private final List<TextField> matrixTextFields = new ArrayList<>();
  /**
   * List of TextField objects representing the sign input fields.
   */
  private final List<TextField> signTextFields = new ArrayList<>();
  /**
   * List of TextField objects representing the min/max input fields.
   */
  private final List<TextField> minMaxFields = new ArrayList<>();

  /**
   * Constructs an EditTransformsMenu object with specified screenController and FactorialPage.
   *
   * @param screenController ScreenController object representing the controller for the page.
   * @param factorialPage    FactorialPage object representing the page where the transforms
   *                         are edited.
   */
  public EditTransformsMenu(ScreenController screenController, FactorialPage factorialPage) {
    super(screenController);
    this.factorialPage = factorialPage;
    addObserver(new EditTransformController(this, factorialPage));

    if (!factorialPage.getDescription().getTransforms().isEmpty()) {
      setUp();
    }
  }

  /**
   * Sets up the menu for editing the transforms.
   */

  @Override
  public void setUp() {
    menu.getStyleClass().add("vbox");
    this.menu = createSideBar();
  }

  /**
   * Creates a VBox object containing the min/max input fields.
   *
   * @return VBox object containing the min/max input fields.
   */
  private VBox chooseMinMaxArea() {
    // Create min/ max area
    Text minTitle = new Text(" Min: ");
    Text maxTitle = new Text(" Max: ");
    maxTitle.getStyleClass().add("heading");
    minTitle.getStyleClass().add("heading");

    TextField inputMax = new TextField();
    inputFieldStyle(inputMax, factorialPage.getDescription().getMaxCoords().getX0()
        + "", buttonHeight, buttonWidth / 4 - 5);

    TextField inputMax2 = new TextField();
    inputFieldStyle(inputMax2, factorialPage.getDescription().getMaxCoords().getX1()
        + "", buttonHeight, buttonWidth / 4 - 5);

    inputMax.setOnKeyTyped(
        e -> updateObserver("max input", inputMax.getText()
            + ", " + inputMax2.getText()));
    inputMax2.setOnKeyTyped(
        e -> updateObserver("max input", inputMax.getText()
            + ", " + inputMax2.getText()));

    HBox maxArea = new HBox(10);
    maxArea.getChildren().addAll(inputMax, inputMax2);

    // Min area
    TextField inputMin = new TextField();
    inputFieldStyle(inputMin, factorialPage.getDescription().getMinCoords().getX0()
        + "", buttonHeight, buttonWidth / 4 - 5);
    TextField inputMin2 = new TextField();
    inputFieldStyle(inputMin2, factorialPage.getDescription().getMinCoords().getX1()
        + "", buttonHeight, buttonWidth / 4 - 5);

    inputMin.setOnKeyTyped(
        e -> updateObserver("min input", inputMin.getText() + ", "
            + inputMin2.getText()));
    inputMin2.setOnKeyTyped(
        e -> updateObserver("min input", inputMin.getText() + ", "
            + inputMin2.getText()));

    HBox minArea = new HBox(10);
    minArea.getChildren().addAll(inputMin, inputMin2);

    minMaxFields.add(inputMin);
    minMaxFields.add(inputMin2);
    minMaxFields.add(inputMax);
    minMaxFields.add(inputMax2);

    VBox minBox = new VBox(10);
    VBox maxBox = new VBox(10);

    minBox.getChildren().addAll(minTitle, minArea);
    maxBox.getChildren().addAll(maxTitle, maxArea);

    HBox minMaxBox = new HBox(20);
    minMaxBox.getChildren().addAll(minBox, maxBox);
    minMaxBox.setAlignment(Pos.CENTER);

    return new VBox(minMaxBox);
  }

  /**
   * Creates a VBox object containing the vector input fields.
   *
   * @param transform Transform2D object representing the transformation.
   * @return VBox object containing the vector input fields.
   */
  private VBox createVectorArea(Transform2D transform) {

    if (transform instanceof AffineTransform2D affine) {

      Text vector = new Text("Vector");
      vector.getStyleClass().add("text");

      TextField a = new TextField();
      inputFieldStyle(a, Double.toString(affine.getVector().getX0()), buttonHeight,
          buttonWidth / 2);

      TextField b = new TextField();
      inputFieldStyle(b, Double.toString(affine.getVector().getX1()), buttonHeight,
          buttonWidth / 2);


      b.setOnKeyTyped(
          e -> updateObserver("vector input", a.getText() + ", " + b.getText()));
      a.setOnKeyTyped(
          e -> updateObserver("vector input", a.getText() + ", " + b.getText()));

      vectorTextFields.add(a);
      vectorTextFields.add(b);
      HBox vectorInput = new HBox(20, a, b);
      return new VBox(10, vector, vectorInput);

    } else if (transform instanceof JuliaTransform julia) {

      Text complex = new Text("Complex");
      complex.getStyleClass().add("text");

      TextField c0 = new TextField();
      inputFieldStyle(c0, Double.toString(julia.getComplex().getX0()), buttonHeight,
          buttonWidth / 2 - 5);

      TextField c1 = new TextField();
      inputFieldStyle(c1, Double.toString(julia.getComplex().getX1()), buttonHeight,
          buttonWidth / 2 - 5);

      c0.setOnKeyTyped(e -> updateObserver("vector input",
          c0.getText() + ", " + c1.getText()));
      c1.setOnKeyTyped(e -> updateObserver("vector input",
          c0.getText() + ", " + c1.getText()));

      vectorTextFields.add(c0);
      vectorTextFields.add(c1);

      HBox vectorInput = new HBox(15, c0, c1);

      VBox vectorArea = new VBox(10);
      vectorArea.getChildren().addAll(complex, vectorInput);
      vectorArea.setAlignment(Pos.CENTER);

      return vectorArea;
    }
    return null;
  }

  /**
   * Creates a VBox object containing the sign input fields.
   *
   * @param julia JuliaTransform object representing the transformation.
   * @return VBox object containing the sign input fields.
   */
  private VBox createSignArea(JuliaTransform julia) {
    TextField signInput = new TextField();
    inputFieldStyle(signInput, julia.getSign() + "", buttonHeight, buttonWidth / 4 - 5);

    Text signText = new Text("Sign");
    signText.getStyleClass().add("text");
    signInput.setOnKeyTyped(e -> updateObserver("sign input", signInput.getText()));


    signTextFields.add(signInput);

    return new VBox(10, signText, signInput);
  }

  /**
   * Creates a VBox object containing the matrix input fields.
   *
   * @param transform AffineTransform2D object representing the transformation.
   * @return VBox object containing the matrix input fields.
   */
  private VBox createMatrix(AffineTransform2D transform) {
    Text matrix = new Text("Matrix");
    matrix.getStyleClass().add("text");

    TextField a00 = new TextField();
    inputFieldStyle(a00, Double.toString(transform.getMatrix().getA00()),
        buttonHeight, buttonWidth / 2);

    TextField a01 = new TextField();
    inputFieldStyle(a01, Double.toString(transform.getMatrix().getA01()),
        buttonHeight, buttonWidth / 2);

    TextField a10 = new TextField();
    inputFieldStyle(a10, Double.toString(transform.getMatrix().getA10()),
        buttonHeight, buttonWidth / 2);

    TextField a11 = new TextField();
    inputFieldStyle(a11, Double.toString(transform.getMatrix().getA11()),
        buttonHeight, buttonWidth / 2);

    a00.setOnKeyTyped(e -> updateObserver("matrix input",
        a00.getText() + ", " + a01.getText() + ", " + a10.getText() + ", " + a11.getText()));
    a01.setOnKeyTyped(e -> updateObserver("matrix input",
        a00.getText() + ", " + a01.getText() + ", " + a10.getText() + ", " + a11.getText()));
    a10.setOnKeyTyped(e -> updateObserver("matrix input",
        a00.getText() + ", " + a01.getText() + ", " + a10.getText() + ", " + a11.getText()));
    a11.setOnKeyTyped(e -> updateObserver("matrix input",
        a00.getText() + ", " + a01.getText() + ", " + a10.getText() + ", " + a11.getText()));

    matrixTextFields.add(a00);
    matrixTextFields.add(a01);
    matrixTextFields.add(a10);
    matrixTextFields.add(a11);

    HBox inputA0 = new HBox(20, a00, a01);
    HBox inputA1 = new HBox(20, a10, a11);

    return new VBox(10, matrix, inputA0, inputA1);
  }

  /**
   * Creates a VBox object containing the sidebar menu with all necessary input fields for the
   * different transform types.
   *
   * @return VBox object containing the sidebar menu.
   */
  private VBox createSideBar() {
    VBox descriptions = new VBox(20);
    descriptions.setAlignment(Pos.CENTER);
    descriptions.getStyleClass().add("vbox");


    factorialPage.getDescription().getTransforms().forEach(transform -> {
      if (transform instanceof AffineTransform2D affine) {

        VBox vector = createVectorArea(affine);
        VBox matrix = createMatrix(affine);


        VBox affineArea = new VBox(5);
        affineArea.getChildren().addAll(vector, matrix);
        descriptions.getChildren().add(affineArea);

      }
      if (transform instanceof JuliaTransform julia) {

        VBox complex = createVectorArea(julia);
        VBox signArea = createSignArea(julia);
        signArea.setAlignment(Pos.CENTER);

        VBox juliaArea = new VBox(5);
        juliaArea.getChildren().addAll(complex, signArea);
        juliaArea.setAlignment(Pos.CENTER);

        descriptions.getChildren().add(juliaArea);
      }
    });

    errorLabel.getStyleClass().add("error");
    updateErrorLabel("final", errorLabel);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(descriptions);
    scrollPane.getStyleClass().add("scroll-pane");

    scrollPane.setMinHeight(MainLogic.height * 0.25);
    scrollPane.setMaxHeight(MainLogic.height * 0.25);


    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);

    VBox sidebarMenu = new VBox(20);

    sidebarMenu.setAlignment(Pos.CENTER);
    sidebarMenu.getStyleClass().add("vbox");

    VBox minMaxBox = chooseMinMaxArea();
    Button applyChangesButton = getApplyChangesButton();
    sidebarMenu.getChildren()
        .addAll(minMaxBox, scrollPane, errorLabel, applyChangesButton);

    return sidebarMenu;
  }

  /**
   * Creates a Button object for applying changes to the transforms.
   * The button will call the updateObserver method with the necessary information for saving the
   * validating and saving the input.
   *
   * @return Button object for applying changes to the transforms.
   */
  private Button getApplyChangesButton() {
    Button save = new Button("Apply Changes");
    save.setMinWidth(buttonWidth);
    save.setOnAction(e -> {
      StringBuilder vectors = new StringBuilder();
      StringBuilder matrix = new StringBuilder();
      StringBuilder sign = new StringBuilder();
      StringBuilder minMax = new StringBuilder();

      boolean failed = false;

      try {
        getInput(vectors, matrix, vectorTextFields, matrixTextFields);
        getInput(sign, minMax, signTextFields, minMaxFields);
      } catch (Exception exception) {
        failed = true;
      }
      // Checks that there are no error before closing the stage
      if (failed) {
        showError("final", "You can not apply with invalid input");
      } else {
        updateObserver("save vectors", vectors.toString());
        updateObserver("save matrix", matrix.toString());
        updateObserver("save signs", sign.toString());
        updateObserver("edit description", minMax.toString());
      }
    });
    return save;
  }

  /**
   * Gets the input from the input fields and appends it to the StringBuilder objects.
   *
   * @param vectors          StringBuilder object representing the vector input.
   * @param matrix           StringBuilder object representing the matrix input.
   * @param vectorTextFields List of TextField objects representing the vector input fields.
   * @param matrixTextFields List of TextField objects representing the matrix input fields.
   */
  private void getInput(StringBuilder vectors, StringBuilder matrix,
                        List<TextField> vectorTextFields, List<TextField> matrixTextFields) {
    for (TextField textField : vectorTextFields) {
      Double.parseDouble(textField.getText());
      vectors.append(textField.getText()).append(",");
    }
    for (TextField textField : matrixTextFields) {
      Double.parseDouble(textField.getText());
      matrix.append(textField.getText()).append(",");
    }
  }

  /**
   * Sets the style of the input fields.
   *
   * @param inputField TextField object representing the input field.
   * @param text       String representing the text in the input field.
   * @param height     int representing the height of the input field.
   * @param width      int representing the width of the input field.
   */
  public void inputFieldStyle(TextField inputField, String text, int height, int width) {
    inputField.setMinHeight(height);
    inputField.setMaxWidth(width);
    inputField.setText(text);
  }

  /**
   * Returns the menu as a VBox object.
   *
   * @return VBox object representing the menu.
   */
  @Override
  public VBox getMenu() {
    return menu;
  }

  /**
   * Updates the error label with the error message.
   *
   * @param key   String representing the placement of the error message.
   * @param label Label object representing the error label.
   */
  private void updateErrorLabel(String key, Label label) {
    String errorMessage = errorMap.getOrDefault(key, "");
    label.setText(errorMessage.isEmpty() ? "" : errorMessage);
  }

  /**
   * Shows an error message in the error label.
   *
   * @param placement String representing the placement of the error message.
   * @param message   String representing the error message.
   */

  public void showError(String placement, String message) {
    errorMap.put(placement, message);
    switch (placement) {
      case "InputMinMax":
        updateErrorLabel("InputMinMax", errorLabel);
        break;
      case "InputMatrix":
        updateErrorLabel("InputMatrix", errorLabel);
        break;
      case "InputVector":
        updateErrorLabel("InputVector", errorLabel);
        break;
      case "InputSign":
        updateErrorLabel("InputSign", errorLabel);
        break;
      case "final":
        updateErrorLabel("final", errorLabel);
        break;
      default:
        break;
    }
  }
}
