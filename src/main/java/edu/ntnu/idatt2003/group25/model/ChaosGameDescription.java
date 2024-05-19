package edu.ntnu.idatt2003.group25.model;

import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import java.util.List;

/**
 * Class for ChaosGameDescription, which represents all necessary information for the ChaosGame.
  */
public class ChaosGameDescription {

  private Vector2D minCoords;
  private Vector2D maxCoords;
  private List<Transform2D> transforms;

  /**
   * Constructs a ChaosGameDescription object with specified transformation and coordinate bounds.
   *
   * @param transforms List of Transform2D objects representing the transformations used.
   * @param maxCoords Vector2D object representing the maximum coordinate bounds.
   * @param minCoords Vector2D object representing the minimum coordinate bounds.
   */
  public ChaosGameDescription(List<Transform2D> transforms, Vector2D minCoords,
      Vector2D maxCoords) {
    this.transforms = transforms;
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
  }

  /**
   * Returns the list of transformations used in the ChaosGame.
   *
   * @return List of Transform2D objects.
   */
  public List<Transform2D> getTransforms() {
    return transforms;
  }
  /**
   * Returns the maximum coordinate bounds.
   *
   * @return Vector2D object representing the maximum coordinate bounds.
   */

  public Vector2D getMaxCoords() {
    return maxCoords;
  }

  /**
   * Returns the minimum coordinate bounds.
   *
   * @return vector2D object representing the minimum coordinate bounds.
   */
  public Vector2D getMinCoords() {
    return minCoords;
  }

  public void setMinCoords(Vector2D minCoords) {
    this.minCoords = minCoords;
  }

  /**
   * Sets the maximum coordinate bounds.
   *
   * @param maxCoords vector2D object representing the maximum coordinate bounds.
   */
  public void setMaxCoords(Vector2D maxCoords) {
    this.maxCoords = maxCoords;
  }

  /**
   * Sets the list of transformations used in the ChaosGame.
   *
   * @param transforms List of Transform2D objects.
   */
  public void setTransforms(List<Transform2D> transforms) {
    this.transforms = transforms;
  }
}
