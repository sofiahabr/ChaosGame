package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.EditTransformController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
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
import javafx.stage.Stage;

public class EditTransformsMenu extends Menu {
  private FactorialPage factorialPage;
  private HashMap<String, String> errorMap = new HashMap<>();
  private int buttonHeight = Math.round(MainLogic.height*0.03f);
  private int buttonWidth = 200;
  private VBox menu = new VBox(20);
  Label minMaxError = new Label();
  Label errorLabel = new Label();


  public EditTransformsMenu(ScreenController screenController, FactorialPage factorialPage) {
    super(screenController);
    this.factorialPage = factorialPage;
    addObserver(new EditTransformController(this, factorialPage));

    if (!factorialPage.getDescription().getTransforms().isEmpty()){

      setUp();
    }
  }

  @Override
  public void setUp() {
    menu.getStyleClass().add("vbox");
    this.menu = createSideBar();
  }

  private VBox createSideBar() {
    Stage editStage = new Stage();

//    editStage.setTitle("Edit Transforms");
    VBox descriptions = new VBox(20);
    descriptions.setAlignment(Pos.CENTER);
    descriptions.getStyleClass().add("vbox");

    // Create min/ max area
    Text minTitle = new Text(" Min: ");
    Text maxTitle = new Text(" Max: ");
    maxTitle.getStyleClass().add("heading");
    minTitle.getStyleClass().add("heading");

    TextField inputMax = new TextField();
    inputFieldStyle(inputMax, factorialPage.getDescription().getMaxCoords().getX0() + "", buttonHeight, buttonWidth/4-5);

    TextField inputMax2 = new TextField();
    inputFieldStyle(inputMax2, factorialPage.getDescription().getMaxCoords().getX1() + "", buttonHeight, buttonWidth/4-5);

    inputMax.setOnKeyTyped(
        e -> updateObserver("max input", inputMax.getText() + ", " + inputMax2.getText()));
    inputMax2.setOnKeyTyped(
        e -> updateObserver("max input", inputMax.getText() + ", " + inputMax2.getText()));

    HBox maxArea = new HBox(10);
    maxArea.getChildren().addAll(inputMax, inputMax2);

    // Min area
    TextField inputMin = new TextField();
    inputFieldStyle(inputMin, factorialPage.getDescription().getMinCoords().getX0() + "", buttonHeight, buttonWidth/4-5);
    TextField inputMin2 = new TextField();
    inputFieldStyle(inputMin2, factorialPage.getDescription().getMinCoords().getX1() + "", buttonHeight, buttonWidth/4-5);

    inputMin.setOnKeyTyped(
        e -> updateObserver("min input", inputMin.getText() + ", " + inputMin2.getText()));
    inputMin2.setOnKeyTyped(
        e -> updateObserver("min input", inputMin.getText() + ", " + inputMin2.getText()));

    HBox minArea = new HBox(10);
    minArea.getChildren().addAll(inputMin, inputMin2);

    VBox minBox = new VBox(10);
    VBox maxBox = new VBox(10);


    minBox.getChildren().addAll(minTitle, minArea);
    maxBox.getChildren().addAll(maxTitle, maxArea);

    HBox hBox = new HBox(20);
    hBox.getChildren().addAll(minBox, maxBox);
    hBox.setAlignment(Pos.CENTER);

    VBox minMaxBox = new VBox(hBox, minMaxError);

    List<TextField> vectorTextFields = new ArrayList<>();
    List<TextField> matrixTextFields = new ArrayList<>();
    List<TextField> signTextFields = new ArrayList<>();

    factorialPage.getDescription().getTransforms().forEach(transform -> {
      if (transform instanceof AffineTransform2D) {
        AffineTransform2D affine = (AffineTransform2D) transform;

        Text vector = new Text("Vector");
        vector.getStyleClass().add("text");

        TextField a = new TextField();
        inputFieldStyle(a, Double.toString(affine.getVector().getX0()), buttonHeight, buttonWidth / 2);

        TextField b = new TextField();
        inputFieldStyle(b, Double.toString(affine.getVector().getX1()), buttonHeight, buttonWidth / 2);
        HBox vectorInput = new HBox(20, a, b);

        b.setOnKeyTyped(
            e -> updateObserver("vector input", a.getText() + ", " + b.getText()));
        a.setOnKeyTyped(
            e -> updateObserver("vector input", a.getText() + ", " + b.getText()));

        vectorTextFields.add(a);
        vectorTextFields.add(b);

        Text matrix = new Text("Matrix");
        matrix.getStyleClass().add("text");

        TextField a00 = new TextField();
        inputFieldStyle(a00, Double.toString(affine.getMatrix().getA00()), buttonHeight, buttonWidth/2);

        TextField a01 = new TextField();
        inputFieldStyle(a01, Double.toString(affine.getMatrix().getA01()), buttonHeight, buttonWidth / 2);

        TextField a10 = new TextField();
        inputFieldStyle(a10, Double.toString(affine.getMatrix().getA10()), buttonHeight, buttonWidth/2);

        TextField a11 = new TextField();
        inputFieldStyle(a11, Double.toString(affine.getMatrix().getA11()), buttonHeight, buttonWidth / 2);

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

        VBox affineArea = new VBox(10);
        affineArea.getChildren().addAll(vector, vectorInput, matrix, inputA0, inputA1);
        descriptions.getChildren().add(affineArea);

      }
      if (transform instanceof JuliaTransform) {

        JuliaTransform julia = (JuliaTransform) transform;

        Text complex = new Text("Complex");
        complex.getStyleClass().add("text");

        TextField c0 = new TextField();
        inputFieldStyle(c0, Double.toString(julia.getComplex().getX0()), buttonHeight, buttonWidth/2-5);

        TextField c1 = new TextField();
        inputFieldStyle(c1, Double.toString(julia.getComplex().getX1()), buttonHeight, buttonWidth/2-5);

        c0.setOnKeyTyped(e -> updateObserver("vector input", c0.getText() + ", " + c1.getText()));
        c1.setOnKeyTyped(e -> updateObserver("vector input", c0.getText() + ", " + c1.getText()));

        TextField signInput = new TextField();
        inputFieldStyle(signInput, julia.getSign() + "", buttonHeight, buttonWidth/4-5);

        Text signText = new Text("Sign");
        signText.getStyleClass().add("text");
        signInput.setOnKeyTyped(e -> updateObserver("sign input", signInput.getText()));

        vectorTextFields.add(c0);
        vectorTextFields.add(c1);

        signTextFields.add(signInput);

        HBox vectorInput = new HBox(15, c0, c1);

        VBox juliaArea = new VBox(10);
        juliaArea.getChildren().addAll(complex, vectorInput,signText, signInput);
        juliaArea.setAlignment(Pos.CENTER);

        descriptions.getChildren().add(juliaArea);
      }
    });

    errorLabel.getStyleClass().add("error");
    updateErrorLabel("final", errorLabel);

    Button save = new Button("Apply Changes");
    save.setMinWidth(buttonWidth);
    save.setOnAction(e -> {
      StringBuilder vectors = new StringBuilder();
      StringBuilder matrix = new StringBuilder();
      StringBuilder sign = new StringBuilder();

      boolean failed = false;

      try {
        for (TextField textField : vectorTextFields) {
          Double.parseDouble(textField.getText());
          vectors.append(textField.getText()).append(", ");
        }
        for (TextField textField : matrixTextFields) {
          Double.parseDouble(textField.getText());
          matrix.append(textField.getText()).append(", ");
        }
        for (TextField textField : signTextFields) {
          Double.parseDouble(textField.getText());
          sign.append(textField.getText()).append(",");
        }
      }catch (Exception exception) {
          failed = true;
        }

      // Checks that there are no error before closing the stage
      if (failed) {
        showError("final", "You can not apply with invalid input");
      } else {
        updateObserver("save vectors", vectors.toString());
        updateObserver("save matrix", matrix.toString());
        updateObserver("save signs", sign.toString());
        updateObserver("edit description",
            inputMin.getText() + ", " + inputMin2.getText() + ", " + inputMax.getText() + ", " +
                inputMax2.getText());
      }
    });

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(descriptions);
    scrollPane.getStyleClass().add("scroll-pane");

    scrollPane.setMinHeight(MainLogic.height*0.25);
    scrollPane.setMaxHeight(MainLogic.height*0.25);


    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);

    VBox menu = new VBox(5);

    menu.setAlignment(Pos.CENTER);
    menu.getStyleClass().add("vbox");

    Text transformTitle = new Text("Transforms");
    transformTitle.getStyleClass().add("heading");

    menu.getChildren()
        .addAll(minMaxBox, minMaxError, transformTitle, scrollPane, errorLabel, save);

    return menu;
  }

  public void inputFieldStyle(TextField inputField, String text, int height, int width) {
    inputField.setMinHeight(height);
    inputField.setMaxWidth(width);
    inputField.setText(text);
  }

  @Override
  public VBox getMenu() {
    return menu;
  }

  private void updateErrorLabel(String key, Label label) {
    String errorMessage = errorMap.getOrDefault(key, "");
    label.setText(errorMessage.isEmpty() ? "" : errorMessage);
  }

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

    }
  }
}
