import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.group25.model.transforms.AffineTransform2D;
import edu.ntnu.idatt2003.group25.model.Matrix2x2;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import org.junit.jupiter.api.Test;

/**
 * Test the transformation method in AffineTransform2D class return correct
 */
class TestAffineTransform2D {

  /**
   * Matrix object for testing.
   */
    Matrix2x2 A = new Matrix2x2(0.5,1,1,0.5);
  /**
   * Vector object for testing.
   */
  Vector2D b = new Vector2D(3,1);
  /**
   * Vector object for testing.
   */
    Vector2D x = new Vector2D(1, 2);
  /**
   * AffineTransform2D object for testing.
   */
    AffineTransform2D test = new AffineTransform2D(A,b);

  /**
   * Test the transformation method returns correct for all values
   */
    @Test
    void transformationTest() {
      assertEquals(5.5,test.transform(x).getX0());
      assertEquals(3,test.transform(x).getX1());
    }
}
