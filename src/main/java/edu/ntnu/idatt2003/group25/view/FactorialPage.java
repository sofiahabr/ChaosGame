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

/**
 * Class for the factorial page of the application.
 */
public class FactorialPage extends View {

  /**
   * The height of the buttons in the sidebar.
   */
  private final int buttonHeight = Math.round(MainLogic.height * 0.04f);
  /**
   * The width of the buttons in the sidebar.
   */
  private static final int buttonWidth = 200;
  /**
   * The BorderPane of the factorial page.
   */
  private final BorderPane borderPane = new BorderPane();
  /**
   * The VBox of the sidebar menu.
   */
  private VBox sidebarMenu;
  /**
   * The ScreenController of the application.
   */
  private final ScreenController screenController;
  /**
   * The ChaosGameDescription of the application.
   */
  private ChaosGameDescription description = ChaosGameDescriptionFactory.createEmpty();
  /**
   * The FactorialPageController of the application.
   */
  private final FactorialPageController controller;
  /**
   * The game type of the application, either Julia or Affine transform.
   */
  private String gameType; // Julia or Affine
  /**
   * The ChaosGame of the application.
   */
  private ChaosGame chaosGame;
  /**
   * The Canvas of the application.
   */
  private Canvas pixelCanvas;
  /**
   * An error map of the application storing error messages and location of message output.
   */
  HashMap<String, String> errorMap = new HashMap<>();
  /**
   * The label that shows the error message for incorrect steps input.
   */
  private Label stepsErrorLabel;

  /**
   * Constructor for the FactorialPage class.
   *
   * @param screenController ScreenController object that the page is observing.
   */
  public FactorialPage(ScreenController screenController) {
    this.controller = new FactorialPageController(screenController, this);
    this.screenController = screenController;
    addObserver(controller);

    initializeCanvas();
    bindCanvasSize();
    addResizeListener();
  }

  /**
   * Initializes the canvas of the application.
   */
  private void initializeCanvas() {
    this.pixelCanvas = new Canvas();
    this.chaosGame = new ChaosGame(description, (int) (MainLogic.width * 0.7),
        (int) (MainLogic.height * 0.7));
  }

  /**
   * Binds the canvas size to the size of the BorderPane.
   */
  private void bindCanvasSize() {
    pixelCanvas.widthProperty().bind(borderPane.widthProperty().multiply(0.7));
    pixelCanvas.heightProperty().bind(borderPane.heightProperty().multiply(0.7));
  }

  /**
   * Adds a listener to the BorderPane to handle screen resizing.
   */
  private void addResizeListener() {
    borderPane.widthProperty().addListener((obs, oldValue, newValue) ->
        controller.handleScreenResize(newValue.doubleValue(), borderPane.getHeight()));
    borderPane.heightProperty().addListener((obs, oldValue, newValue) ->
        controller.handleScreenResize(borderPane.getWidth(), newValue.doubleValue()));
  }

  /**
   * Sets up the factorial page of the application.
   */
  @Override
  public void setUp() {

    TopMenu topMenu = new TopMenu(screenController);
    topMenu.setUp();
    borderPane.setTop(topMenu.getMenu());

    setSidebarMenu();

    borderPane.setLeft(sidebarMenu);
    borderPane.setCenter(pixelCanvas);
  }

  /**
   * Creates the sidebar menu of the application.
   */
  private void createSideBar() {
    sidebarMenu = new VBox(MainLogic.height * 0.01f);
    sidebarMenu.getStyleClass().add("vbox");
    sidebarMenu.setPadding(new Insets(20));
  }

  /**
   * Creates the steps field of the application.
   *
   * @return VBox object representing the steps field.
   */
  public VBox chooseStepsField() {
    Text chooseSteps = new Text("Choose steps");
    chooseSteps.getStyleClass().add("heading");

    TextField inputSteps = new TextField();
    inputFieldStyle(inputSteps, "Ex. 500...", buttonHeight, buttonWidth);



    stepsErrorLabel = new Label();
    stepsErrorLabel.setMinHeight(buttonHeight * 1.3);
    stepsErrorLabel.getStyleClass().add("error");
    updateErrorLabel(stepsErrorLabel);

    VBox stepsArea = new VBox(10);
    stepsArea.getChildren().addAll(chooseSteps, inputSteps, stepsErrorLabel);
    stepsArea.setAlignment(Pos.CENTER);

    // send to controller
    inputSteps.setOnKeyTyped(e -> updateObserver("register steps", inputSteps.getText()));

    return stepsArea;
  }

