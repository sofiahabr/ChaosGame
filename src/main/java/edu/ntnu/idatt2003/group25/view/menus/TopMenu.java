package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.ScreenController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

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


    //TODO: drop down informasjon om spillet og hvordan det fungerer
    // TODO: Ikoner for back, exit og info + spillnavn i midten

    HBox buffer = new HBox();
    HBox.setHgrow(buffer, Priority.ALWAYS);

    menu.setPrefHeight(60);
    menu.setPadding(new Insets(20));
    menu.getChildren().addAll(homeButton, buffer,infoButton, exitButton);
  }

  @Override
  public HBox getMenu() {
    return menu;
  }
}
