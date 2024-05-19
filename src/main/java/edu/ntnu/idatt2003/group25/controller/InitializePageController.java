package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Matrix2x2;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import edu.ntnu.idatt2003.group25.view.MainLogic;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.MainView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.FileChooser;

public class InitializePageController extends Controller {
  ScreenController screenController;
  FactorialPage factorialPage;

  public InitializePageController(ScreenController screenController) {
    this.screenController = screenController;
  }

  @Override
  public void gameChanged(String event, String transformName) {
    ChaosGameDescription description = ChaosGameDescriptionFactory.createEmpty();
    this.factorialPage = new FactorialPage(screenController);
    if (event.equals("button clicked")) {
      switch (transformName) {
        case "Create new Affine Transform":
          factorialPage.setGameType("Affine Transform");
          description = (ChaosGameDescriptionFactory.createEmpty());
          break;
        case "Create new Julia Transform":
          factorialPage.setGameType("Julia Transform");
          description = (ChaosGameDescriptionFactory.createEmpty());
          break;
        case "Julia Transform":
          factorialPage.setGameType("Julia Transform");
          description = ChaosGameDescriptionFactory.createJuliaTransformation(new Complex(-0.7, 0.1));
          break;
        case "Sierpinski Triangle":
          factorialPage.setGameType("Affine Transform");
          description = (ChaosGameDescriptionFactory.createSierpinski());
          break;
        case "Barnsley Fern":
          factorialPage.setGameType("Affine Transform");
          description = (ChaosGameDescriptionFactory.createBarnsleyFern());
          break;
        case "Lévy Dragon":
          factorialPage.setGameType("Affine Transform");
          description = ChaosGameDescriptionFactory.createLevyDragon();
          break;
        case "Heighway Dragon":
          factorialPage.setGameType("Affine Transform");
          description = ChaosGameDescriptionFactory.createHeighwayDragon();
          break;
        case "Read from file":
          readFromFile();
          if(factorialPage.getDescription().getTransforms().get(0) instanceof JuliaTransform){
            factorialPage.setGameType("Julia Transform");
          } else {
            factorialPage.setGameType("Affine Transform");
          }
      }
    }
    factorialPage.setDescription(description);
    screenController.addScreenContent(factorialPage, "factorial page");
    screenController.getScreenContent("factorial page").setUp();
    screenController.gameChanged("switch page", "factorial page");
  }

  public void readFromFile() {
    factorialPage.setGameType("Julia Transform");
    ChaosGameFileHandler fileHandler =
        new ChaosGameFileHandler(new ArrayList<>(), new Vector2D(0, 0),
            new Vector2D(MainLogic.width * 0.7f, MainLogic.height * 0.7f));
    FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter(
                "files (*.txt)", "*.txt"));
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
      try {
        factorialPage.setDescription(fileHandler.readFromFile(selectedFile.getPath()));
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }
}
