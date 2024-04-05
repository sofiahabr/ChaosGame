import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.group25.Complex;
import edu.ntnu.idatt2003.group25.JuliaTransform;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class TestJuliaTransform {
  @Nested
  class TransformTest {
    Complex z = new Complex(0.4, 0.2);
    Complex c = new Complex(0.3, 0.6);

    @Test
    void testTransform() {
      JuliaTransform test = new JuliaTransform(z, 1);
      assertEquals(0.506, test.transform(c).round().getX0());
      assertEquals(-0.395, test.transform(c).round().getX1());

    }
  }
}
