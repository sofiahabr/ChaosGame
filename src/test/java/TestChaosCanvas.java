import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2003.group25.ChaosCanvas;
import edu.ntnu.idatt2003.group25.Vector2D;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestChaosCanvas {

  ChaosCanvas test = new ChaosCanvas(100, 100, new Vector2D(0,0), new Vector2D(10,10));
  @Nested
  class testGetMethods {
    @Test
    void getPixel() {
      int pixelValue = test.getPixel(new Vector2D(5,5));

      assertEquals(0, pixelValue);
    }

    @Test
    void getCanvasArray() {
      assertEquals(100, test.getCanvasArray().length);
    }

    @Test
    void getHeight() {
      int height = test.getHeight();

      assertEquals(100, height);
    }

    @Test
    void getWidth() {
      int width = test.getWidth();

      assertEquals(100, width);
    }

    @Test
    void getMaxCoords() {
      Vector2D maxCoords = test.getMaxCoords();

      assertEquals(10, maxCoords.getX0());
      assertEquals(10, maxCoords.getX1());
    }

    @Test
    void getMinCoords() {
      Vector2D minCoords = test.getMinCoords();

      assertEquals(0, minCoords.getX0());
      assertEquals(0, minCoords.getX1());
    }

  }

  @Test
  void putPixel() {
    test.clear();
    test.putPixel(new Vector2D(3, 5));

    assertEquals(1, test.getPixel(new Vector2D(3, 5)));
    test.clear();
  }

  @Test
  void fillAffineTransform() {

  }

  @Test
  void clear() {
    test.putPixel(new Vector2D(3, 5));
    assertEquals(1, test.getPixel(new Vector2D(3, 5)));

    test.clear();
    assertEquals(0, test.getPixel(new Vector2D(3,5)));
  }
}