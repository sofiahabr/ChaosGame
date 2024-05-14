package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosGameObserver;
import edu.ntnu.idatt2003.group25.model.Validation;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import edu.ntnu.idatt2003.group25.view.MainLogic;
import java.util.ArrayList;
import java.util.List;

/**
 * This class function as both observer and subject.
 */
public class ScreenSizeChange extends Controller {
  int newHeight;
  int newWidth;
  private List<ChaosGameObserver> observerList;
  public ScreenSizeChange() {
    observerList = new ArrayList<>();
  }
  public void addObserver(ChaosGameObserver observer) {
    observerList.add(observer);
  }

  @Override
  public void gameChanged(String height, String width) {
    newHeight = Validation.verifyPositiveInt(height,0);
    newWidth = Validation.verifyPositiveInt(width,0);
    if (newHeight > 0) {
      MainLogic.height = newHeight;
    }
    if (newWidth > 0) {
      MainLogic.width = newWidth;
    }
  }
  public void notifyObservers() {
    for (ChaosGameObserver observer : observerList) {
      observer.gameChanged("" + newHeight,"" + newWidth);
    }
  }
}
