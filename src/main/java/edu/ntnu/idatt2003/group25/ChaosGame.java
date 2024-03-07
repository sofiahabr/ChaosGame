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
    List<Vector2D> list = new ArrayList<>();
    for(int i = 0; i < steps; i++ ) {
      int dice = random.nextInt(6);
      Vector2D randomPoint = new Vector2D(dice, dice);
      // TODO: samme ? (1,1)
      Vector2D transformedPoint = description.getTransforms().getFirst().transform(randomPoint);
      // TODO: velge transformasjon

      if (transformedPoint.getX1() < canvas.getMaxCoords().getX1() &&
          transformedPoint.getX0() < canvas.getMaxCoords().getX0() ) {

        System.out.println(currentPoint.add(transformedPoint));
        canvas.putPixel(currentPoint.add(transformedPoint));
        this.currentPoint = currentPoint.add(transformedPoint);
      }
    }
  }

}
