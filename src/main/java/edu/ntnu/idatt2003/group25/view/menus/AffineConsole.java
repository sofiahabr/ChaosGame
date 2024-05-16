package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.ScreenController;

public class AffineConsole extends SideMenu {
  public AffineConsole(ScreenController screenController) {
    super(screenController);
  }
  @Override
  public void setUp() {
    createSideBar();
    sideMenu.getChildren().addAll(chooseStepsField(),chooseMinMaxField(),createVector(), createMatrix(), gameButtons());
  }
}