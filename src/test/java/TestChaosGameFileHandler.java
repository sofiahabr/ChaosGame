import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.transforms.Transform2D;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestChaosGameFileHandler {

  private final String path = "src/test/java/TestAffine2D";
  private final List<Transform2D> transform2DList = new ArrayList<>();
  private final Vector2D vectorMin = new Vector2D(1,1);
  private final Vector2D vectorMax = new Vector2D(10,10);
  private final ChaosGameDescription description = new ChaosGameDescription(transform2DList,vectorMin,vectorMax);
  ChaosGameFileHandler fileHandler = new ChaosGameFileHandler(transform2DList,vectorMin,vectorMax);

  @Test
  void testReadFromFileAddTransformation() {
    try {
      assertEquals(3, fileHandler.readFromFile(path).getTransforms().size());
      assertTrue(new Vector2D(0.0,0.0).isEqual(fileHandler.readFromFile(path).getMinCoords()));

      assertTrue(new Vector2D(1.0,1.0).isEqual(fileHandler.readFromFile(path).getMaxCoords()));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  @Test
  void testReadFromFileThrowsExceptions() throws FileNotFoundException {
    String invalidFilePath = "invalid file path";
    assertThrows(FileNotFoundException.class, () -> {fileHandler.readFromFile( invalidFilePath);});
    }
  @Nested
  class testInstructionsForFile {
    @Test
    void testAffine2DValuesAreInArray() throws FileNotFoundException {
      ChaosGameDescription readDescription = fileHandler.readFromFile(path);
      List<String> instruction = fileHandler.instructionForFile(readDescription);
      System.out.println("Instructions: "+ instruction);
      assertEquals("Affine2D # Type of transform", instruction.get(0));
      assertEquals("0.0, 0.0 # Lower left", instruction.get(1));
      assertEquals("1.0, 1.0 # Upper right", instruction.get(2));
      assertEquals("0.5, 0.0, 0.0, 0.5 , 0.0, 0.0 # 1 transform (a00, a01, a10, a11, b0, b1)",
          instruction.get(3));
    }
  }
}


