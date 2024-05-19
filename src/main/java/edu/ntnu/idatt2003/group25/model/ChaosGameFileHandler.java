package edu.ntnu.idatt2003.group25.model;

import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * The ChaosGameFileHandler class is responsible for reading and writing files for the ChaosGame.
 */

public class ChaosGameFileHandler extends ChaosGameDescription {
  private static final Logger logger = Logger.getLogger(ChaosGameFileHandler.class.getName());

  /**
   * The constructor for the ChaosGameFileHandler class.
   *
   * @param transforms list of transform2D objects representing the transformations.
   * @param minCoords the minimum coordinates of the plane.
   * @param maxCoords the maximum coordinates of the plane.
   */

  public ChaosGameFileHandler(List<Transform2D> transforms, Vector2D minCoords,
      Vector2D maxCoords) {
    super(transforms, minCoords, maxCoords);
  }

  /**
   * The readFromFile methods reads and convert data from a file to a ChaosGameDescription object.
   *
   * @param path The method takes in the file path
   * @return a ChaosGameDescription with updated values from the file
   * @throws FileNotFoundException if the program doesn't find the file from the filepath
   */

  public ChaosGameDescription readFromFile(String path) throws FileNotFoundException {
    try {
      Scanner scanner = new Scanner(new File(path));

      String firstLine = scanner.nextLine();
      String transformName = firstLine.split("(#)|( )")[0];

      String lineSplitIndicator = "(,)|(#)";

      String[] minimumValues = scanner.nextLine().split(lineSplitIndicator);
      double min0 = Double.parseDouble(minimumValues[0]);
      double min1 = Double.parseDouble(minimumValues[1]);

      Vector2D min = new Vector2D(min0, min1);

      String[] maximumValues = scanner.nextLine().split(lineSplitIndicator);
      double max0 = Double.parseDouble(maximumValues[0]);
      double max1 = Double.parseDouble(maximumValues[1]);

      Vector2D max = new Vector2D(max0, max1);

      List<Transform2D> transforms = new ArrayList<>();
      switch (transformName) {
        case ("Julia"):
          String[] c = scanner.nextLine().split(lineSplitIndicator);
          double x0 = Double.parseDouble(c[0]);
          double x1 = Double.parseDouble(c[1]);

          Complex complex = new Complex(x0, x1);
          JuliaTransform juliaPos = new JuliaTransform(complex, 1);
          JuliaTransform juliaNeg = new JuliaTransform(complex, -1);
          transforms.add(juliaPos);
          transforms.add(juliaNeg);
          break;

        case ("Affine2D"):
          while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(lineSplitIndicator);
            AffineTransform2D affine = getAffineTransform2D(line);
            transforms.add(affine);
          }
          break;
        default:
          logger.warning("Invalid transform type: " + transformName);
      }
      scanner.close();
      return new ChaosGameDescription(transforms, min, max);

    } catch (FileNotFoundException e) {
      logger.severe("Error: " + e.getMessage());
      throw e;
    }
  }

  /**
   * The getAffineTransform2D method creates an AffineTransform2D object from a given line.
   *
   * @param line a string[] with the values for the AffineTransform2D object
   * @return an AffineTransform2D object
   */

  private static AffineTransform2D getAffineTransform2D(String[] line) {
    double a00 = Double.parseDouble(line[0]);
    double a01 = Double.parseDouble(line[1]);
    double a10 = Double.parseDouble(line[2]);
    double a11 = Double.parseDouble(line[3]);

    double b1 = Double.parseDouble(line[4]);
    double b2 = Double.parseDouble(line[5]);

    Matrix2x2 matrix = new Matrix2x2(a00, a01, a10, a11);
    Vector2D vector = new Vector2D(b1, b2);

    return new AffineTransform2D(matrix, vector);
  }

  /**
   * The writeToFile method write instructions to given file path.
   *
   * @param description of ChaosGame with transform, min and max values
   * @param path the file path for the file being written to
   */

  public void writeToFile(ChaosGameDescription description, String path) {
  try {
      File file = new File(path);
      BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
      if (file.createNewFile()) {
        logger.info("File created: " + file.getName());

        for (int i = 0; i < instructionForFile(description).size(); i ++) {
          buffer.write(instructionForFile(description).get(i));
          buffer.newLine();
        }
        buffer.close();
      } else {
        logger.info("File: " + file.getName() + " already exists.");
        for (int i = 0; i < instructionForFile(description).size(); i ++) {
          buffer.write(instructionForFile(description).get(i));
          buffer.newLine();
        }
        buffer.close();
      }
    } catch (Exception e) {
      logger.severe("An error occurred.");
      e.printStackTrace();
    }

  }

  /**
   * The instructionForFile method creates an ArrayList with instructions for writing a file.
   *
   * @param description of the ChaosGame transformation, minCoords and maxCoords.
   */
  public List<String> instructionForFile(ChaosGameDescription description) {
    List<String> instructions = new ArrayList<>();

    for (int i = 0; i < description.getTransforms().size(); i++) {
      Transform2D transform2D = description.getTransforms().get(i);

      String transformName = transform2D.getClass().getName();

      if (transformName.contains("JuliaTransform")) {
        instructions.add("Julia  # Type of transform");
        instructions.add(description.getMinCoords().toString() + " # Lower left");
        instructions.add(description.getMaxCoords().toString() + " # Upper right");

        instructions.add(transform2D.toString() + " # Real and imaginary parts of the constant c");

      }
      if (transformName.contains("AffineTransform2D")) {
        if (!instructions.contains("Affine2D # Type of transform")) {
          instructions.add("Affine2D # Type of transform");
          instructions.add(description.getMinCoords().toString() + " # Lower left");
          instructions.add(description.getMaxCoords().toString() + " # Upper right");
        }
        instructions.add(transform2D.toString() + " # " + (i + 1) + " transform (a00, a01, a10, a11, b0, b1)");

      }
    }
    return instructions;
  }
}


