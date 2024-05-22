import static edu.ntnu.idatt2003.group25.model.Validation.verifyDouble;
import static edu.ntnu.idatt2003.group25.model.Validation.verifyInt;
import static edu.ntnu.idatt2003.group25.model.Validation.verifyPositiveInt;
import static edu.ntnu.idatt2003.group25.model.Validation.verifyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestValidation {

  /**
   * The dummy value.
   */
  private int dummy;
  /**
   * A dummy value not null.
   */
  private int dummyNotNull;
  /**
   * The int input.
   */
  private String intInput;
  /**
   * The string input.
   */
  private String stringInput;

  /**
   * Sets up before each test
   */
  @BeforeEach
      public void setUp() {
       dummy = 0;
       intInput = "100";
       stringInput = "hello";
       dummyNotNull = 10;
  }

  /**
   * Nested class for testing the verifyInt method of Validation.
   */
  @Nested
  class testVerifyInt {

    /**
     * Test verify int returns correct.
     */
    @Test
    void testVerifyIntReturnsCorrect() {
      assertEquals(100,verifyInt(intInput,dummy));
    }
    /**
     * Test verify int returns dummy.
     */
    @Test
    void testVerifyIntReturnsDummy() {
      assertEquals(0,verifyInt(stringInput,dummy));
      assertEquals(0,verifyInt(null,dummy));
    }
  }
  /**
   * Nested class for testing the verifyPositiveInt method of Validation.
   */
  @Nested
  class testVerifyPositiveInt {
    /**
     * Test verify positive int (VPI) returns correct.
     */
    @Test
    void testVPIReturnsCorrect() {
      assertEquals(100,verifyPositiveInt(intInput,dummy));
      assertEquals(0,verifyPositiveInt("0",dummyNotNull));
    }

    /**
     * Test VPI returns dummy value.
     */
    @Test
    void testVPIReturnsDummyValue() {
      assertEquals(dummy,verifyPositiveInt("-19",dummy));
    }
  }
  /**
   * Nested class for testing the verifyDouble method of Validation.
   */
  @Nested
  class testVerifyDouble {

    /**
     * Test verify double return correct.
     */
    @Test
    void testVerifyDoubleReturnCorrect() {
      Object result = verifyDouble(intInput,dummy);
      assertEquals(100.00,result);
      assertInstanceOf(Double.class, result);
    }
    /**
     * Test verify double return dummy.
     */
    @Test
    void testVerifyDoubleReturnDummy() {
      assertEquals(dummy,verifyDouble(stringInput,dummy));
      assertEquals(dummyNotNull,verifyDouble(stringInput,dummyNotNull));
    }
  }
  /**
   * Nested class for testing the verifyString method of Validation.
   */
  @Nested
  class testVerifyString {
    /**
     * Test verify string returns correct.
     */
    @Test
    void testVerifyStringReturnsCorrect() {
      assertEquals("hello",verifyString(stringInput));
      assertEquals("100",verifyString(intInput));
    }
    /**
     * Test verify string returns null rather than empty string.
     */
    @Test
    void testVerifyStringDoesntReturnEmpty() {
      assertNull(verifyString(""));
      assertNull(verifyString(" "));
    }
  }
}
