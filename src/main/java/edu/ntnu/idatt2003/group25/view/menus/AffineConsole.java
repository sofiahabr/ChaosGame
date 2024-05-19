package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class AffineConsole extends Menu {
  private VBox sideMenu = new VBox(40);
  public AffineConsole(ScreenController screenController, FactorialPage factorialPage) {
    super(screenController);
    addObserver(new FactorialPageController(screenController, factorialPage));
  }
  @Override
  public void setUp() {
    createSideBar(sideMenu);
    sideMenu.getChildren().addAll(chooseStepsField(),chooseMinMaxField(),createVector(), createMatrix(), gameButtons());
  }

  @Override
  public VBox getMenu() {
    return sideMenu;
  }
}
