package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.MainLogic;
import java.io.File;
import java.util.ArrayList;
import javafx.stage.FileChooser;

/**
 * The type controller for the initialize page.
 */
public class InitializePageController extends Controller {
  /**
   * The Screen controller that controls what view is displayed on the screen.
   */
  ScreenController screenController;
  /**
   * The Factorial page, the view where the factorial game is displayed.
   */
  FactorialPage factorialPage;

  /**
   * Instantiates a new Initialize page controller.
   * The initialize page is the first page the user sees when the application is started.
   *
   * @param screenController the screen controller
   */
  public InitializePageController(ScreenController screenController) {
    this.screenController = screenController;
    this.factorialPage = new FactorialPage(screenController);
  }

  /**
   * The method that handles changes in the view InitializePage.
   * The method changes the game type and the description of the game
   * based on the event and the transform name.
   *
   * @param event         the event that has happened
   * @param transformName the name of the transform the user wishes to use
   */

  @Override
  public void gameChanged(String event, String transformName) {
    ChaosGameDescription description = ChaosGameDescriptionFactory.createEmpty();
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
          description =
              ChaosGameDescriptionFactory.createJuliaTransformation(new Complex(-0.7, 0.1));
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
              description = fileHandler.readFromFile(selectedFile.getPath());
            } catch (Exception ignored) {

            }
          }
          if (description.getTransforms().getFirst() instanceof JuliaTransform) {
            factorialPage.setGameType("Julia Transform");
          } else if (description.getTransforms().getFirst() instanceof AffineTransform2D) {
            factorialPage.setGameType("Affine Transform");
          }
          break;
        default:

          break;
      }
    }
    factorialPage.setDescription(description);
    factorialPage.reset();
    screenController.addScreenContent(factorialPage, "factorial page");
    screenController.getScreenContent("factorial page").setUp();
    screenController.gameChanged("switch page", "factorial page");
  }
}
