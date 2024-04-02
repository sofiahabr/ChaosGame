package edu.ntnu.idatt2003.group25;

public class ChaosCanvas {
  private int[][] canvas;
  private int width;
  private int height;
  private Vector2D minCoords;
  private Vector2D maxCoords;
  private AffineTransform2D transformCoordsToIndices;

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
    }
    else {
      return -1; // default value for out of bounds
    }
  }
  public void putPixel(Vector2D point) {
    Vector2D thisPixel = transformCoordsToIndices.transform(point);
    int i = (int) thisPixel.getX0();
    int j = (int) thisPixel.getX1();

//    System.out.println("");
//    System.out.println("Punkt: " + point);
//    System.out.println("Pixel : " + i + ", " + j);

    if(i > 0 && i < height && j > 0 && j < width) {
      canvas[i][j] = 1;
    }
  }
  public int[][] getCanvasArray() {
    return canvas;
  }

  private void fillAffineTransform() {
    Matrix2x2 A = new Matrix2x2(
        0, (height-1)/ (minCoords.getX1() - maxCoords.getX1()),
        (width - 1)/(maxCoords.getX0() -minCoords.getX0()), 0);

    Vector2D b = new Vector2D(((height - 1)* maxCoords.getX1())/(maxCoords.getX1() -
        minCoords.getX1()), ((width - 1)* minCoords.getX0())/(minCoords.getX0()- maxCoords.getX0()));

    this.transformCoordsToIndices = new AffineTransform2D(A, b);
    // Setter A og b slik at AffineTransform -en kan brukes for å "oversette" punkter til pixler
  }

  public void clear() {
    int[][] newCanvas = new int[height][width];
    for (int i = 0; i < height; i++ ) {
      for( int j = 0; j < width; j++) {
        newCanvas[i][j] = 0;
      }
    }
    this.canvas = newCanvas;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public Vector2D getMaxCoords() {
    return maxCoords;
  }

  public Vector2D getMinCoords() {
    return minCoords;
  }
}

