package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.model.ChaosGameObserver;
import edu.ntnu.idatt2003.group25.model.ChaosGameSubject;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;

/**
 * The type View, that represents a page to display on the screen.
 */
public abstract class View implements ChaosGameSubject {
  /**
   * The Observers, a list of observers that observes the view.
   */
  private List<ChaosGameObserver> observers = new ArrayList<>();

  /**
   * The method that adds an observer to the view.
   *
   * @param observer the observer that is to be added.
   */
  @Override
  public void addObserver(ChaosGameObserver observer) {
    observers.add(observer);
  }

  /**
   * The method that removes an observer from the view.
   *
   * @param observer the observer that is to be removed.
   */

  @Override
  public void removeObserver(ChaosGameObserver observer) {
    observers.remove(observer);
  }

  /**
   * The method that updates the observers of the view.
   *
   * @param event the event that has happened.
   * @param info the information that is to be sent to the observers
   */

  @Override
  public void updateObserver(String event, String info) {
    for (ChaosGameObserver observer : observers) {
      observer.gameChanged(event, info);
    }
  }

  /**
   * A method for accessing panes of the views.
   *
   * @return the pane
   */
  public abstract BorderPane getPane();

  /**
   * The method for setting up the UI- layout.
   */
  public abstract void setUp();
}
