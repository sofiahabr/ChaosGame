package edu.ntnu.idatt2003.group25;

public class Matrix2x2 {

  double a00;
  double a01;
  double a10;
  double a11;

  public Matrix2x2(double a00,double a01, double a10, double a11) {
    this.a00 = a00;
    this.a01 = a01;
    this.a10 = a10;
    this.a11 = a11;
  }

  public Vector2D multiply(Vector2D vector) {
    double x0 = a00 * vector.getX0() + a01 * vector.getX1();
    double x1 = a10* vector.getX0() + a11 * vector.getX1();
    return new Vector2D(x0,x1);
  }

}
