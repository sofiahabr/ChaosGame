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

    /**
     * Test the sqrt method returns correct for both X0 and X1 for negative complex values.
     */
    @Test
    void testSqrtNegativeValues() {
      Complex negativeTest = new Complex(-2,-2);
      assertEquals(0.6435942529055827, negativeTest.sqrt().getX0(),1e-9);
      assertEquals(-1.5537739740300374, negativeTest.sqrt().getX1(),1e-9);
    }

    /**
     * Test the sqrt method returns correct for both X0 and X1 for zero.
     */
    @Test
    void testSqrtZero() {
      Complex zeroTest = new Complex(0,0);
      assertEquals(0, zeroTest.sqrt().getX0());
      assertEquals(0, zeroTest.sqrt().getX1());
    }
  }
}
