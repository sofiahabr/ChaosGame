import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.group25.Complex;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestComplex {
  @Nested
  class SqrtTest {
    Complex test = new Complex(2, 2);

    @Test
    void testSqrt() {
      assertEquals(1.5537739740300374, test.sqrt().getX0());
      assertEquals(0.6435942529055827, test.sqrt().getX1());
    }
  }
}
