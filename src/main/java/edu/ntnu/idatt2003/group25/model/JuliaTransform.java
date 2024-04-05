package edu.ntnu.idatt2003.group25.model;

public class JuliaTransform implements Transform2D {
  private final Complex point;
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
}
