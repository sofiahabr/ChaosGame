package edu.ntnu.idatt2003.group25;

/**
 * The type Affine transform 2 d.
 */
public class AffineTransform2D implements Transform2D {

  /**
   * The Matrix.
   */
  Matrix2x2 matrix;
  /**
   * The Vector.
   */
  Vector2D vector;

  /**
   * Instantiates a new Affine transform 2 d.
   *
   * @param matrix the matrix
   * @param vector the vector
   */
  public AffineTransform2D(Matrix2x2 matrix, Vector2D vector) {
    this.matrix = matrix;
    this.vector = vector;
  }

  public Matrix2x2 getMatrix() {
    return matrix;
  }

  public Vector2D getVector() {
    return vector;
  }

  /**
   * Affine transforms a vector.
   *
   * @param point is a Vector
   * @return the transformed vector.
   */
  @Override
  public Vector2D transform(Vector2D point) {
    return matrix.multiply(point).add(vector);
  }
}
