package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.view.menus.Menu;
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

    Button backButton = new Button("Back");
    backButton.setOnAction(e -> {
      updateObserver("back", "initialize page");
    });
    backButton.setAlignment(Pos.CENTER);

    Button exitButton = new Button("Exit");
    exitButton.setOnAction(e -> System.exit(0));
    exitButton.setAlignment(Pos.CENTER_RIGHT);

    HBox buffer = new HBox();
    HBox.setHgrow(buffer, Priority.ALWAYS);

    menu.setPrefHeight(60);
    menu.setPadding(new Insets(20));
    menu.getChildren().addAll(backButton, buffer, exitButton);
  }

  @Override
  public HBox getMenu() {
    return menu;
  }
}
