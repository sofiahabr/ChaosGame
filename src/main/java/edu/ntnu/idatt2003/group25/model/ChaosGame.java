package edu.ntnu.idatt2003.group25.model;

import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import java.util.List;
import java.util.Random;
/**
 * Class creating a ChaosGame object.
 */

public class ChaosGame {
  private final ChaosCanvas canvas;
  private ChaosGameDescription description;
  private Vector2D currentPoint;
  private final Random random = new Random();

  /**
   * Constructor for ChaosGame.
   *
   * @param description all necessary information for the ChaosGame.
   * @param width int value representing the width of the ChaosGame canvas.
   * @param height int value representing the height of the ChaosGame canvas.
   */

  public ChaosGame(ChaosGameDescription description, int width, int height) {
    this.description = description;
    this.canvas = new ChaosCanvas(width, height, description.getMinCoords(),
        description.getMaxCoords());
    this.currentPoint = new Vector2D(0, 0);
  }

  /**
   * Method for accessing the canvas.
   *
   * @return canvas object.
   */

  public ChaosCanvas getCanvas() {
    return canvas;
  }

  /**
   * Method for calculating the next points in the ChaosGame.
   *
   * @param steps int value represents the number of steps to be calculated.
   */

  public void runSteps(int steps) {
    List<Transform2D> list = description.getTransforms();
    for (int i = 0; i < steps; i++) {
      Transform2D transform2D = list.get(random.nextInt(list.size()));
      Vector2D newPoint = transform2D.transform(currentPoint);

      canvas.putPixel(newPoint);
      this.currentPoint = newPoint;


    }
  }

  /**
   * Method for accessing the height of the canvas.
   *
   * @return int value representing the height of the canvas.
   */
  public int getHeight() {
    return canvas.getHeight();
  }

  /**
   * Method for accessing the width of the canvas.
   *
   * @return int value representing the width of the canvas.
   */
  public int getWidth() {
    return canvas.getWidth();
  }

  /**
   * Method for setting the height of the canvas.
   *
   * @param height int value representing the new height of the canvas.
   */
  public void setHeight(int height) {
    canvas.setHeight(height);
  }

  /**
   * Method for setting the width of the canvas.
   *
   * @param width int value representing the new width of the canvas.
   */
  public void setWidth(int width) {
    canvas.setWidth(width);
  }

  /**
   * Method for setting description of the ChaosGame.
   *
   * @param description ChaosGameDescription object representing the new description.
   */
  public void setDescription(ChaosGameDescription description) {
    this.description = description;
  }
}

