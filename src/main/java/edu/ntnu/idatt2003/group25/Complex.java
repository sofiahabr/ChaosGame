package edu.ntnu.idatt2003.group25;

public class Complex extends Vector2D {

  /**
   *
   */

  double realPart;
  double imaginaryPart;
  public Complex(double realPart, double imaginaryPart) {
    super(realPart, imaginaryPart);
  }

  public Complex sqrt(){
    double absZ = Math.sqrt(Math.pow(getX0(), 2) + Math.pow(getX1(), 2));
    double seperatedReal = getX0() + absZ;
    double absDenominator = Math.sqrt(Math.pow(seperatedReal, 2) + Math.pow(getX1(), 2));

    double newRealPart = Math.sqrt(absZ)*seperatedReal / absDenominator;
    double newImaginaryPart = Math.sqrt(absZ)*getX1() / absDenominator;

    return new Complex(newRealPart, newImaginaryPart);
  }


}
