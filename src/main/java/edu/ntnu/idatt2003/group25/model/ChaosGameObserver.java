package edu.ntnu.idatt2003.group25.model;

/**
 * The ChaosGameObserver interface should be implemented by any classes that need to be notified
 * of changes in the ChaosGame.
 */

public interface ChaosGameObserver {
  void gameChanged(String event, String info);
}

