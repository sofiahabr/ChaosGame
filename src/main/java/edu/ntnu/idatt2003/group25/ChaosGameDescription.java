package edu.ntnu.idatt2003.group25;

import java.util.List;

public class ChaosGameDescription {

  Vector2D minCoords;
  Vector2D maxCoords;
  List<Transform2D> transforms;

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
}
