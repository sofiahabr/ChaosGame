package edu.ntnu.idatt2003.group25.model;

import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import java.util.ArrayList;
import java.util.List;

/**
 * The ChaosGameDescriptionFactory creates ChaosGameDescriptions for four different fractals.
 * The factory creates descriptions for Sierpinski-, Barnsley-Fern- and Julia fractals.
 */
public class ChaosGameDescriptionFactory {

  /**
   * Creates a ChaosGameDescription for the Sierpinski fractal.
   *
   * @return ChaosGameDescription object with values for the Sierpinski fractal.
   */

  public static ChaosGameDescription createSierpinski() {
    List<Transform2D> transformations = new ArrayList<>();
    transformations.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0, 0)));
    transformations.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0.25, 0.5)));
    transformations.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0.5, 0)));
    return new ChaosGameDescription(transformations, new Vector2D(0, 0), new Vector2D(1, 1));
  }

  /**
   * Creates a ChaosGameDescription for the Barnsley Fern fractal.
   *
   * @return ChaosGameDescription object with values for the Barnsley Fern fractal.
   */
  public static ChaosGameDescription createBarnsleyFern() {
    List<Transform2D> transformations = new ArrayList<>();
    transformations.add(new AffineTransform2D(new Matrix2x2(0, 0, 0, 0.16),
        new Vector2D(0, 0)));
    transformations.add(new AffineTransform2D(new Matrix2x2(0.85, 0.04, -0.04, 0.85),
        new Vector2D(0, 1.6)));
    transformations.add(new AffineTransform2D(new Matrix2x2(0.2, -0.26, 0.23, 0.22),
        new Vector2D(0, 1.6)));
    transformations.add(new AffineTransform2D(new Matrix2x2(-0.15, 0.28, 0.26, 0.24),
        new Vector2D(0, 0.44)));
    return new ChaosGameDescription(transformations,
        new Vector2D(-2.64, 0), new Vector2D(2.65, 10));
  }

  /**
   * Creates a ChaosGameDescription for the Julia fractal.
   *
   * @param c Complex number used in the Julia fractal.
   * @return ChaosGameDescription object with values for the Julia fractal.
   */

  public static ChaosGameDescription createJuliaTransformation(Complex c) {
    List<Transform2D> transformation = new ArrayList<>();
    transformation.add(new JuliaTransform(c, 1));
    transformation.add(new JuliaTransform(c, -1));
    return new ChaosGameDescription(transformation, new Vector2D(-1.6, -1), new Vector2D(1.6, 1));
  }

  /**
   * Creates a ChaosGameDescription for the Levy Dragon fractal.
   *
   * @return ChaosGameDescription object with values for the Levy Dragon fractal.
   */
  public static ChaosGameDescription createLevyDragon() {
    List<Transform2D> transformations = new ArrayList<>();
    transformations.add(new AffineTransform2D(new Matrix2x2(0.5, -0.5, 0.5, 0.5),
        new Vector2D(0, 0)));
    transformations.add(new AffineTransform2D(new Matrix2x2(0.5, 0.5, -0.5, 0.5),
        new Vector2D(0.5, 0.5)));

    return new ChaosGameDescription(transformations,
        new Vector2D(-0.5, -0.5), new Vector2D(1.5, 1));
  }

  /**
   * Creates a ChaosGameDescription for the Heighway Dragon fractal.
   *
   * @return ChaosGameDescription object with values for the Heighway Dragon fractal.
   */
  public static ChaosGameDescription createHeighwayDragon() {
    List<Transform2D> transformations = new ArrayList<>();
    transformations.add(new AffineTransform2D(new Matrix2x2(0.5, -0.5, 0.5, 0.5),
        new Vector2D(0, 0)));
    transformations.add(new AffineTransform2D(new Matrix2x2(-0.5, -0.5, 0.5, -0.5),
        new Vector2D(1, 0)));

    return new ChaosGameDescription(transformations,
        new Vector2D(-0.5, -0.5), new Vector2D(1.25, 0.75));
  }

  /**
   * Creates an empty ChaosGameDescription.
   *
   * @return ChaosGameDescription object with empty values.
   */
  public static ChaosGameDescription createEmpty() {
    return new ChaosGameDescription(new ArrayList<>(),
        new Vector2D(0, 0), new Vector2D(0, 0));
  }
}
