package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.MainView;
import java.io.File;
import java.util.ArrayList;
import javafx.stage.FileChooser;

public class InitializePageController extends Controller{

  @Override
  public void gameChanged(String transformName) {
    System.out.println(transformName);
    switch (transformName){
//      case "Create new Affine Transform":
//        break;
//      case "Create new Julia Transform":
//        break;
      case "Julia Transform":
        System.out.println("Julia");
        MainView.description = (ChaosGameDescriptionFactory.createJuliaTransformation(new Complex(-0.7, 0.1)));
        break;
      case "Sierpinski Triangle":
        System.out.println("Sierpinski");
        MainView.description = ChaosGameDescriptionFactory.createSierpinski();
        break;
      case "Barnsley Fern":
        System.out.println("Barnsley fern");
        MainView.description = ChaosGameDescriptionFactory.createBarnsleyFern();
        break;
      case "Read from file":
        ChaosGameFileHandler fileHandler = new ChaosGameFileHandler(new ArrayList<>(), new Vector2D(0,0), new Vector2D(MainView.width*0.7f, MainView.height*0.7f));
        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().add(
//            new FileChooser.ExtensionFilter(
//                "files (*.txt)", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
          try {
            MainView.description = fileHandler.readFromFile(selectedFile.getPath());
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        }
    }
    try {
      FactorialPage factorialPage = new FactorialPage();
      MainView.mainPage.setCenter(factorialPage.getPane());
    } catch (NullPointerException e) {
      System.out.println(e.getMessage());
    }
  }
}
