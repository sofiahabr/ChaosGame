package edu.ntnu.idatt2003.group25.model;

interface ChaosGameObserver {
  void gameChanged();
}
interface ChaosGameSubject {
  void addObserver(ChaosGameObserver observer);
  void removeObserver(ChaosGameObserver observer);
  void updateObserver();
}

/**
 * Temporary observer who will be used in GUI for updating ChaosGame
 */
//TODO add functionality for the observers
class ObserverObject implements ChaosGameObserver {
  @Override
  public void gameChanged() {
    System.out.println("Update observer");
  }

}

