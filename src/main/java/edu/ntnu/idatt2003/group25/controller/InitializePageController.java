package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import edu.ntnu.idatt2003.group25.view.MainView;
import java.util.ArrayList;

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
//        mainView.setDescription(ChaosGameDescriptionFactory.createSierpinski());
        break;
      case "Barnsley Fern":
        System.out.println("Barnsley fern");
        MainView.description = ChaosGameDescriptionFactory.createBarnsleyFern();
//        mainView.setDescription(ChaosGameDescriptionFactory.createBarnsleyFern());
        break;
//      case "Read from file":
//        break;
    }
    try {
      FactorialPage factorialPage = new FactorialPage();
      MainView.mainPage.setCenter(factorialPage.getPane());
    } catch (NullPointerException e) {
      System.out.println(e.getMessage());
    }
  }
}
