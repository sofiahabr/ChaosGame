package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class SideMenu extends Menu {
  public VBox sideMenu = new VBox(20);
  public SideMenu(ScreenController screenController, FactorialPage factorialPage) {
    super(screenController);
    addObserver(new FactorialPageController(screenController, factorialPage));
  }
  void createSideBar() {
    sideMenu = new VBox(40);
    sideMenu.getStyleClass().add("vbox");
    sideMenu.setPadding(new Insets(20));
  }

  @Override
  public abstract void setUp() ;


  @Override
  public VBox getMenu() {
    return sideMenu;
  }

  public VBox chooseStepsField() {
    Text chooseSteps = new Text("Choose steps");
    chooseSteps.getStyleClass().add("heading");

    TextField inputSteps = new TextField();
    inputFieldStyle(inputSteps, "Ex. 500...", 30, 180);

    VBox stepsArea = new VBox(10);
    stepsArea.getChildren().addAll(chooseSteps, inputSteps);
    stepsArea.setAlignment(Pos.CENTER);

    // send to controller
    inputSteps.setOnKeyTyped(e-> updateObserver("register steps", inputSteps.getText()));

    return stepsArea;
  }
  HBox chooseMinMaxField() {

    // Create min/ max area
    Text minTitle = new Text(" Min: ");
    Text maxTitle = new Text(" Max: ");
    maxTitle.getStyleClass().add("heading");
    minTitle.getStyleClass().add("heading");


    TextField inputMax = new TextField();
    inputFieldStyle(inputMax,"1", 30, 35);

    TextField inputMax2 = new TextField();
    inputFieldStyle(inputMax2,"1", 30, 35);

    HBox maxArea = new HBox(10);
    maxArea.getChildren().addAll(inputMax, inputMax2);

    inputMax.setOnKeyTyped(e-> updateObserver("max input", inputMax.getText() + ", " + inputMax2.getText()));
    inputMax2.setOnKeyTyped(e-> updateObserver("max input", inputMax.getText() + ", " + inputMax2.getText()));


    TextField inputMin = new TextField();
    inputFieldStyle(inputMin,"0", 30, 35);

    TextField inputMin2 = new TextField();
    inputFieldStyle(inputMin2,"0", 30, 35);

    HBox minArea = new HBox(10);
    minArea.getChildren().addAll(inputMin, inputMin2);

    inputMin2.setOnKeyTyped(e-> updateObserver("min input", inputMin.getText() + ", " + inputMin2.getText()));
    inputMin2.setOnKeyTyped(e-> updateObserver("min input", inputMin.getText() + ", " + inputMin2.getText()));


    VBox minBox = new VBox(10);
    VBox maxBox = new VBox(10);

    minBox.getChildren().addAll(minTitle, minArea);
    maxBox.getChildren().addAll(maxTitle, maxArea);

    HBox minMaxBox = new HBox(20);
    minMaxBox.getChildren().addAll(minBox, maxBox);
    minMaxBox.setAlignment(Pos.CENTER);

    return minMaxBox;
  }

  VBox gameButtons() {

    Button playButton = new Button("Play");
    playButton.getStyleClass().add("button2");
    addStyle(playButton,"green", 180);
    playButton.setOnAction(e-> updateObserver("button clicked", "play"));

    Button resetButton = new Button("Reset canvas");
    resetButton.getStyleClass().add("button2");
    addStyle(resetButton,"red",180);
    resetButton.setOnAction(e-> updateObserver("button clicked", "reset"));

    Button saveButton = new Button("Save to file");
    saveButton.getStyleClass().add("button2");
    addStyle(saveButton, "black", 180);
    saveButton.setOnAction(e-> updateObserver("button clicked", "save"));

    Button addTransformButton = new Button("Add transform");
    addTransformButton.getStyleClass().add("button2");
    addStyle(addTransformButton,"grey",180);
    addTransformButton.setOnAction(e-> updateObserver("button clicked", "add"));

    Button applyEditsButton = new Button("Edit transformation");
    applyEditsButton.getStyleClass().add("button2");
    addStyle(applyEditsButton,"blue",180);
    applyEditsButton.setOnAction(e-> updateObserver("button clicked", "edit"));


    VBox buttonBox = new VBox(10);
    buttonBox.getChildren().addAll(addTransformButton, applyEditsButton, saveButton, resetButton, playButton);
    buttonBox.setAlignment(Pos.CENTER);

    return buttonBox;
  }

  public VBox createVector() {
    Text chooseVector = new Text("Choose vector:");
    chooseVector.getStyleClass().add("heading");


    TextField inputVector1 = new TextField();
    inputFieldStyle(inputVector1,"x0", 30, 80);
    TextField inputVector2 = new TextField();
    inputFieldStyle(inputVector2,"x1", 30, 80);

    inputVector1.setOnKeyTyped(e-> updateObserver("vector input", inputVector1.getText() + ", " + inputVector2.getText()));
    inputVector1.setOnKeyTyped(e-> updateObserver("vector input", inputVector1.getText() + ", " + inputVector2.getText()));

    HBox vectorInputs = new HBox(20);
    vectorInputs.getChildren().addAll(inputVector1,inputVector2);

    VBox vectorArea = new VBox(10);
    vectorArea.getChildren().addAll(chooseVector, vectorInputs);
    vectorArea.setAlignment(Pos.CENTER);
    return vectorArea;
  }

  public VBox createMatrix() {
    Text createMatrix = new Text("Choose matrix:");
    createMatrix.getStyleClass().add("heading");

    TextField inputA = new TextField();
    inputFieldStyle(inputA,"a", 30, 80);
    TextField inputB = new TextField();
    inputFieldStyle(inputB,"b", 30, 80);
    TextField inputC = new TextField();
    inputFieldStyle(inputC,"c", 30, 80);
    TextField inputD = new TextField();
    inputFieldStyle(inputD,"d", 30, 80);

    inputA.setOnKeyTyped(e-> updateObserver("matrix input", inputA.getText() + ", " + inputB.getText() + ", " + inputC.getText() + ", " + inputD.getText()));
    inputB.setOnKeyTyped(e-> updateObserver("matrix input", inputA.getText() + ", " + inputB.getText() + ", " + inputC.getText() + ", " + inputD.getText()));
    inputC.setOnKeyTyped(e-> updateObserver("matrix input", inputA.getText() + ", " + inputB.getText() + ", " + inputC.getText() + ", " + inputD.getText()));
    inputD.setOnKeyTyped(e-> updateObserver("matrix input", inputA.getText() + ", " + inputB.getText() + ", " + inputC.getText() + ", " + inputD.getText()));


    HBox inputAB = new HBox(20, inputA,inputB);
    HBox inputCD = new HBox(20, inputC, inputD);

    VBox matrixArea = new VBox(10);
    matrixArea.getChildren().addAll(createMatrix,inputAB,inputCD);
    matrixArea.setAlignment(Pos.CENTER);

    return matrixArea;
  }
}
