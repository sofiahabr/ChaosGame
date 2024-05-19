import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2003.group25.model.Vector2D;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the Vector2D class.
 */
public class TestVector2D {
  /**
   * Nested class for testing the get methods of Vector2D.
   */
  @Nested
  class GetTest {
    Vector2D testVector = new Vector2D(1, 2);

    /**
     * Test for getX0 method.
     */
    @Test
    public void testGetX0ReturnCorrect() {
      assertEquals(1, testVector.getX0());
    }
    /**
     * Test for getX1 method.
     */
    @Test
    public void testGetX1ReturnCorrect() {
      assertEquals(2, testVector.getX1());
    }
  }
  /**
   * Nested class for testing the set methods of Vector2D.
   */
  @Nested
  class SetTest {
    Vector2D testVector = new Vector2D(0, 0);
    /**
     * Test for setX0 method.
     */
    @Test
    public void testSetX0ReturnCorrect() {
      testVector.setX0(1);
      assertEquals(1, testVector.getX0());
    }
    /**
     * Test for setX1 method.
     */
    @Test
    public void testSetX1ReturnCorrect() {
      testVector.setX1(2);
      assertEquals(2, testVector.getX1());
    }
  }

  /**
   * Nested class for testing the add method of Vector2D.
   */
  @Nested
  class AddTest {
    Vector2D testVector = new Vector2D(1, 2);
    Vector2D testVector2 = new Vector2D(4, 1);

    /**
     * Test for add method.
     */
    @Test
    void testAdd(){
      assertEquals(5.0, testVector.add(testVector2).getX0());
      assertEquals(3.0, testVector.add(testVector2).getX1());
    }
  }

  /**
   * Nested class for testing the subtract method of Vector2D.
   */
  @Nested
  class SubtractTest {
    Vector2D testVector = new Vector2D(1, 2);
    Vector2D testVector2 = new Vector2D(4, 1);

    /**
     * Test for subtract method.
     */
    @Test
    public void testSubtract(){
      assertEquals(-3.0, testVector.subtract(testVector2).getX0());
      assertEquals(1.0, testVector.subtract(testVector2).getX1());
    }
  }

  /**
   * Nested class for testing of the multiply method in Vector2D.
   */
  @Nested
  class MultiplyTest {
    Vector2D testVector = new Vector2D(1, 2);

    /**
     * Test for multiply method
     */
    @Test
    void testMultiply(){
      int scale = 2;
      assertEquals(2.0, testVector.scale(scale).getX0());
      assertEquals(4.0, testVector.scale(scale).getX1());
    }
    @Test
    void testEqualsReturnsTrue() {
      Vector2D vectorOriginal = new Vector2D(1,2);
      Vector2D duplicateVector = new Vector2D(1,2);
      assertTrue(vectorOriginal.isEqual(duplicateVector));
    }

    @Test
    void testEqualsReturnFalse() {
      Vector2D vectorOriginal = new Vector2D(1,2);
      Vector2D notDuplicateVector = new Vector2D(0,3);
      assertFalse(vectorOriginal.isEqual(notDuplicateVector));
    }
  }
}