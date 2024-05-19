package edu.ntnu.idatt2003.group25.model;

/**
 * The ChaosGameSubject interface should be implemented by any classes that need to notify observers
 * of changes in the ChaosGame.
 */

public interface ChaosGameSubject {

  void addObserver(ChaosGameObserver observer);

  void removeObserver(ChaosGameObserver observer);

  void updateObserver(String event, String info);
}
