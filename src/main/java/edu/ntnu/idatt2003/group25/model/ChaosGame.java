package edu.ntnu.idatt2003.group25.model;

import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChaosGame{
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
  public int getHeight() {
    return canvas.getHeight();
  }
  public int getWidth() {
    return canvas.getWidth();
  }
  public void setHeight(int height) {
    canvas.setHeight(height);
  }
  public void setWidth(int width) {
    canvas.setWidth(width);
  }

  public void setDescription(ChaosGameDescription description) {
    this.description = description;
  }
}

