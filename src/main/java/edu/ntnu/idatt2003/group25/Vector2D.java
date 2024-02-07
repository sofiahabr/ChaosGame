package edu.ntnu.idatt2003.group25;

import java.util.Vector;

public class Vector2D {
  private double x0;
  private double x1;

  public Vector2D(double x0, double x1){
    this.x0 = x0;
    this.x1 = x1;
  }

  public double getX0(){
    return x0;
  }
  public double getX1(){
    return x1;
  }

  public Vector2D add(Vector2D other){
    double newX0 = x0 + other.getX0();
    double newX1 = x1 + other.getX1();

    return new Vector2D(newX0, newX1);
  }
}