package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FactorialPage extends View {

  private final BorderPane borderPane = new BorderPane();
  public ScreenController screenController;
  private ChaosGameDescription description = ChaosGameDescriptionFactory.createEmpty();
  private FactorialPageController controller;
  private String gameType; // Julia or Affine
  private ChaosGame chaosGame = new ChaosGame(description, Math.round(MainView.width*0.7f), Math.round(MainView.height*0.7f));
  private Canvas pixelCanvas = new Canvas(MainView.width*0.7, MainView.height*0.7);

  public ChaosGameDescription description;
  FactorialPageController controller;
  HashMap<String,String> errorMap = new HashMap<>();
  private Label stepsErrorLabel;
  private Label minMaxError;
  private Label matrixErrorLabel;
  private Label vectorErrorLabel;

  public FactorialPage(ScreenController screenController) {
    this.controller = new FactorialPageController(screenController, this);
    this.screenController = screenController;
    addObserver(controller);
  }

  @Override
  public void setUp() {

    if(MainLogic.description.getTransforms().getFirst().getClass().getName().contains("JuliaTransform")){
      juliaSidebar();
    } else if (MainLogic.description.getTransforms().getFirst().getClass().getName().contains("AffineTransform2D")) {
      affineSidebar();
    }

    HBox topBox = new HBox(0);
    borderPane.setTop(topBox);

    Menu console;
    if (gameType.equals("Julia Transform")) {
      console = new JuliaConsole(screenController, this);
    } else {
      console = new AffineConsole(screenController, this);
    }
    console.setUp();
    borderPane.setLeft(sidebarMenu);
  }

    borderPane.setTop(topBarMenu.getMenu());
    borderPane.setLeft(console.getMenu());
  }

  @Override
  public void update() {
    this.description = MainLogic.description;
  }

  private void createSideBar() {
    sidebarMenu = new VBox(40);
    sidebarMenu.getStyleClass().add("vbox");
    sidebarMenu.setPadding(new Insets(20));
  }

  public VBox chooseStepsField() {
    Text chooseSteps = new Text("Choose steps");
    chooseSteps.getStyleClass().add("heading");


    TextField inputSteps = new TextField();
    inputFieldStyle(inputSteps, "Ex. 500...", 30, 180);

    VBox stepsArea = new VBox(10);

    stepsErrorLabel = new Label();
    stepsErrorLabel.getStyleClass().add("error");
    updateErrorLabel("InputSteps", stepsErrorLabel);

    stepsArea.getChildren().addAll(chooseSteps, inputSteps, stepsErrorLabel);
    stepsArea.setAlignment(Pos.CENTER);

    // send to controller
    inputSteps.setOnKeyTyped(e-> {
        updateObserver("register steps", inputSteps.getText());
    });

    return stepsArea;
  }
  private VBox chooseMinMaxField() {

    // Create min/ max area
    Text minTitle = new Text(" Min: ");
    Text maxTitle = new Text(" Max: ");
    maxTitle.getStyleClass().add("heading");
    minTitle.getStyleClass().add("heading");

    TextField inputMax = new TextField();
    inputFieldStyle(inputMax,"ex. 1", 30, 80);
    inputMax.setOnKeyTyped(e-> updateObserver("max input", inputMax.getText() + ", "
        + inputMax.getText()));


    TextField inputMin = new TextField();
    inputFieldStyle(inputMin,"ex. 0", 30, 80);
    inputMin.setOnKeyTyped(e-> updateObserver("min input", inputMin.getText() + ", "
        + inputMin.getText()));


    VBox minBox = new VBox(10);
    VBox maxBox = new VBox(10);

    minMaxError = new Label();
    minMaxError.getStyleClass().add("error");
    updateErrorLabel("InputMinMax", minMaxError);

    minBox.getChildren().addAll(minTitle, inputMin);
    maxBox.getChildren().addAll(maxTitle, inputMax);

    HBox minMaxBox = new HBox(20);
    minMaxBox.getChildren().addAll(minBox, maxBox);
    minMaxBox.setAlignment(Pos.CENTER);

    VBox minMaxArea = new VBox(10);
    minMaxArea.getChildren().addAll(minMaxBox, minMaxError);
    minMaxArea.setAlignment(Pos.CENTER);

    return minMaxArea;
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

    TextField inputVectorX0 = new TextField();
    inputFieldStyle(inputVectorX0,"x0", 30, 80);
    TextField inputVectorX1 = new TextField();
    inputFieldStyle(inputVectorX1,"x1", 30, 80);

    inputVectorX0.setOnKeyTyped(e-> updateObserver("vector input", inputVectorX0.getText() + ", " + inputVectorX1.getText()));
    inputVectorX1.setOnKeyTyped(e-> updateObserver("vector input", inputVectorX0.getText() + ", " + inputVectorX1.getText()));

    HBox vectorInputs = new HBox(20);
    vectorInputs.getChildren().addAll(inputVectorX0,inputVectorX1);

    vectorErrorLabel = new Label();
    vectorErrorLabel.getStyleClass().add("error");
    updateErrorLabel("InputVector",vectorErrorLabel);

    VBox vectorArea = new VBox(10);
    vectorArea.getChildren().addAll(chooseVector, vectorInputs,vectorErrorLabel);
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

    matrixErrorLabel = new Label();
    matrixErrorLabel.getStyleClass().add("error");
    updateErrorLabel("InputMatrix", matrixErrorLabel);
    HBox inputAB = new HBox(20, inputA,inputB);
    HBox inputCD = new HBox(20, inputC, inputD);

    VBox matrixArea = new VBox(10);
    matrixArea.getChildren().addAll(createMatrix,inputAB,inputCD, matrixErrorLabel);
    matrixArea.setAlignment(Pos.CENTER);

    return matrixArea;
  }

  private void handleSceneChange(String info) {}
  public void inputFieldStyle(TextField inputField, String promptText, int height,int width ) {
    inputField.setMinHeight(height);
    inputField.setMaxWidth(width);
    inputField.setPromptText(promptText);
  }

  public void addStyle(Button button, String color, int width) {
    String colorInit = "-fx-background-color: " + color + ";" ;
    String widthInit = "-fx-min-width: " + width + ";" ;

    button.setStyle(colorInit + widthInit);
  }


  @Override
  public BorderPane getPane() {
    return borderPane;
  }
  private void updateErrorLabel(String key, Label label) {
    String errorMessage = errorMap.getOrDefault(key, "");
    label.setText(errorMessage.isEmpty() ? " " : errorMessage);
  }
  public void showError(String placement, String message) {
    errorMap.put(placement, message);
    switch (placement) {
      case "InputSteps":
        updateErrorLabel("InputSteps", stepsErrorLabel);
        break;
      case "InputMinMax":
        updateErrorLabel("InputMinMax", minMaxError);
        break;
      case "InputMatrix":
        updateErrorLabel("InputMatrix", matrixErrorLabel);
        break;
      case "InputVector":
        updateErrorLabel("InputVector", vectorErrorLabel);
        break;
    }
  }

  public void draw(int steps) {

    Canvas pixelCanvas =
        new Canvas(chaosGame.getCanvas().getWidth(), chaosGame.getCanvas().getHeight());

    chaosGame.runSteps(steps);

    int[][] canvas = chaosGame.getCanvas().getCanvasArray();
    GraphicsContext gc = pixelCanvas.getGraphicsContext2D();

    for (int i = 0; i < canvas.length; i++) {
      for (int j = 0; j < canvas[i].length; j++) {
        double number = canvas[i][j] / 10f;
        if ((number) > 0) {
          if ((number) < 1) {
            gc.setFill(Color.color(1, 0, number));
          } else if ((number) < 2) {
            gc.setFill(Color.color(2 - number, 0, 1));
          } else {
            gc.setFill(Color.color(0, 0, 1));
          }
          gc.fillRect(j, i, 1, 1); // Draw a single pixel
        }
      }
    }
    borderPane.setCenter(pixelCanvas);
  }
  public void reset() {
    GraphicsContext gc = pixelCanvas.getGraphicsContext2D();
    gc.clearRect(0, 0, pixelCanvas.getWidth(), pixelCanvas.getHeight());
    borderPane.setCenter(pixelCanvas);
  }
  public ChaosGameDescription getDescription() {
    return description;
  }
  public String getGameType(){
    return gameType;
  }

  public void setGameType(String gameType) {
    this.gameType = gameType;
  }
  public void setDescription(ChaosGameDescription description) {
    this.description = description;
    chaosGame = new ChaosGame(description, Math.round(MainView.width*0.7f), Math.round(MainView.height*0.7f));

  }
}
