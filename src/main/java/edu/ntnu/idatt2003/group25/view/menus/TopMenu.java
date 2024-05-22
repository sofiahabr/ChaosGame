package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.ScreenController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The top menu of the application.
 */
public class TopMenu extends Menu {

  /**
   * The menu of the application as a HBox.
   */
  private final HBox menu = new HBox(20);

  /**
   * Constructor for the TopMenu class.
   *
   * @param screenController ScreenController object that the menu is observing
   */
  public TopMenu(ScreenController screenController) {
    super(screenController);
  }

  /**
   * Sets up the menu of the application.
   */
  @Override
  public void setUp() {
    menu.getStyleClass().add("hbox");

    Button homeButton = new Button("Home");

    homeButton.getStyleClass().add("heading");
    homeButton.setStyle("-fx-font: 30px");

    homeButton.setOnAction(e ->
        updateObserver("home", "initialize page"));

    homeButton.setAlignment(Pos.CENTER);

    Button exitButton = new Button("X");
    exitButton.setOnAction(e -> Platform.exit());
    exitButton.setAlignment(Pos.CENTER_RIGHT);

    Button infoButton = new Button("Help and instructions");
    infoButton.setOnAction(e -> showInstructions());

    HBox buffer = new HBox();
    HBox.setHgrow(buffer, Priority.ALWAYS);

    menu.setPrefHeight(60);
    menu.setPadding(new Insets(20));
    menu.getChildren().addAll(homeButton, buffer, infoButton, exitButton);
  }

  /**
   * Shows the help instructions of the application.
   */
  public void showInstructions() {
    Stage informationStage = new Stage();
    informationStage.setTitle("Help and instructions");


    Text infoTitle = new Text("Instructions");
    infoTitle.getStyleClass().add("heading");

    Text infoText = getInfoText();
    infoText.getStyleClass().add("text");

    Button okButton = new Button("OK");
    okButton.getStyleClass().add("button");
    okButton.setOnAction(e -> informationStage.close());

    VBox instructionArea = new VBox(10);
    instructionArea.getChildren().addAll(infoTitle, infoText, okButton);
    instructionArea.setAlignment(Pos.CENTER);
    instructionArea.setPadding(new Insets(10));

    Scene infoScene = new Scene(instructionArea);
    infoScene.getStylesheets().add("style/style.css");

    informationStage.setScene(infoScene);
    informationStage.show();
  }

  /**
   * Returns the text of the help instructions.
   *
   * @return Text object with the help instructions
   */
  private static Text getInfoText() {
    Text infoText;
    infoText = new Text("""
        START GAME:
        Select a fractal and press the yellow start button
                
        TO PLAY:
        Choose nr of iterations and click the green play button
                
        TO SAVE:
        Click the save button and choose a file
                
        TO ADD TRANSFORMS:
        Press the add button and enter values in the text fields
                
        TO EDIT:
        Change values in the text fields and click the edit button
                
        TO RESET SCREEN:
        Click the reset canvas button
                
        TO CHANGE FRACTAL:
        Click the home button and choose new fractal
                
        TO EXIT:
        Click the X button""");
    return infoText;
  }

  /**
   * Returns the menu of the application.
   *
   * @return HBox object with the menu of the application
   */
  @Override
  public HBox getMenu() {
    return menu;
  }
}
