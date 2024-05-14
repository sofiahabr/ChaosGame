package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.model.ChaosGameObserver;
import edu.ntnu.idatt2003.group25.model.ChaosGameSubject;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public abstract class View implements ChaosGameSubject {
  private List<ChaosGameObserver> observers = new ArrayList<>();
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
    for (ChaosGameObserver observer : observers){
      observer.gameChanged(event, info);
    }
  }

  public abstract Pane getPane();
  public abstract void setUp();
  public abstract void update();
}
