package edu.ntnu.idatt2003.group25.model;

/**
 * The Matrix2x2 class represents a 2x2 matrix.
 */
public class Matrix2x2 {

  double a00;
  double a01;
  double a10;
  double a11;

  /**
   * Constructor of Matrix2x2 objects with four double values.
   *
   * @param a00 double a00 represents the value in the first row and first column of the matrix.
   * @param a01 double a01 represents the value in the first row and second column of the matrix.
   * @param a10 double a10 represents the value in the second row and first column of the matrix.
   * @param a11 double a11 represents the value in the second row and second column of the matrix.
   */
  public Matrix2x2(double a00, double a01, double a10, double a11) {
    this.a00 = a00;
    this.a01 = a01;
    this.a10 = a10;
    this.a11 = a11;
  }

  /**
   * The multiply method multiplies a Matrix2x2 object with a Vector2D object.
   *
   * @param vector a Vector2D object to be multiplied with the Matrix2x2 object.
   * @return a new Vector2D object representing the result of the multiplication.
   */
  public Vector2D multiply(Vector2D vector) {
    double x0 = a00 * vector.getX0() + a01 * vector.getX1();
    double x1 = a10 * vector.getX0() + a11 * vector.getX1();
    return new Vector2D(x0, x1);
  }

  /**
   * The getA00 method returns the value in the first row and first column of the matrix.
   *
   * @return a double representing a00 value.
   */
  public double getA00() {
    return a00;
  }

  /**
   * The getA01 method returns the value in the first row and second column of the matrix.
   *
   * @return a double representing a01 value.
   */
  public double getA01() {
    return a01;
  }

  /**
   * The getA10 method returns the value in the second row and first column of the matrix.
   *
   * @return a double representing a10 value.
   */
  public double getA10() {
    return a10;
  }

  /**
   * The getA11 method returns the value in the second row and second column of the matrix.
   *
   * @return a double representing a11 value.
   */
  public double getA11() {
    return a11;
  }

  /**
   * The setA00 method sets the value in the first row and first column of the matrix.
   *
   * @param a00 the new value of a00.
   */
  public void setA00(double a00) {
    this.a00 = a00;
  }

  /**
   * The setA01 method sets the value in the first row and second column of the matrix.
   *
   * @param a01 the new value of a01.
   */
  public void setA01(double a01) {
    this.a01 = a01;
  }

  /**
   * The setA10 method sets the value in the second row and first column of the matrix.
   *
   * @param a10 the new value of a10.
   */

  public void setA10(double a10) {
    this.a10 = a10;
  }

  /**
   * The setA11 method sets the value in the second row and second column of the matrix.
   *
   * @param a11 the new value of a11.
   */
  public void setA11(double a11) {
    this.a11 = a11;
  }

  /**
   * The toString method returns a string representation of the Matrix2x2 object.
   *
   * @return a string representation of the Matrix2x2 object.
   */
  @Override
  public String toString() {
    return a00 + ", " + a01 + ", " + a10 + ", " + a11;
  }
}

