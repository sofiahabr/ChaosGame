package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.EditTransformController;
import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditTransformsMenu extends Menu {
  private FactorialPage factorialPage;
  private HashMap<String, String> errorMap = new HashMap<>();
  Label minMaxError = new Label();
  Label matrixErrorLabel = new Label();
  Label vectorErrorLabel = new Label();
  Label signErrorLabel = new Label();
  Label finalErrorLabel = new Label();


  public EditTransformsMenu(ScreenController screenController, FactorialPage factorialPage) {
    super(screenController);
    this.factorialPage = factorialPage;
    addObserver(new EditTransformController(this, factorialPage));

    setUp();
  }

  @Override
  public void setUp() {
    Stage editStage = new Stage();

    editStage.setTitle("Edit Transforms");
    HBox descriptions = new HBox(40);

    // Create min/ max area
    Text minTitle = new Text(" Min: ");
    Text maxTitle = new Text(" Max: ");
    maxTitle.getStyleClass().add("heading");
    minTitle.getStyleClass().add("heading");

    TextField inputMax = new TextField();
    inputFieldStyle(inputMax, factorialPage.getDescription().getMaxCoords().getX0() + "", 30, 50);

    TextField inputMax2 = new TextField();
    inputFieldStyle(inputMax2, factorialPage.getDescription().getMaxCoords().getX1() + "", 30, 50);

    inputMax.setOnKeyTyped(
        e -> updateObserver("InputMinMax", inputMax.getText() + ", " + inputMax2.getText()));
    inputMax2.setOnKeyTyped(
        e -> updateObserver("InputMinMax", inputMax.getText() + ", " + inputMax2.getText()));

    HBox maxArea = new HBox(10);
    maxArea.getChildren().addAll(inputMax, inputMax2);

    // Min area
    TextField inputMin = new TextField();
    inputFieldStyle(inputMin, factorialPage.getDescription().getMinCoords().getX0() + "", 30, 50);
    TextField inputMin2 = new TextField();
    inputFieldStyle(inputMin2, factorialPage.getDescription().getMinCoords().getX1() + "", 30, 50);

    inputMin.setOnKeyTyped(
        e -> updateObserver("min input", inputMin.getText() + ", " + inputMin2.getText()));
    inputMin2.setOnKeyTyped(
        e -> updateObserver("max input", inputMin.getText() + ", " + inputMin2.getText()));

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
        inputFieldStyle(a, Double.toString(affine.getVector().getX0()), 30, 60);

        TextField b = new TextField();
        inputFieldStyle(b, Double.toString(affine.getVector().getX1()), 30, 60);
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
        inputFieldStyle(a00, Double.toString(affine.getMatrix().getA00()), 30, 60);

        TextField a01 = new TextField();
        inputFieldStyle(a01, Double.toString(affine.getMatrix().getA01()), 30, 60);

        TextField a10 = new TextField();
        inputFieldStyle(a10, Double.toString(affine.getMatrix().getA10()), 30, 60);

        TextField a11 = new TextField();
        inputFieldStyle(a11, Double.toString(affine.getMatrix().getA11()), 30, 60);

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
        inputFieldStyle(c0, Double.toString(julia.getComplex().getX0()), 30, 60);

        TextField c1 = new TextField();
        inputFieldStyle(c1, Double.toString(julia.getComplex().getX1()), 30, 60);

        c0.setOnKeyTyped(e -> updateObserver("vector input", c0.getText() + ", " + c1.getText()));
        c1.setOnKeyTyped(e -> updateObserver("vector input", c0.getText() + ", " + c1.getText()));

        TextField signInput = new TextField();
        inputFieldStyle(signInput, julia.getSign() + "", 30, 50);

        Text signText = new Text("Sign");
        signText.getStyleClass().add("text");
        signInput.setOnKeyTyped(e -> updateObserver("sign input", signInput.getText()));

        vectorTextFields.add(c0);
        vectorTextFields.add(c1);

        signTextFields.add(signInput);

        HBox vectorInput = new HBox(10, c0, c1);

        VBox juliaArea = new VBox(10);
        juliaArea.getChildren().addAll(complex, vectorInput,signText, signInput);
        juliaArea.setAlignment(Pos.CENTER);

        descriptions.getChildren().add(juliaArea);
      }
    }
    minMaxError.getStyleClass().add("error");
    updateErrorLabel("InputMinMax", minMaxError);

    vectorErrorLabel.getStyleClass().add("error");
    updateErrorLabel("InputVector", vectorErrorLabel);

    matrixErrorLabel.getStyleClass().add("error");
    updateErrorLabel("InputMatrix", matrixErrorLabel);

    signErrorLabel.getStyleClass().add("error");
    updateErrorLabel("InputSign", signErrorLabel);

    finalErrorLabel.getStyleClass().add("error");
    updateErrorLabel("final", finalErrorLabel);

    Button save = new Button("Save");
    save.setOnAction(e -> {
      String vectors = "";
      for (TextField textField : vectorTextFields) {
        vectors += textField.getText() + ", ";
      }
      String matrix = "";
      for (TextField textField : matrixTextFields) {
        matrix += textField.getText() + ", ";
      }
      StringBuilder sign = new StringBuilder();
      for (TextField textField : signTextFields) {
        sign.append(textField.getText()).append(",");
      }
      updateObserver("save vectors", vectors.toString());
      updateObserver("save matrix", matrix.toString());
      updateObserver("save signs", sign.toString());
      updateObserver("edit description",
          inputMin.getText() + ", " + inputMin2.getText() + ", " + inputMax.getText() + ", " +
              inputMax2.getText());

      // Checks that there are no error before closing the stage
      if (!matrixErrorLabel.getText().isEmpty() || !vectorErrorLabel.getText().isEmpty() ||
          !minMaxError.getText().isEmpty() || !signErrorLabel.getText().isEmpty() ) {
        showError("final", "You can not save without entering ONLY numbers");
      } else {
        editStage.close();
      }
    });

    VBox stage = new VBox(20);
    stage.setAlignment(Pos.CENTER);
    stage.setPadding(new Insets(40));

    stage.getChildren()
        .addAll(editTransformsTitle, minMaxBox, minMaxError, descriptions, signErrorLabel, vectorErrorLabel,
            matrixErrorLabel, finalErrorLabel, save);

    Scene editScene = new Scene(stage);
    editScene.getStylesheets().add("/style/style.css");

    editStage.setScene(editScene);
    editStage.show();
  }

  public void inputFieldStyle(TextField inputField, String text, int height, int width) {
    inputField.setMinHeight(height);
    inputField.setMaxWidth(width);
    inputField.setText(text);
  }

  @Override
  public Node getMenu() {
    return null;
  }

  private void updateErrorLabel(String key, Label label) {
    String errorMessage = errorMap.getOrDefault(key, "");
    label.setText(errorMessage.isEmpty() ? "" : errorMessage);
  }

  public void showError(String placement, String message) {
    errorMap.put(placement, message);
    switch (placement) {
      case "InputMinMax":
        updateErrorLabel("InputMinMax", minMaxError);
        break;
      case "InputMatrix":
        updateErrorLabel("InputMatrix", matrixErrorLabel);
        break;
      case "InputVector":
        updateErrorLabel("InputVector", vectorErrorLabel);
        break;
      case "InputSign":
        updateErrorLabel("InputSign", signErrorLabel);
      case "final":
        updateErrorLabel("final", finalErrorLabel);

    }
  }
}
