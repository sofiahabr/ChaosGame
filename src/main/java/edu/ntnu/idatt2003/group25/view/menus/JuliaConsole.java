package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.ScreenController;

public class JuliaConsole extends SideMenu {
  public JuliaConsole(ScreenController screenController) {
    super(screenController);
  }

  @Override
  public void setUp() {
    createSideBar();
    sideMenu.getChildren().addAll(chooseStepsField(),chooseMinMaxField(),createVector(),gameButtons());
  }
}
