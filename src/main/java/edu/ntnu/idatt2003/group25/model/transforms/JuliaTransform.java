package edu.ntnu.idatt2003.group25.model.transforms;

import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Vector2D;

/**
 * The class represents Julia transformations and implements the transform2D interface.
 */
public class JuliaTransform implements Transform2D {
  private Complex point;
  private final int sign;

  /**
   * Constructor a JuliaTransform object with a specified point and sign.
   *
   * @param point a complex value representing the point in a complex pane.
   * @param sign an integer represents the direction of the transformation
   */

  public JuliaTransform(Complex point, int sign) {
    this.point = point;
    this.sign = sign;
  }

  /**
   * Transformation a given point in the 2D plane using Julia set transformation.
   *
   * @param otherPoint the point in the 2D plane to be transformed, represented as a vector2D.
   * @return a new vector object representing the transformed point.
   */
  @Override
  public Vector2D transform(Vector2D otherPoint) {
    Complex newPoint = new Complex(otherPoint.getX0(), otherPoint.getX1());

    Complex newComplex = newPoint.subtract(point);
    return newComplex.sqrt().scale(sign);
  }

  /**
   * Returns a string representation of the transformation point.
   *
   * @return a string representing of the complex point used in the transformation.
   */
  @Override
  public String toString() {
    return point.toString();
  }

  /**
   * Sets the complex point to a specified number.
   *
   * @param complex the complex number to be set as the transformation point.
   */
  public void setComplex(Complex complex) {
    this.point = new Complex(complex.getX0(), complex.getX1());
  }

  /**
   * Gets the current transformation point.
   *
   * @return the complex number representing the current transformation point.
   */
  public Complex getComplex() {
    return point;
  }
}
