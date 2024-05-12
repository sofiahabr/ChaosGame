import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.transforms.JuliaTransform;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test for the get methods for ChaosGameDescription class.
 */
public class TestChaosGameDescription {

  @Nested
  class getMethodsTest {
    Vector2D vector1 = new Vector2D(0,1);
    Vector2D vector2 = new Vector2D(2,2);
    List<Transform2D> transform2DList = new ArrayList<Transform2D>();

    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(transform2DList,vector1,vector2);

    @Test
    void testGetTransforms() {
      assertEquals(0, transform2DList.size());
      transform2DList.add(new JuliaTransform(new Complex(2,1),2));
      assertEquals(1, transform2DList.size());
    }

    @Test
    void testGetMaxCoords() {
      assertEquals(vector2,chaosGameDescription.getMaxCoords());
    }
    @Test
    void testGetMinCoords() {
      assertEquals(vector1, chaosGameDescription.getMinCoords());
    }

  }

}
