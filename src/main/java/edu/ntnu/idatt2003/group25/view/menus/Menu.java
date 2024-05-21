package edu.ntnu.idatt2003.group25.view.menus;

import edu.ntnu.idatt2003.group25.controller.Controller;
import edu.ntnu.idatt2003.group25.model.ChaosGameObserver;
import edu.ntnu.idatt2003.group25.model.ChaosGameSubject;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;

/**
 * Abstract class for the different menus in the application.

 */
public abstract class Menu implements ChaosGameSubject {
  /**
   * List of observers that are observing the menu.
   */
  private final List<ChaosGameObserver> observers = new ArrayList<>();

  /**
   * Constructor for the Menu class.
   *
   * @param controller Controller object that the menu is observing.
   */
  Menu(Controller controller) {
    addObserver(controller);
  }

  /**
   * Adds an observer to the list of observers.
   *
   * @param observer ChaosGameObserver object to be added to the list of observers.
   */
  @Override
  public void addObserver(ChaosGameObserver observer) {
    observers.add(observer);
  }

  /**
   * Removes an observer from the list of observers.
   *
   * @param observer ChaosGameObserver object to be removed from the list of observers.
   */
  @Override
  public void removeObserver(ChaosGameObserver observer) {
    observers.remove(observer);
  }

  /**
   * Notifies all observers in the list of observers.
   * The method update the observers with event and information which are processes by the
   * controller.
   *
   * @param event The event that has occurred.
   * @param info Information about the event that has occurred.
   */
  @Override
  public void updateObserver(String event, String info) {
    for (ChaosGameObserver observer : observers) {
      observer.gameChanged(event, info);
    }
  }

  /**
   * Sets up the menu.
   */
  public abstract void setUp();

  /**
   * Returns the menu.
   *
   * @return Node object representing the menu.
   */
  public abstract Node getMenu();
}
