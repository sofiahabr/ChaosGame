import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.group25.model.Complex;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the Complex class.

 */
public class TestComplex {

  /**
   * Nested class for testing the add method of Complex.
   */
  @Nested
  class SqrtTest {
    Complex test = new Complex(2, 2);
    /**
     * Test the sqrt method returns correct for both X0 and X1
     */
    @Test
    void testSqrt() {
      assertEquals(1.5537739740300374, test.sqrt().getX0());
      assertEquals(0.6435942529055827, test.sqrt().getX1());
    }
  }
}
