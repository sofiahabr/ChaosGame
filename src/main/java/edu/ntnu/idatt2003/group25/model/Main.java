package edu.ntnu.idatt2003.group25.model;

import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    System.out.println("Hello World");

    Vector2D min = new Vector2D(25, 25);
    Vector2D max = new Vector2D(75, 125);

    JuliaTransform julia = new JuliaTransform(new Complex(1, 2), 1);
    AffineTransform2D affine = new AffineTransform2D(new Matrix2x2(1,0,0,1), new Vector2D(1,2));
    List<Transform2D> transformList = new ArrayList<>();
    transformList.add(julia);
    transformList.add(affine);

    ChaosCanvas canvas = new ChaosCanvas(100, 150, min, max);
    ChaosGameDescription description = new ChaosGameDescription(transformList, min, max);
    ChaosGame game = new ChaosGame(description, canvas.getWidth(), canvas.getHeight());

    game.runSteps(4);

  }
}
