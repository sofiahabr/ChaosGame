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
import edu.ntnu.idatt2003.group25.view.MainView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.FileChooser;

public class InitializePageController extends Controller{
  ScreenController screenController;
  public InitializePageController(ScreenController screenController){
    this.screenController = screenController;
  }

  @Override
  public void gameChanged(String event, String transformName) {
    System.out.println(transformName);
    if(event.equals("button clicked")){
      switch (transformName) {
        case "Create new Affine Transform":
          List<Transform2D> transforms = new ArrayList<>();
          transforms.add(new AffineTransform2D(new Matrix2x2(0,0,0,0), new Vector2D(0,0)));
          MainLogic.description = new ChaosGameDescription(transforms, new Vector2D(0,0), new Vector2D(1,1));
          break;
        case "Create new Julia Transform":
          transforms = new ArrayList<>();
          transforms.add(new JuliaTransform(new Complex(0,0),1));
          MainLogic.description.getTransforms().add(new JuliaTransform(new Complex(0, 0), 1));
          break;
        case "Julia Transform":
          System.out.println("Julia");
          MainLogic.description =
              (ChaosGameDescriptionFactory.createJuliaTransformation(new Complex(-0.7, 0.1)));
          break;
        case "Sierpinski Triangle":
          System.out.println("Sierpinski");
          MainLogic.description = ChaosGameDescriptionFactory.createSierpinski();
          break;
        case "Barnsley Fern":
          System.out.println("Barnsley fern");
          MainLogic.description = ChaosGameDescriptionFactory.createBarnsleyFern();
          break;
        case "Read from file":
          ChaosGameFileHandler fileHandler =
              new ChaosGameFileHandler(new ArrayList<>(), new Vector2D(0, 0),
                  new Vector2D(MainLogic.width * 0.7f, MainLogic.height * 0.7f));
          FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().add(
//            new FileChooser.ExtensionFilter(
//                "files (*.txt)", "*.txt"));
          File selectedFile = fileChooser.showOpenDialog(null);
          if (selectedFile != null) {
            try {
              MainLogic.description = fileHandler.readFromFile(selectedFile.getPath());
            } catch (Exception e) {
              e.printStackTrace();
            }
            break;
          }
      }
      screenController.getScreenContent("factorial page").setUp();
      screenController.gameChanged("switch page", "factorial page");
    }
  }
}
