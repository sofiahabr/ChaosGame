package edu.ntnu.idatt2003.group25;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChaosGame {
  private ChaosCanvas canvas;
  private ChaosGameDescription description;
  private Vector2D currentPoint;  // punktet den ved en hver tid befinner seg
  public Random random = new Random();

  public ChaosGame(ChaosGameDescription description, int width, int height) {
    this.description = description;
    this.canvas = new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
    this.currentPoint = new Vector2D(0, 0);
  }

  public ChaosCanvas getCanvas() {
   return canvas;
  }

  public void runSteps(int steps) {
    List<Transform2D> list = description.getTransforms();
    for(int i = 0; i < steps; i++ ) {
      Transform2D transform2D = list.get(random.nextInt(list.size()));
      Vector2D newPoint = transform2D.transform(currentPoint);

      canvas.putPixel(newPoint);
      this.currentPoint = newPoint;

      System.out.println(currentPoint);

//      double randomX0 = canvas.getMinCoords().getX0() + random.nextDouble() * (canvas.getMaxCoords().getX0() - canvas.getMinCoords().getX0());
//      double randomX1 = canvas.getMinCoords().getX1() + random.nextDouble() * (canvas.getMaxCoords().getX1() - canvas.getMinCoords().getX1());
//      Vector2D randomPoint = new Vector2D(randomX0, randomX1);
//
//
//      Vector2D transformedPoint = description.getTransforms().get(0).transform(randomPoint);
//
//      System.out.println(randomPoint);
//      System.out.println(transformedPoint);
//      Vector2D newPoint = currentPoint.add(transformedPoint);
//      System.out.println(newPoint);
//      if (newPoint.getX1() < canvas.getMaxCoords().getX1() &&
//          newPoint.getX0() < canvas.getMaxCoords().getX0() &&
//          newPoint.getX0() > canvas.getMinCoords().getX0() &&
//          newPoint.getX1() > canvas.getMinCoords().getX1()) {
//
//        System.out.println(newPoint);
//        canvas.putPixel(newPoint);
//        list.add(newPoint);
//        this.currentPoint = newPoint;
      }
    }
  }
