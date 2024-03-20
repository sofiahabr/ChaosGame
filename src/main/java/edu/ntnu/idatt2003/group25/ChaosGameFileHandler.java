package edu.ntnu.idatt2003.group25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.SocketHandler;

public class ChaosGameFileHandler extends ChaosGameDescription{

  public ChaosGameFileHandler(List<Transform2D> transforms, Vector2D minCoords,
      Vector2D maxCoords) {
    super(transforms, minCoords, maxCoords);
  }

  public ChaosGameDescription readFromFile(String path) throws FileNotFoundException {
    try {
      Scanner scanner = new Scanner(new File(path));

      String firstLine = scanner.nextLine();
      String transformName = firstLine.split("#")[0];

      String[] minimumValues = scanner.nextLine().split("(,)|(#)");
      double min0 = Double.parseDouble(minimumValues[0]);
      double min1 = Double.parseDouble(minimumValues[1]);

      Vector2D min = new Vector2D(min0, min1);

      String[] maximumValues = scanner.nextLine().split("(,)|(#)");
      double max0 = Double.parseDouble(maximumValues[0]);
      double max1 = Double.parseDouble(maximumValues[1]);

      Vector2D max = new Vector2D(max0, max1);

      List<Transform2D> transforms = new ArrayList<>();
      switch (transformName) {
        case ("Julia"):
          String[] c = scanner.nextLine().split("(,)|(#)");
          double x0 = Double.parseDouble(c[0]);
          double x1 = Double.parseDouble(c[1]);

          Complex complex = new Complex(x0, x1);
          JuliaTransform julia = new JuliaTransform(complex, 1);
          transforms.add(julia);

          break;
        case ("Affine2D"):
          while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("(,)|(#)");
            double a00 = Double.parseDouble(line[0]);
            double a01 = Double.parseDouble(line[1]);
            double a10 = Double.parseDouble(line[2]);
            double a11 = Double.parseDouble(line[3]);

            double b1 = Double.parseDouble(line[4]);
            double b2 = Double.parseDouble(line[5]);

            Matrix2x2 matrix = new Matrix2x2(a00, a01, a10, a11);
            Vector2D vector = new Vector2D(b1, b2);

            AffineTransform2D affine = new AffineTransform2D(matrix, vector);
            transforms.add(affine);

            break;
          }
      }
      return new ChaosGameDescription(transforms, min, max);

    } catch (FileNotFoundException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return null;
    }

  public void writeToFile(ChaosGameDescription description, String path) {

  }
}


