package edu.ntnu.idatt2003.group25.model;

public interface ChaosGameObserver {
  void gameChanged(String event, String info);
}

///**
// * Temporary observer who will be used in GUI for updating ChaosGame
// */
////TODO add functionality for the observers
//class ObserverObject implements ChaosGameObserver {
//  @Override
//  public void gameChanged() {
//    System.out.println("Update observer");
//  }


