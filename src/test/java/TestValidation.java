import static edu.ntnu.idatt2003.group25.model.Validation.verifyDouble;
import static edu.ntnu.idatt2003.group25.model.Validation.verifyInt;
import static edu.ntnu.idatt2003.group25.model.Validation.verifyPositiveInt;
import static edu.ntnu.idatt2003.group25.model.Validation.verifyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestValidation {
  private int dummy;
  private int dummyNotNull;
  private String intInput;
  private String stringInput;

  @BeforeEach
      public void setUp() {
       dummy = 0;
       intInput = "100";
       stringInput = "hello";
       dummyNotNull = 10;
  }


  @Nested
  class testVerifyInt {

    @Test
    void testVerifyIntReturnsCorrect() {
      assertEquals(100,verifyInt(intInput,dummy));
    }
    @Test
    void testVerifyIntReturnsDummy() {
      assertEquals(0,verifyInt(stringInput,dummy));
      assertEquals(0,verifyInt(null,dummy));
    }
  }
  @Nested
  class testVerifyPositiveInt {
    @Test
    void testVPIReturnsCorrect() {
      assertEquals(100,verifyPositiveInt(intInput,dummy));
      assertEquals(0,verifyPositiveInt("0",dummyNotNull));
    }
    @Test
    void testVPIReturnsDummyValue() {
      assertEquals(dummy,verifyPositiveInt("-19",dummy));
    }
  }
  @Nested
  class testVerifyDouble {

    @Test
    void testVerifyDoubleReturnCorrect() {
      Object result = verifyDouble(intInput,dummy);
      assertEquals(100.00,result);
      assertTrue(result instanceof Double);
    }
    @Test
    void testVerifyDoubleReturnDummy() {
      assertEquals(dummy,verifyDouble(stringInput,dummy));
      assertEquals(dummyNotNull,verifyDouble(stringInput,dummyNotNull));
    }
  }
  @Nested
  class testVerifyString {
    @Test
    void testVerifyStringReturnsCorrect() {
      assertEquals("hello",verifyString(stringInput));
      assertEquals("100",verifyString(intInput));
    }
    @Test
    void testVerifyStringDoesntReturnEmpty() {
      assertEquals(null,verifyString(""));
      assertEquals(null,verifyString(" "));
    }
  }
}
