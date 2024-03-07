package edu.ntnu.idatt2003.group25;

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
    for(int i = 0; i < steps; i++ ) {
      Vector2D randomPoint = new Vector2D(random.nextInt(), random.nextInt());
      Vector2D transformedPoint = description.getTransforms().getFirst().transform(randomPoint);
      // TODO: velge transformasjon

      currentPoint.add(transformedPoint);
    }
  }

}
