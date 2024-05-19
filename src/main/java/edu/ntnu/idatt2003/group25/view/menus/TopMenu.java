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

public class TopMenu extends Menu {
  private HBox menu = new HBox(20);

  public TopMenu(ScreenController screenController) {
    super(screenController);
  }

  @Override
  public void setUp() {
    menu.getStyleClass().add("hbox");

    Button homeButton = new Button("Home");

    homeButton.getStyleClass().add("heading");
    homeButton.setStyle("-fx-font: 30px");

    homeButton.setOnAction(e -> {
      updateObserver("home", "initialize page");
    });
    homeButton.setAlignment(Pos.CENTER);

    Button exitButton = new Button("X");
    exitButton.setOnAction(e -> Platform.exit());
    exitButton.setAlignment(Pos.CENTER_RIGHT);

    Button infoButton = new Button("Help and instructions");
    infoButton.setOnAction(e -> showInstructions());

    //TODO: drop down informasjon om spillet og hvordan det fungerer
    // TODO: Ikoner for back, exit og info + spillnavn i midten

    HBox buffer = new HBox();
    HBox.setHgrow(buffer, Priority.ALWAYS);

    menu.setPrefHeight(60);
    menu.setPadding(new Insets(20));
    menu.getChildren().addAll(homeButton, buffer,infoButton, exitButton);
  }


  public void showInstructions() {
    Stage informationStage = new Stage();
    informationStage.setTitle("Instructions");


    Text infoTitle = new Text("Instructions");
    infoTitle.getStyleClass().add("heading");
    Text infoText;
    infoText = new Text("START GAME: Select a fractal and press the yellow start button\n\n"
        + "TO PLAY: choose nr of iterations and click the green play button\n\n"
        + "TO SAVE: click the save button and choose a file\n\n"
        + "TO ADD TRANSFORMS: enter values in the text fields and press the add button\n\n"
        + "TO EDIT: click the edit button and change values in the text fields\n\n"
        + "TO RESET SCREEN: click the remove button\n\n"
        + "TO CHANGE FRACTAL: click the home button and choose new fractal\n\n"
        + "TO EXIT: click the X button\n\n");

    infoText.getStyleClass().add("text");

    VBox instructionArea = new VBox(10);
    instructionArea.getChildren().addAll(infoTitle, infoText);

    Scene infoScene = new Scene(instructionArea);
    infoScene.getStylesheets().add("style/style.css");

    informationStage.setScene(infoScene);
    informationStage.show();
  }

  @Override
  public HBox getMenu() {
    return menu;
  }
}
