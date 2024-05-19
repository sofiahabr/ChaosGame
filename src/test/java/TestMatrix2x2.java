import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.group25.model.Matrix2x2;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestMatrix2x2 {

  @Nested
  class GetTest {
    Matrix2x2 test = new Matrix2x2(2.0,3.0,4.0,5.0);

    @Test
    void testGetAReturnCorrect() {
      assertEquals(2.0,test.getA00());
      assertEquals(3.0,test.getA01());
      assertEquals(4.0,test.getA10());
      assertEquals(5.0,test.getA11());
    }
  }
  @Nested
  class SetTest {
    Matrix2x2 test = new Matrix2x2(0,0,0,0);
    @Test
    void testThatSetAValueReturnCorrect() {
      test.setA00(1);
      test.setA01(2);
      test.setA10(3);
      test.setA11(4);
      assertEquals(1,test.getA00());
      assertEquals(2,test.getA01());
      assertEquals(3,test.getA10());
      assertEquals(4,test.getA11());
    }
  }
  @Nested
  class MultiplyTest {
    Vector2D vector2D = new Vector2D(1,2);
    Matrix2x2 matrix = new Matrix2x2(0.5,1,1,0.5);
    Vector2D solutionVector = new Vector2D(2.5,2);
    @Test
    void testThatMultiplyReturnsX0Correct() {
      assertEquals(solutionVector.getX0(),matrix.multiply(vector2D).getX0());
    }
    @Test
    void testThatMultiplyReturnX1correct() {
      assertEquals(solutionVector.getX1(),matrix.multiply(vector2D).getX1());
    }
  }


}
