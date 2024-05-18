package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.model.ChaosGameObserver;
import edu.ntnu.idatt2003.group25.model.ChaosGameSubject;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Menu implements ChaosGameSubject {
  private ScreenController screenController;
  private List<ChaosGameObserver> observers = new ArrayList<>();

  public Menu(ScreenController screenController) {
        this.screenController = screenController;
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

  public void inputFieldStyle(TextField inputField, String promptText, int height, int width) {
    inputField.setMinHeight(height);
    inputField.setMaxWidth(width);
    inputField.setPromptText(promptText);
  }

  public void addStyle(Button button, String color, int width) {
    String colorInit = "-fx-background-color: " + color + ";";
    String widthInit = "-fx-min-width: " + width + ";";

    button.setStyle(colorInit + widthInit);
  }
}
