package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.view.FactorialPage;

public class JuliaConsole extends SideMenu {
  public JuliaConsole(ScreenController screenController, FactorialPage factorialPage) {
    super(screenController, factorialPage);
  }

  @Override
  public void setUp() {
    createSideBar();
    sideMenu.getChildren().addAll(chooseStepsField(),chooseMinMaxField(),createVector(),gameButtons());
  }
}
