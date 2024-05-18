package edu.ntnu.idatt2003.group25.model;

import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import java.util.List;

public class ChaosGameDescription {

  private Vector2D minCoords;
  private Vector2D maxCoords;
  private List<Transform2D> transforms;

  public ChaosGameDescription(List<Transform2D> transforms, Vector2D minCoords, Vector2D maxCoords) {
    this.transforms = transforms;
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
  }

  public List<Transform2D> getTransforms() {
    return transforms;
  }

  public Vector2D getMaxCoords() {
    return maxCoords;
  }

  public Vector2D getMinCoords() {
    return minCoords;
  }

  public void setMinCoords(Vector2D minCoords) {
    this.minCoords = minCoords;
  }

  public void setMaxCoords(Vector2D maxCoords) {
    this.maxCoords = maxCoords;
  }

  public void setTransforms(List<Transform2D> transforms) {
    this.transforms = transforms;
  }
}
