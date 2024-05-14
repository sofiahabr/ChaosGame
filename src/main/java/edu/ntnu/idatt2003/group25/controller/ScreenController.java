package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.view.MainView;
import edu.ntnu.idatt2003.group25.view.View;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ScreenController extends Controller{
  HashMap<String, View> screenContents = new HashMap<>();
  private Scene scene;

  public ScreenController(Scene scene) {
    this.scene = scene;
  }

  public View getScreenContent(String name){
    return screenContents.get(name);
  }
  public void addScreenContent(View view, String name){
    screenContents.put(name, view);
  }

  @Override
  public void gameChanged(String event, String screenName) {
    scene.setRoot(getScreenContent(screenName).getPane());
  }

}
