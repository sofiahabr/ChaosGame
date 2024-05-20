package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.view.menus.Menu;
import edu.ntnu.idatt2003.group25.view.menus.TopMenu;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FactorialPage extends View {

  private int buttonHeight = Math.round(MainLogic.height*0.03f);
  private int buttonWidth = 180;
  private final BorderPane borderPane = new BorderPane();
  private VBox sidebarMenu;
  public ScreenController screenController;
  private ChaosGameDescription description = ChaosGameDescriptionFactory.createEmpty();
  private final FactorialPageController controller;
  private String gameType; // Julia or Affine
  private ChaosGame chaosGame;
  private Canvas pixelCanvas;
  HashMap<String, String> errorMap = new HashMap<>();
  private Label stepsErrorLabel;
  private Label minMaxError;
  private Label matrixErrorLabel;
  private Label vectorErrorLabel;
  private Label signErrorLabel;

  public FactorialPage(ScreenController screenController) {
    this.controller = new FactorialPageController(screenController, this);
    this.screenController = screenController;
    addObserver(controller);

    initilizeCanvas();
    bindCanvasSize();
    addResizeListener();
  }

  private void initilizeCanvas() {
    this.pixelCanvas = new Canvas();
    this.chaosGame = new ChaosGame(description, (int) (MainLogic.width * 0.7),
        (int) (MainLogic.height * 0.7));
  }

  private void bindCanvasSize() {
    pixelCanvas.widthProperty().bind(borderPane.widthProperty().multiply(0.7));
    pixelCanvas.heightProperty().bind(borderPane.heightProperty().multiply(0.7));
  }
  private void addResizeListener() {
    borderPane.widthProperty().addListener((obs, oldValue, newValue) -> {
      controller.handleScreenResize(newValue.doubleValue(), borderPane.getHeight());
    });
    borderPane.heightProperty().addListener((obs, oldValue, newValue) -> {
      controller.handleScreenResize(borderPane.getWidth(), newValue.doubleValue());
    });
  }

  @Override
  public void setUp() {

    TopMenu topMenu = new TopMenu(screenController);
    topMenu.setUp();
    borderPane.setTop(topMenu.getMenu());

    if (gameType.equals("Julia Transform")) {
      juliaSidebar();
    } else {
      affineSidebar();
    }

    borderPane.setLeft(sidebarMenu);
    borderPane.setCenter(pixelCanvas);
  }
  private void createSideBar() {
    sidebarMenu = new VBox(10);
    sidebarMenu.getStyleClass().add("vbox");
    sidebarMenu.setPadding(new Insets(20));
  }

  public VBox chooseStepsField() {
    Text chooseSteps = new Text("Choose steps");
    chooseSteps.getStyleClass().add("heading");

    TextField inputSteps = new TextField();
    inputFieldStyle(inputSteps, "Ex. 500...", buttonHeight, buttonWidth);

    VBox stepsArea = new VBox(10);

    stepsErrorLabel = new Label();
    stepsErrorLabel.setPrefHeight(buttonHeight * 1.25);
    stepsErrorLabel.getStyleClass().add("error");
    updateErrorLabel("InputSteps", stepsErrorLabel);

    stepsArea.getChildren().addAll(chooseSteps, inputSteps, stepsErrorLabel);
    stepsArea.setAlignment(Pos.CENTER);

    // send to controller
    inputSteps.setOnKeyTyped(e -> {
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
    inputFieldStyle(inputMax, "1", buttonHeight, buttonWidth/4 - 10);

    TextField inputMax2 = new TextField();
    inputFieldStyle(inputMax2, "1", buttonHeight, buttonWidth/4 - 10);

    HBox maxArea = new HBox(10);
    maxArea.getChildren().addAll(inputMax, inputMax2);

    inputMax.setOnKeyTyped(
        e -> updateObserver("max input", inputMax.getText() + ", " + inputMax2.getText()));
    inputMax2.setOnKeyTyped(
        e -> updateObserver("max input", inputMax.getText() + ", " + inputMax2.getText()));

    TextField inputMin = new TextField();
    inputFieldStyle(inputMin, "0", buttonHeight, buttonWidth/4 - 10);

    TextField inputMin2 = new TextField();
    inputFieldStyle(inputMin2, "0", buttonHeight, buttonWidth/4 - 10);

    HBox minArea = new HBox(10);
    minArea.getChildren().addAll(inputMin, inputMin2);

    inputMin.setOnKeyTyped(
        e -> updateObserver("min input", inputMin.getText() + ", " + inputMin2.getText()));
    inputMin2.setOnKeyTyped(
        e -> updateObserver("min input", inputMin.getText() + ", " + inputMin2.getText()));

    VBox minBox = new VBox(10);
    VBox maxBox = new VBox(10);

    minMaxError = new Label();
    minMaxError.getStyleClass().add("error");
    updateErrorLabel("InputMinMax", minMaxError);

    minBox.getChildren().addAll(minTitle, minArea);
    maxBox.getChildren().addAll(maxTitle, maxArea);

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
    playButton.getStyleClass().add("button2");
    addStyle(playButton, "green", buttonWidth);
    playButton.setOnAction(e -> updateObserver("button clicked", "play"));

    Button resetButton = new Button("Reset canvas");
    resetButton.getStyleClass().add("button2");
    addStyle(resetButton, "red", buttonWidth);
    resetButton.setOnAction(e -> updateObserver("button clicked", "reset"));

    Button saveButton = new Button("Save to file");
    saveButton.getStyleClass().add("button2");
    addStyle(saveButton, "black", buttonWidth);
    saveButton.setOnAction(e -> updateObserver("button clicked", "save"));

    Button addTransformButton = new Button("Add transform");
    addTransformButton.getStyleClass().add("button2");
    addStyle(addTransformButton, "grey", buttonWidth);

    addTransformButton.setOnAction(e -> updateObserver("button clicked", "add"));

    Button applyEditsButton = new Button("Edit transformation");
    applyEditsButton.getStyleClass().add("button2");
    addStyle(applyEditsButton, "blue", buttonWidth);
    applyEditsButton.setOnAction(e -> updateObserver("button clicked", "edit"));

    VBox buttonBox = new VBox(10);
    buttonBox.getChildren()
        .addAll(addTransformButton, applyEditsButton, saveButton, resetButton, playButton);
    buttonBox.setAlignment(Pos.CENTER);

    return buttonBox;
  }

  public void juliaSidebar() {
    createSideBar();
    sidebarMenu.getChildren()
        .addAll(chooseStepsField(), createVector(), createSignArea(), chooseMinMaxField(), gameButtons());
  }

  public void affineSidebar() {
    createSideBar();
    sidebarMenu.getChildren().addAll(chooseStepsField(), createVector(),
        createMatrix(), chooseMinMaxField(), gameButtons());
  }

  public VBox createVector() {
    Text chooseVector = new Text("Choose vector:");
    chooseVector.getStyleClass().add("heading");

    TextField inputVectorX0 = new TextField();
    inputFieldStyle(inputVectorX0,"x0", buttonHeight, buttonWidth/2 - 10);
    TextField inputVectorX1 = new TextField();
    inputFieldStyle(inputVectorX1,"x1", buttonHeight, buttonWidth/2 - 10);

    inputVectorX0.setOnKeyTyped(e -> updateObserver("vector input",
        inputVectorX0.getText() + ", " + inputVectorX1.getText()));
    inputVectorX1.setOnKeyTyped(e -> updateObserver("vector input",
        inputVectorX0.getText() + ", " + inputVectorX1.getText()));

    HBox vectorInputs = new HBox(20);
    vectorInputs.getChildren().addAll(inputVectorX0, inputVectorX1);

    vectorErrorLabel = new Label();
    vectorErrorLabel.getStyleClass().add("error");
    updateErrorLabel("InputVector", vectorErrorLabel);

    VBox vectorArea = new VBox(10);
    vectorArea.getChildren().addAll(chooseVector, vectorInputs, vectorErrorLabel);
    vectorArea.setAlignment(Pos.CENTER);
    return vectorArea;
  }
  public VBox createSignArea() {
    Text chooseSign = new Text("Choose sign:");
    chooseSign.getStyleClass().add("heading");

    TextField inputSign = new TextField();
    inputFieldStyle(inputSign,"1", buttonHeight, buttonWidth / 2 - 10);

    inputSign.setOnKeyTyped(e -> updateObserver("sign input", inputSign.getText()));

    signErrorLabel = new Label();
    signErrorLabel.getStyleClass().add("error");
    updateErrorLabel("InputSign", signErrorLabel);

    VBox signArea = new VBox(10);
    signArea.getChildren().addAll(chooseSign, inputSign, signErrorLabel);
    signArea.setAlignment(Pos.CENTER);

    return signArea;
  }

  public VBox createMatrix() {
    Text createMatrix = new Text("Choose matrix:");
    createMatrix.getStyleClass().add("heading");

    TextField inputA = new TextField();
    inputFieldStyle(inputA,"a", buttonHeight, buttonWidth/2 - 10);
    TextField inputB = new TextField();
    inputFieldStyle(inputB,"b", buttonHeight, buttonWidth/2 - 10);
    TextField inputC = new TextField();
    inputFieldStyle(inputC,"c", buttonHeight, buttonWidth/2 - 10);
    TextField inputD = new TextField();
    inputFieldStyle(inputD,"d", buttonHeight, buttonWidth/2 - 10);

    inputA.setOnKeyTyped(e -> updateObserver("matrix input",
        inputA.getText() + ", " + inputB.getText() + ", " + inputC.getText() + ", "
            + inputD.getText()));
    inputB.setOnKeyTyped(e -> updateObserver("matrix input",
        inputA.getText() + ", " + inputB.getText() + ", " + inputC.getText() + ", "
            + inputD.getText()));
    inputC.setOnKeyTyped(e -> updateObserver("matrix input",
        inputA.getText() + ", " + inputB.getText() + ", " + inputC.getText() + ", "
            + inputD.getText()));
    inputD.setOnKeyTyped(e -> updateObserver("matrix input",
        inputA.getText() + ", " + inputB.getText() + ", " + inputC.getText() + ", "
            + inputD.getText()));

    matrixErrorLabel = new Label();
    matrixErrorLabel.getStyleClass().add("error");
    updateErrorLabel("InputMatrix", matrixErrorLabel);
    HBox inputAB = new HBox(20, inputA, inputB);
    HBox inputCD = new HBox(20, inputC, inputD);

    VBox matrixArea = new VBox(10);
    matrixArea.getChildren().addAll(createMatrix, inputAB, inputCD, matrixErrorLabel);
    matrixArea.setAlignment(Pos.CENTER);

    return matrixArea;
  }

    public void inputFieldStyle (TextField inputField, String promptText,int height, int width){
      inputField.setMinHeight(height);
      inputField.setMaxWidth(width);
      inputField.setPromptText(promptText);
    }


    public void addStyle(Button button, String color,int width) {
      String colorInit = "-fx-background-color: " + color + ";";
      String widthInit = "-fx-min-width: " + width + ";";

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
    public void showError (String placement, String message) {
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
        case "InputSign":
            updateErrorLabel("InputSign", signErrorLabel);
            break;
      }
    }

    public void draw( int steps){
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
    }
    public void reset() {
      GraphicsContext gc = pixelCanvas.getGraphicsContext2D();
      gc.clearRect(0, 0, pixelCanvas.getWidth(), pixelCanvas.getHeight());
    }
    public ChaosGameDescription getDescription() {
      return description;
    }
    public String getGameType() {
      return gameType;
    }

    public void setGameType (String gameType){
      this.gameType = gameType;
    }
    public void setDescription (ChaosGameDescription description){
      this.description = description;
      chaosGame = new ChaosGame(description, Math.round(MainLogic.width * 0.7f),
          Math.round(MainLogic.height * 0.7f));

    }
}


