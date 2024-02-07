import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.group25.Vector2D;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestVector2D {
  @Nested
  class GetTest {
    Vector2D testVector = new Vector2D(1, 2);

    @Test
    public void testGetXReturnCorrect() {
      assertEquals(1, testVector.getX0());
      assertEquals(2, testVector.getX1());
    }
  }

  @Nested
  class SetTest {
    Vector2D testVector = new Vector2D(0, 0);

    @Test
    public void testSetXReturnCorrect() {
      testVector.setX0(1);
      testVector.setX1(2);
      assertEquals(1, testVector.getX0());
      assertEquals(2, testVector.getX1());
    }
  }
  @Nested
  class AddTest {
    Vector2D testVector = new Vector2D(1, 2);
    Vector2D testVector2 = new Vector2D(4, 1);

    @Test
    public void testAdd(){
      assertEquals(5.0, testVector.add(testVector2).getX0());
      assertEquals(3.0, testVector.add(testVector2).getX1());
    }
  }
  @Nested
  class SubtractTest {
    Vector2D testVector = new Vector2D(1, 2);
    Vector2D testVector2 = new Vector2D(4, 1);

    @Test
    public void testSubtract(){
      assertEquals(-3.0, testVector.subtract(testVector2).getX0());
      assertEquals(1.0, testVector.subtract(testVector2).getX1());
    }
  }
  @Nested
  class MultiplyTest {

  }
}