  /**
   * Creates the game buttons of the application.
   *
   * @return VBox object representing the game buttons.
   */
  private VBox gameButtons() {

    Button playButton = new Button("Play");
    playButton.getStyleClass().add("button2");
    addStyle(playButton, "green", buttonWidth);
    String buttonClicked = "button clicked";
    playButton.setOnAction(e -> updateObserver(buttonClicked, "play"));

    Button resetButton = new Button("Reset canvas");
    resetButton.getStyleClass().add("button2");
    addStyle(resetButton, "red", buttonWidth);
    resetButton.setOnAction(e -> updateObserver(buttonClicked, "reset"));

    Button saveButton = new Button("Save to file");
    saveButton.getStyleClass().add("button2");
    addStyle(saveButton, "black", buttonWidth);
    saveButton.setOnAction(e -> updateObserver(buttonClicked, "save"));

    Button addTransformButton = new Button("Add transform");
    addTransformButton.getStyleClass().add("button2");
    addStyle(addTransformButton, "grey", buttonWidth);
    addTransformButton.setOnAction(e -> updateObserver(buttonClicked, "add"));

    VBox buttonBox = new VBox(10);
    buttonBox.getChildren()
        .addAll(addTransformButton, saveButton, resetButton, playButton);
    buttonBox.setAlignment(Pos.CENTER);

    return buttonBox;
  }

  /**
   * Sets the sidebar menu of the application.
   */
  public void setSidebarMenu() {
    EditTransformsMenu editTransformsMenu = new EditTransformsMenu(screenController, this);
    editTransformsMenu.setUp();
    createSideBar();
    sidebarMenu.getChildren()
        .addAll(chooseStepsField(), editTransformsMenu.getMenu(), gameButtons());
  }

  /**
   *  Sets the style of the input field.
   *
   * @param inputField The input field to be styled.
   * @param promptText The prompt text of the input field.
   * @param height The height of the input field.
   * @param width The width of the input field.
   */
  public void inputFieldStyle(TextField inputField, String promptText, int height, int width) {
    inputField.setMinHeight(height);
    inputField.setMaxWidth(width);
    inputField.setPromptText(promptText);
  }

  /**
   * Adds style to a button.
   *
   * @param button The button to be styled.
   * @param color The color of the button.
   * @param width The width of the button.
   */
  public void addStyle(Button button, String color, int width) {
    String colorInit = "-fx-background-color: " + color + ";";
    String widthInit = "-fx-min-width: " + width + ";";

    button.setMaxHeight(buttonHeight);

    button.setStyle(colorInit + widthInit);
  }

  /**
   * Returns the BorderPane of the application.
   *
   * @return BorderPane object representing the application.
   */
  @Override
  public BorderPane getPane() {
    return borderPane;
  }

  /**
   * Updates the error label with the correct error message.
   *
   * @param label The label to be updated.
   */
  private void updateErrorLabel(Label label) {
    String errorMessage = errorMap.getOrDefault("InputSteps", "");
    label.setText(errorMessage.isEmpty() ? " " : errorMessage);
  }

  /**
   * Shows an error message.
   *
   * @param placement The placement of the error message.
   * @param message The error message.
   */
  public void showError(String placement, String message) {
    errorMap.put(placement, message);
    if (placement.equals("InputSteps")) {
      updateErrorLabel(stepsErrorLabel);
    }
  }

  /**
   * Draws the canvas of the application.
   */
  public void draw() {
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

  /**
   * Resets the canvas of the application.
  */
  public void reset() {
    GraphicsContext gc = pixelCanvas.getGraphicsContext2D();
    gc.clearRect(0, 0, pixelCanvas.getWidth(), pixelCanvas.getHeight());
  }

  /**
   * Returns the ChaosGameDescription of the application.
   *
   * @return ChaosGameDescription object representing the description of the application.
   */
  public ChaosGameDescription getDescription() {
    return description;
  }

  /**
   * Sets the ChaosGameDescription of the application.
   *
   * @return ChaosGameDescription object representing the description of the application.
   */
  public String getGameType() {
    return gameType;
  }

  /**
   * Sets the game type of the application.
   *
   * @param gameType The game type to be set.
   */

  public void setGameType(String gameType) {
    this.gameType = gameType;
  }

  /**
   * Sets the ChaosGameDescription of the application.
   *
   * @param description The ChaosGameDescription to be set.
   */
  public void setDescription(ChaosGameDescription description) {
    this.description = description;
    chaosGame = new ChaosGame(description, Math.round(MainLogic.width * 0.7f),
        Math.round(MainLogic.height * 0.7f));

  }

  /**
   * Returns the ChaosGame of the application.
   *
   * @return ChaosGame object representing the game of the application.
   */
  public ChaosGame getChaosGame() {
    return chaosGame;
  }
}


