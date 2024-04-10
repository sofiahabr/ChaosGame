package edu.ntnu.idatt2003.group25.model;

import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.Soundbank;

/**
 * The ChaosGameDescriptionFactory creates ChaosGameDescriptions for four different fractals.
 * The factory creates descriptions for Sierpinski-, Barnsley-Fern-, Affine and Julia fractals.
 */
public class ChaosGameDescriptionFactory {

  static ChaosGameDescription createSierpinski() {
    List<Transform2D> transformations = new ArrayList<>();
    transformations.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0, 0)));
    transformations.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0.25, 0.5)));
    transformations.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0.5, 0)));
    return new ChaosGameDescription(transformations, new Vector2D(0, 0), new Vector2D(1, 1));
  }

  static ChaosGameDescription createBarnsleyFern() {
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

  static ChaosGameDescription createAffineTransformation() {
    List<Transform2D> transformation = new ArrayList<>();
    transformation.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0, 0)));
    transformation.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0.25, 0.5)));
    transformation.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0.5, 0)));
    return new ChaosGameDescription(transformation, new Vector2D(0, 0), new Vector2D(1, 1));
  }

  //TODO: Consider if Julia should have 1 or 2 transformations
  static ChaosGameDescription createJuliaTransformation(Complex c) {
    List<Transform2D> transformation = new ArrayList<>();
    transformation.add(new JuliaTransform(c, 1));
    transformation.add(new JuliaTransform(c,-1));
    return new ChaosGameDescription(transformation, new Vector2D(-1.6, -1), new Vector2D(1.6, 1));
  }
}
