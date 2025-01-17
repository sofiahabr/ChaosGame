package edu.ntnu.idatt2003.group25.model;

/**
 * The type Complex.
 */
public class Complex extends Vector2D {

  /**
   * The Real part.
   */

  double realPart;
  /**
   * The Imaginary part.
   */
  double imaginaryPart;

  /**
   * Constructor of complex objects with real and imaginary parts.
   *
   * @param realPart      double realPart illustrates the real part of the complex number.
   * @param imaginaryPart double imaginaryPart illustrates the imaginary part of the complex number.
   */
  public Complex(double realPart, double imaginaryPart) {
    super(realPart, imaginaryPart);
  }

  /**
   * Calculation of the square root of a complex number.
   *
   * @return a complex object representing the square root of a complex number.
   */

  public Complex sqrt() {
    double newRealPart =
        Math.sqrt(0.5 * (Math.sqrt(Math.pow(getX0(), 2) + Math.pow(getX1(), 2)) + getX0()));
    double newImaginaryPart = Math.signum(getX1())
        * Math.sqrt(0.5 * (Math.sqrt(Math.pow(getX0(), 2) + Math.pow(getX1(), 2)) - getX0()));

    return new Complex(newRealPart, newImaginaryPart);
  }

  /**
   * Subtracts another Vector from this Complex number and returns the result
   * as a new Complex number.
   *
   * @param other The Vector2D to be subtracted.
   * @return A new Vector2D representing the difference between this vector
   * and the specified vector.
   */
  @Override
  public Complex subtract(Vector2D other) {
    double newX0 = getX0() - other.getX0();
    double newX1 = getX1() - other.getX1();

    return new Complex(newX0, newX1);
  }


}
