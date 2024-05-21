package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.view.menus.EditTransformsMenu;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FactorialPage extends View {

  private final int buttonHeight = Math.round(MainLogic.height*0.03f);
  private final int buttonWidth = 200;
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
  private EditTransformsMenu editTransformsMenu;

  public FactorialPage(ScreenController screenController) {
    this.controller = new FactorialPageController(screenController, this);
    this.screenController = screenController;
    addObserver(controller);

    initializeCanvas();
    bindCanvasSize();
    addResizeListener();
  }

  private void initializeCanvas() {
    this.pixelCanvas = new Canvas();
    this.chaosGame = new ChaosGame(description, (int) (MainLogic.width * 0.7),
        (int) (MainLogic.height * 0.7));
  }

  private void bindCanvasSize() {
    pixelCanvas.widthProperty().bind(borderPane.widthProperty().multiply(0.7));
    pixelCanvas.heightProperty().bind(borderPane.heightProperty().multiply(0.7));
  }
  private void addResizeListener() {
    borderPane.widthProperty().addListener((obs, oldValue, newValue) -> controller.handleScreenResize(newValue.doubleValue(), borderPane.getHeight()));
    borderPane.heightProperty().addListener((obs, oldValue, newValue) -> controller.handleScreenResize(borderPane.getWidth(), newValue.doubleValue()));
  }

  @Override
  public void setUp() {

    TopMenu topMenu = new TopMenu(screenController);
    topMenu.setUp();
    borderPane.setTop(topMenu.getMenu());

    setSidebarMenu();

    borderPane.setLeft(sidebarMenu);
    borderPane.setCenter(pixelCanvas);
  }
  private void createSideBar() {
    sidebarMenu = new VBox(MainLogic.height * 0.01f);
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
    stepsErrorLabel.setMinHeight(buttonHeight*1.3);
    stepsErrorLabel.getStyleClass().add("error");
    updateErrorLabel("InputSteps", stepsErrorLabel);

    stepsArea.getChildren().addAll(chooseSteps, inputSteps, stepsErrorLabel);
    stepsArea.setAlignment(Pos.CENTER);

    // send to controller
    inputSteps.setOnKeyTyped(e -> updateObserver("register steps", inputSteps.getText()));

    return stepsArea;
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

    VBox buttonBox = new VBox(10);
    buttonBox.getChildren()
        .addAll(addTransformButton, saveButton, resetButton, playButton);
    buttonBox.setAlignment(Pos.CENTER);

    return buttonBox;
  }

  public void setSidebarMenu() {
    editTransformsMenu = new EditTransformsMenu(screenController, this);
    editTransformsMenu.setUp();
    createSideBar();
    sidebarMenu.getChildren()
        .addAll(chooseStepsField(), editTransformsMenu.getMenu(), gameButtons());
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
      }
    }

    public void draw(){
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
    public ChaosGame getChaosGame() {
    return chaosGame;
    }
}


