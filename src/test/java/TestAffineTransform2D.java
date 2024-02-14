import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.group25.AffineTransform2D;
import edu.ntnu.idatt2003.group25.Matrix2x2;
import edu.ntnu.idatt2003.group25.Vector2D;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test the transformation method in AffineTransform2D class return correct
 */
public class TestAffineTransform2D {

  @Nested
  class TransformationTest {
    Matrix2x2 A = new Matrix2x2(0.5,1,1,0.5);
    Vector2D b = new Vector2D(3,1);
    Vector2D x = new Vector2D(1, 2);

    AffineTransform2D test = new AffineTransform2D(A,b);

    @Test
    void transformationTest() {
      assertEquals(5.5,test.transform(x).getX0());
      assertEquals(3,test.transform(x).getX1());
    }
  }

}
