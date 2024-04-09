package edu.ntnu.idatt2003.group25.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChaosGame implements ChaosGameSubject{
  private ChaosCanvas canvas;
  private ChaosGameDescription description;
  private Vector2D currentPoint;  // punktet den ved en hver tid befinner seg
  public Random random = new Random();
  private List<ChaosGameObserver> observerList = new ArrayList<>();

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

    }
  }

  @Override
  public void addObserver(ChaosGameObserver observer) {
    observerList.add(observer);
  }

  @Override
  public void removeObserver(ChaosGameObserver observer) {
    observerList.remove(observer);
  }

  @Override
  public void updateObserver() {
    for (ChaosGameObserver observer : observerList) {
      observer.gameChanged();
    }
  }
}

