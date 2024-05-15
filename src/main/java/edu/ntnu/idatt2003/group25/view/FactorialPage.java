package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Matrix2x2;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FactorialPage extends View {

  public BorderPane borderPane = new BorderPane();
  public VBox sidebarMenu;
  public ScreenController screenController;
  public ChaosGameDescription description;
  FactorialPageController controller;
  public FactorialPage(ScreenController screenController) {
    this.controller = new FactorialPageController(screenController);
    this.screenController = screenController;
    addObserver(controller);
    }

  @Override
  public void setUp() {
    update();
//
//    TopBarMenu topBarMenu = new TopBarMenu(screenController);
//    topBarMenu.setUp();
//
//    borderPane.setTop(topBarMenu.getBox());

    borderPane.setStyle("-fx-background-color: white");


    if(MainView.description.getTransforms().getFirst().getClass().getName().contains("JuliaTransform")){
      juliaSidebar();
    } else if (MainView.description.getTransforms().getFirst().getClass().getName().contains("AffineTransform2D")) {
      affineSidebar();
    }

    HBox topBox = new HBox(0);
    borderPane.setTop(topBox);

    borderPane.setLeft(sidebarMenu);
  }

  @Override
  public void update() {
    this.description = MainView.description;
  }

  private void createSideBar() {
    sidebarMenu = new VBox(40);
    sidebarMenu.getStyleClass().add("vbox");
//    sidebarMenu.setStyle("-fx-background-color: #D9D9D9");
    sidebarMenu.setPadding(new Insets(20));
  }

  public VBox chooseStepsField() {
    Text chooseSteps = new Text("Choose steps");
    chooseSteps.getStyleClass().add("heading");

//    chooseSteps.setStyle("-fx-font-size: 14; ");

    TextField inputSteps = new TextField();
    inputFieldStyle(inputSteps, "Ex. 500...", 30, 180);

    VBox stepsArea = new VBox(10);
    stepsArea.getChildren().addAll(chooseSteps, inputSteps);
    stepsArea.setAlignment(Pos.CENTER);

    // send to controller
    inputSteps.setOnKeyTyped(e-> updateObserver("register steps", inputSteps.getText()));

    return stepsArea;
  }
  private HBox chooseMinMaxField() {

    // Create min/ max area
    Text minTitle = new Text(" Min: ");
    Text maxTitle = new Text(" Max: ");
    maxTitle.getStyleClass().add("heading");
    minTitle.getStyleClass().add("heading");


//    minTitle.setStyle("-fx-font-size: 14;");
//    maxTitle.setStyle("-fx-font-size: 14;");

    TextField inputMax = new TextField();
    inputFieldStyle(inputMax,"ex. 1", 30, 80);
    inputMax.setOnKeyTyped(e-> updateObserver("max input", inputMax.getText() + ", " + inputMax.getText()));

    TextField inputMin = new TextField();
    inputFieldStyle(inputMin,"ex. 0", 30, 80);
    inputMax.setOnKeyTyped(e-> updateObserver("min input", inputMax.getText() + ", " + inputMax.getText()));


    VBox minBox = new VBox(10);
    VBox maxBox = new VBox(10);

    minBox.getChildren().addAll(minTitle, inputMin);
    maxBox.getChildren().addAll(maxTitle, inputMax);

    HBox minMaxBox = new HBox(20);
    minMaxBox.getChildren().addAll(minBox, maxBox);
    minMaxBox.setAlignment(Pos.CENTER);

    return minMaxBox;
  }

  private VBox gameButtons() {

    Button playButton = new Button("Play");
    addStyle(playButton,"green", 180);
    playButton.setOnAction(e-> updateObserver("button clicked", "play"));

    Button resetButton = new Button("Reset");
    addStyle(resetButton,"red",80);
    resetButton.setOnAction(e-> updateObserver("button clicked", "reset"));

    Button saveButton = new Button("Save to file");
    addStyle(saveButton, "black", 80);
    saveButton.setOnAction(e-> updateObserver("button clicked", "save"));


    Button addTransformButton = new Button("Add transform");
    addStyle(addTransformButton,"grey",180);
    addTransformButton.setOnAction(e-> updateObserver("button clicked", "add"));


    HBox topButtonBox = new HBox(20);
    topButtonBox.getChildren().addAll(saveButton, resetButton);

    VBox buttonBox = new VBox(30);
    buttonBox.getChildren().addAll(addTransformButton, topButtonBox, playButton);
//    buttonBox.setAlignment(Pos.BASELINE_CENTER);
    buttonBox.setAlignment(Pos.CENTER);

    return buttonBox;
  }

  public void juliaSidebar() {
    createSideBar();
    sidebarMenu.getChildren().addAll(chooseStepsField(),chooseMinMaxField(),createVector(),gameButtons());
  }

  public void affineSidebar() {
    createSideBar();
    sidebarMenu.getChildren().addAll(chooseStepsField(),chooseMinMaxField(), createVector(),
        createMatrix(), gameButtons());
  }

  public VBox createVector() {
    Text chooseVector = new Text("Choose vector:");
    chooseVector.getStyleClass().add("heading");

//    chooseVector.setStyle("-fx-font-size: 14; ");

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
//    createMatrix.setStyle("-fx-font-size: 14");

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

  public void inputFieldStyle(TextField inputField, String promptText, int height,int width ) {
    inputField.setMinHeight(height);
    inputField.setMaxWidth(width);
//    inputField.setStyle("-fx-background-radius: 10; -fx-font-size: 14");
    inputField.setPromptText(promptText);
  }

  public void addStyle(Button button, String color, int width) {
//    String buttonStyle = "-fx-background-radius: 10; -fx-min-height: 30; "
//        + "-fx-text-fill: white; -fx-font-size: 12;";
    String colorInit = "-fx-background-color: " + color + ";" ;
    String widthInit = "-fx-min-width: " + width + ";" ;

    button.setStyle(colorInit + widthInit);
  }

  @Override
  public BorderPane getPane() {
    return borderPane;
  }

}
