package edu.ntnu.idatt2003.group25.view;

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

  public FactorialPage() { setUp(); }

  @Override
  public void setUp() {
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
  private void createSideBar() {
    sidebarMenu = new VBox(40);
    sidebarMenu.setStyle("-fx-background-color: #D9D9D9");
    sidebarMenu.setPadding(new Insets(20));
  }

  public VBox chooseStepsField() {

    // Create choose steps area
    Text chooseSteps = new Text("Choose steps");
    chooseSteps.setStyle("-fx-font-size: 14; ");

    TextField inputSteps = new TextField();
    inputSteps.setPromptText(" Ex. 500...");
    inputSteps.setMaxWidth(180);
    inputSteps.setMinHeight(30);
    inputSteps.setStyle("-fx-background-radius: 10");

    VBox stepsArea = new VBox(10);
    stepsArea.getChildren().addAll(chooseSteps, inputSteps);
    stepsArea.setAlignment(Pos.CENTER);

    return stepsArea;
  }
  private HBox chooseMinMaxField() {

    // Create min/ max area
    Text minTitle = new Text(" Min: ");
    Text maxTitle = new Text(" Max: ");

    minTitle.setStyle("-fx-font-size: 14;");
    maxTitle.setStyle("-fx-font-size: 14;");

    TextField inputMax = new TextField();
    inputMax.setMaxWidth(80);
    inputMax.setMinHeight(30);
    inputMax.setStyle("-fx-background-radius: 10");

    TextField inputMin = new TextField();
    inputMin.setMaxWidth(80);
    inputMin.setMinHeight(30);
    inputMin.setStyle("-fx-background-radius: 10");

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
    String buttonStyle = "-fx-background-radius: 10; -fx-min-height: 30; "
        + "-fx-text-fill: white; -fx-font-size: 12;";

    Button playButton = new Button("Play");
    playButton.setStyle(buttonStyle + "-fx-background-color: black; -fx-min-width: 80; ");

    Button resetButton = new Button("Reset");
    resetButton.setStyle(buttonStyle + "-fx-background-color: red; -fx-min-width: 80; ");

    Button saveButton = new Button("Save to file");
    saveButton.setStyle(buttonStyle + "-fx-background-color: green; -fx-min-width: 180; ");

    Button addTransformButton = new Button("Add transform");
    addTransformButton.setStyle(buttonStyle + " -fx-background-color: red; -fx-min-width: 180");

    HBox topButtonBox = new HBox(20);
    topButtonBox.getChildren().addAll(playButton, resetButton);

    VBox buttonBox = new VBox(30);
    buttonBox.getChildren().addAll(addTransformButton, topButtonBox, saveButton);
    buttonBox.setAlignment(Pos.BASELINE_CENTER);
    buttonBox.setPadding(new Insets(0, 0, 100, 0));

    return buttonBox;
  }

  public void juliaSidebar() {
    createSideBar();
    sidebarMenu.getChildren().addAll(chooseStepsField(),chooseMinMaxField(), createVector(),gameButtons());
  }

  public void affineSidebar() {
    createSideBar();
    sidebarMenu.getChildren().addAll(chooseStepsField(),chooseMinMaxField(), createVector(),createMatrix(), gameButtons());
  }

  public VBox createVector() {
    Text chooseVector = new Text("Choose vector:");
    chooseVector.setStyle("-fx-font-size: 14; ");

    TextField inputVector1 = new TextField();
    inputFieldStyle(inputVector1,"x0");
    TextField inputVector2 = new TextField();
    inputFieldStyle(inputVector2,"x1");

    HBox vectorInputs = new HBox(10);
    vectorInputs.getChildren().addAll(inputVector1,inputVector2);

    VBox vectorArea = new VBox(10);
    vectorArea.getChildren().addAll(chooseVector, vectorInputs);
    vectorArea.setAlignment(Pos.CENTER);
    return vectorArea;
  }

  public VBox createMatrix() {
    Text createMatrix = new Text("Choose matrix:");
    createMatrix.setStyle("-fx-font-size: 14");

    TextField inputA = new TextField();
    inputFieldStyle(inputA,"a");
    TextField inputB = new TextField();
    inputFieldStyle(inputB,"b");
    TextField inputC = new TextField();
    inputFieldStyle(inputC,"c");
    TextField inputD = new TextField();
    inputFieldStyle(inputD,"d");

    HBox inputAB = new HBox(inputA,inputB);
    HBox inputCD = new HBox(inputC, inputD);

    VBox matrixArea = new VBox(10);
    matrixArea.getChildren().addAll(createMatrix,inputAB,inputCD);
    matrixArea.setAlignment(Pos.CENTER);

    return matrixArea;
  }

  public void inputFieldStyle(TextField inputField, String promptText) {
    inputField.setMinHeight(30);
    inputField.setMaxWidth(80);
    inputField.setStyle("-fx-background-radius: 10");
    inputField.setPromptText(promptText);
  }

  @Override
  public BorderPane getPane() {
    return borderPane;
  }

}
