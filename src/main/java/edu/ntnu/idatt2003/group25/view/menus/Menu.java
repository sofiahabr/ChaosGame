package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.Controller;
import edu.ntnu.idatt2003.group25.controller.FactorialPageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.ChaosGameObserver;
import edu.ntnu.idatt2003.group25.model.ChaosGameSubject;
import edu.ntnu.idatt2003.group25.view.FactorialPage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class Menu implements ChaosGameSubject {
  private List<ChaosGameObserver> observers = new ArrayList<>();

  public Menu(ScreenController screenController) {
    addObserver(screenController);
  }

  @Override
  public void addObserver(ChaosGameObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ChaosGameObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void updateObserver(String event, String info) {
    for (ChaosGameObserver observer : observers) {
      observer.gameChanged(event, info);
    }
  }

  public abstract void setUp();

  public abstract Node getMenu();
}
