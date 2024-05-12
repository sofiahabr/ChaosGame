package edu.ntnu.idatt2003.group25.model.transforms;

import edu.ntnu.idatt2003.group25.model.Vector2D;

/**
 * Transform2D is an interface for transforms.
 * C
 */
public interface Transform2D {

  public Vector2D transform(Vector2D point);

  public String toString();
}
