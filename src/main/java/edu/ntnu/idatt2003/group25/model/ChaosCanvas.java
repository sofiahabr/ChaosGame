package edu.ntnu.idatt2003.group25.model;

import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;

/**
 * The ChaosCanvas create canvas/frame for the ChaosGame.
 */
public class ChaosCanvas {
  private int[][] canvas;
  private final int width;
  private final int height;
  private final Vector2D minCoords;
  private final Vector2D maxCoords;
  private AffineTransform2D transformCoordsToIndices;

  /**
   * The ChaosCanvas create a canvas/frame for the ChaosGame represented by an Array.
   * A canvas is set by parameters for "width" and "height" which determine the size of the canvas,
   * and parameter for "maxCoords" and "minCoords" determine scope of the grid on the canvas.
   *
   * @param width determine the canvas width by number of pixels
   * @param height determine the canvas height by number of pixels
   * @param minCoords determine the max coordinate for the grid
   * @param maxCoords determine the min coordinate for the grid
   */
  public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords) {
    this.width = width;
    this.height = height;
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    fillAffineTransform();
    clear();
  }


  public int getPixel(Vector2D point) {
    Vector2D coordsVector = transformCoordsToIndices.transform(point);
    int coordWidth = (int) coordsVector.getX0();
    int coordHeight = (int) coordsVector.getX1();


    if (coordWidth < width && coordWidth > 0 && coordHeight > 0
        && coordHeight < height) {
      return canvas[coordWidth][coordHeight];
    } else {
      return -1; // default value for out of bounds
    }
  }

  /**
   * putPixel is a method for assigning values on the canvas.
   * Each canvas value represents the color of a specific pixel on the canvas.
   * @param point is the (x,y) coordinate of the pixel being processed.
   *
   */
  public void putPixel(Vector2D point) {
    Vector2D thisPixel = transformCoordsToIndices.transform(point);
    int i = (int) thisPixel.getX0();
    int j = (int) thisPixel.getX1();

    if (i > 0 && i < height && j > 0 && j < width) {
      canvas[i][j] += 1;
    }
  }

  public int[][] getCanvasArray() {
    return canvas;
  }

  /**
   * fillAffineTransform() is a connective method for preparing the
   * transformCoordsToIndiece Transform.
   */
  private void fillAffineTransform() {
    Matrix2x2 A = new Matrix2x2(
        0, (height - 1) / (minCoords.getX1() - maxCoords.getX1()),
        (width - 1) / (maxCoords.getX0() - minCoords.getX0()), 0);

    Vector2D b = new Vector2D(((height - 1) * maxCoords.getX1()) / (maxCoords.getX1()
        - minCoords.getX1()), ((width - 1) * minCoords.getX0())
        / (minCoords.getX0() - maxCoords.getX0()));

    this.transformCoordsToIndices = new AffineTransform2D(A, b);
  }

  /**
   * The clear() method fills an empty canvas with zeros.
   */
  public void clear() {
    int[][] newCanvas = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newCanvas[i][j] = 0;
      }
    }
    this.canvas = newCanvas;
  }

  /**
   * Get method for the canvas height.
   *
   * @return the height of the canvas
   */

  public int getHeight() {
    return height;
  }

  /**
   * Get method for the canvas width.
   *
   * @return the width of the canvas
   */

  public int getWidth() {
    return width;
  }

  /**
   * Get method for maximal coordinate.
   *
   * @return the maximum value for coordinates on the grid.
   *
   */
  public Vector2D getMaxCoords() {
    return maxCoords;
  }

  /**
   * Get method for minimal coordinate.
   *
   * @return the minimal value for coordinates on the grid.
   *
   */

  public Vector2D getMinCoords() {
    return minCoords;
  }

  public void setHeight(int height) {
    this.height = height;
    fillAffineTransform();
  }
  public void setWidth(int width) {
    this.width = width;
    fillAffineTransform();
  }
}

