package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.view.menus.AffineConsole;
import edu.ntnu.idatt2003.group25.view.menus.JuliaConsole;
import edu.ntnu.idatt2003.group25.view.menus.SideMenu;
import edu.ntnu.idatt2003.group25.view.menus.TopMenu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class FactorialPage extends View {

  public BorderPane borderPane = new BorderPane();
  public VBox sidebarMenu;
  public ScreenController screenController;
  public ChaosGameDescription description;
  FactorialPageController controller;
  public FactorialPage(ScreenController screenController) {
    this.controller = new FactorialPageController(screenController);
    this.screenController = screenController;
    addObserver(controller);
    }

  @Override
  public void setUp() {
    this.description = MainView.description;

    TopMenu topBarMenu = new TopMenu(screenController);
    topBarMenu.setUp();


    SideMenu console;
    if(MainView.description.getTransforms().getFirst().getClass().getName().contains("JuliaTransform")){
      console = new JuliaConsole(screenController);
    } else {
      console = new AffineConsole(screenController);
}
    console.setUp();

    borderPane.setTop(topBarMenu.getMenu());
    borderPane.setLeft(console.getMenu());
  }
  @Override
  public BorderPane getPane() {
    return borderPane;
  }

}
