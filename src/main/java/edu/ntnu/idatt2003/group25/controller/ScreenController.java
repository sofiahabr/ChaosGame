package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.view.View;
import java.util.HashMap;
import javafx.scene.Scene;

/**
 * The type Screen controller, the controller that controls what view is displayed on the screen.
 */
public class ScreenController extends Controller{
  /**
   * The Screen contents, a list with all the views to be displayed on the screen.
   */
  HashMap<String, View> screenContents = new HashMap<>();
  /**
   * The scene, the scene that is displayed on the screen.
   */
  private Scene scene;

  /**
   * Instantiates a new Screen controller.
   * The screen controller is used to change the view displayed on the screen.
   *
   * @param scene the scene where the view is displayed
   */
  public ScreenController(Scene scene) {
    this.scene = scene;
  }

  /**
   * A method that gets the view based on the name of the view.
   *
   * @param name the name of the view. The name is the key in the hashmap.
   * @return the view that is displayed on the screen.
   */
  public View getScreenContent(String name){
    return screenContents.get(name);
  }

  /**
   * The method that adds a view to the screen contents.
   *
   * @param view the view
   * @param name the name
   */
  public void addScreenContent(View view, String name){
    screenContents.put(name, view);
  }

  /**'
   * The method that changes the view displayed on the screen.
   *
   * @param event the event that has happened
   * @param screenName the name of the view that is to be displayed
   */
  @Override
  public void gameChanged(String event, String screenName) {
    if(screenContents.containsKey(screenName)){
      scene.setRoot(getScreenContent(screenName).getPane());
    }
  }

}
