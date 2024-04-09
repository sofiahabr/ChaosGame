import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

public class testCGDescriptionFactory {


  @Test
  void testGetSierpinskiTriangleReturnsCorrectFractal() throws FileNotFoundException {
    assertTrue(new Vector2D(0.0,0.0).isEqual(ChaosGameDescriptionFactory.getSierpinskiTriangle().getMinCoords()));
    System.out.println(ChaosGameDescriptionFactory.getSierpinskiTriangle().getTransforms().toString());
    assertEquals(3, ChaosGameDescriptionFactory.getSierpinskiTriangle().getTransforms().size());
  }

}
