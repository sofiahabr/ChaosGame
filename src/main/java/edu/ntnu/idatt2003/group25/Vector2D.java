package edu.ntnu.idatt2003.group25;

/**
 * The Vector2D class represents a two-dimensional vector with components x0 and x1.
 * It provides methods for basic vector operations such as addition and subtraction.
 *
 */

public class Vector2D {
  /**
   * The x0 component of the vector.
   */
  private double x0;
  /**
   * The x1 component of the vector.
   */
  private double x1;

  /**
   * Constructs a Vector2D with the specified components.
   *
   * @param x0 The x0 component of the vector.
   * @param x1 The x1 component of the vector.
   */
  public Vector2D(double x0, double x1){
    this.x0 = x0;
    this.x1 = x1;
  }

  /**
   * Gets the x0 component of the vector.
   *
   * @return The x0 component of the vector.
   */
  public double getX0(){
    return x0;
  }
  /**
   * Gets the x1 component of the vector.
   *
   * @return The x1 component of the vector.
   */
  public double getX1(){
    return x1;
  }
  /**
   * Adds another Vector2D to this vector and returns the result as a new Vector2D.
   *
   * @param other The Vector2D to be added.
   * @return A new Vector2D representing the sum of this vector and the specified vector.
   */
  public Vector2D add(Vector2D other){
    double newX0 = x0 + other.getX0();
    double newX1 = x1 + other.getX1();

    return new Vector2D(newX0, newX1);
  }
  /**
   * Subtracts another Vector2D from this vector and returns the result as a new Vector2D.
   *
   * @param other The Vector2D to be subtracted.
   * @return A new Vector2D representing the difference between this vector and the specified vector.
   */
  public Vector2D subtract(Vector2D other){
    double newX0 = x0 - other.getX0();
    double newX1 = x1 - other.getX1();

    return new Vector2D(newX0, newX1);
  }
}
