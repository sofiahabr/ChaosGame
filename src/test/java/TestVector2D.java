import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.group25.Vector2D;
import org.junit.jupiter.api.Test;

public class TestVector2D {
  Vector2D testVector = new Vector2D(1, 2);

  @Test
  public void testGetX0(){
    assertEquals(1, testVector.getX0());
  }

  @Test
  public void testGetX1(){
    assertEquals(2, testVector.getX1());
  }

}
