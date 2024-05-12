package edu.ntnu.idatt2003.group25.model;

public interface ChaosGameSubject {
  void addObserver(ChaosGameObserver observer);
  void removeObserver(ChaosGameObserver observer);
  void updateObserver(String string);
}
