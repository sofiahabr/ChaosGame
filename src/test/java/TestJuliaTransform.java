import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the JuliaTransform class.

 */
public class TestJuliaTransform {

  /**
   * Nested class for testing the transform method of JuliaTransform.
   */
  @Nested
  class TransformTest {
    /**
     * Test complex numbers called z and c.
     */
    Complex z = new Complex(0.4, 0.2);
    Complex c = new Complex(0.3, 0.6);

    /**
     * Test the transform method returns correct for all both X0 and X1
     */
    @Test
    void testTransform() {
      JuliaTransform test = new JuliaTransform(z, 1);
      assertEquals(0.395, test.transform(c).round().getX0());
      assertEquals(0.506, test.transform(c).round().getX1());

    }
  }
}
