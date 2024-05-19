package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditTransformsMenu extends Menu {
  private FactorialPage factorialPage;

  public EditTransformsMenu(ScreenController screenController,
                            FactorialPage factorialPage) {
    super(screenController);
    this.factorialPage = factorialPage;
    addObserver(new FactorialPageController(screenController, factorialPage));

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

    HBox maxArea = new HBox(10);
    maxArea.getChildren().addAll(inputMax, inputMax2);

    TextField inputMin = new TextField();
    inputFieldStyle(inputMin, factorialPage.getDescription().getMinCoords().getX0() + "", 30, 50);

    TextField inputMin2 = new TextField();
    inputFieldStyle(inputMin2, factorialPage.getDescription().getMinCoords().getX1() + "", 30, 50);

    HBox minArea = new HBox(10);
    minArea.getChildren().addAll(inputMin, inputMin2);

    VBox minBox = new VBox(10);
    VBox maxBox = new VBox(10);

    minBox.getChildren().addAll(minTitle, minArea);
    maxBox.getChildren().addAll(maxTitle, maxArea);

    HBox minMaxBox = new HBox(20);
    minMaxBox.getChildren().addAll(minBox, maxBox);
    minMaxBox.setAlignment(Pos.CENTER);

    List<TextField> vectorTextFields = new ArrayList<>();
    List<TextField> matrixTextFields = new ArrayList<>();

    for (Transform2D transform : factorialPage.getDescription().getTransforms()) {
      if (transform instanceof AffineTransform2D) {
        AffineTransform2D affine = (AffineTransform2D) transform;

        Text vector = new Text("Vector");
        vector.getStyleClass().add("text");

        TextField a = new TextField();
        inputFieldStyle(a, Double.toString(affine.getVector().getX0()), 30, 60);

        TextField b = new TextField();
        inputFieldStyle(b, Double.toString(affine.getVector().getX1()), 30, 60);
        HBox vectorInput = new HBox(20, a, b);

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

        vectorTextFields.add(c0);
        vectorTextFields.add(c1);

        HBox vectorInput = new HBox(20, c0, c1);

        VBox juliaArea = new VBox(10);
        juliaArea.getChildren().addAll(complex, vectorInput);
        descriptions.getChildren().add(juliaArea);
      }
    }
    Button add = new Button("+");
    add.setAlignment(Pos.CENTER);
    add.setOnAction(e-> updateObserver("button clicked", "add"));

    descriptions.getChildren().add(add);
    // TODO: Eventuelt lage metode for denne

    Button save = new Button("Save");
    save.setOnAction(e -> {
      String vectors = "";
      for(TextField textField : vectorTextFields) {
        vectors += textField.getText() + ", ";
      }
      String matrix = "";
      for(TextField textField : matrixTextFields) {
        matrix += textField.getText() + ", ";
      }
      updateObserver("save vectors", vectors);
      updateObserver("save matrix", matrix);
      updateObserver("edit description", inputMin.getText() + ", " + inputMin2.getText() + ", " + inputMax.getText() + ", " + inputMax2.getText());
      editStage.close();
    });

    VBox stage = new VBox(40);
    stage.setAlignment(Pos.CENTER);
    stage.setPadding(new Insets(40));

    stage.getChildren().addAll(minMaxBox, descriptions, save);

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
}
