package edu.ntnu.idatt2003.group25.model.transforms;

import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Vector2D;

/**
 * The JuliaTransform class represents Julia transformations and implements the transform2D interface.
 */
public class JuliaTransform implements Transform2D {
  private Complex point;
  private final int sign;

  public JuliaTransform(Complex point, int sign){
    this.point = point;
    this.sign = sign;
  }
  @Override
  public Vector2D transform(Vector2D otherPoint) {
    Complex newPoint = new Complex(otherPoint.getX0(), otherPoint.getX1());

    Complex newComplex = newPoint.subtract(point);
    return newComplex.sqrt().scale(sign);
  }
  @Override
  public String toString() {
    return point.toString();
  }

  public void setComplex(Complex complex) {
    this.point = new Complex(complex.getX0(), complex.getX1());
  }
  public Complex getComplex() {
    return point;
  }
}
