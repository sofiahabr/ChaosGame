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
    double newRealPart = Math.sqrt(0.5*(Math.sqrt(Math.pow(getX0(),2) + Math.pow(getX1(),2)) + getX0()));
    double newImaginaryPart = Math.signum(getX1())*Math.sqrt(0.5*(Math.sqrt(Math.pow(getX0(), 2) + Math.pow(getX1(), 2)) - getX0()));

    return new Complex(newRealPart, newImaginaryPart);
  }


}
