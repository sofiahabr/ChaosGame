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

  private String path = "src/test/java/TestAffine2D";
  private List<Transform2D> transform2DList = new ArrayList<>();
  private Vector2D vectorMin = new Vector2D(1,1);
  private Vector2D vectorMax = new Vector2D(10,10);
  private ChaosGameDescription description = new ChaosGameDescription(transform2DList,vectorMin,vectorMax);
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
      fileHandler.readFromFile(path);
      List<String> instruction = fileHandler.instructionForFile(description);
      assertEquals("Affine2D ", instruction.get(0));
    }
  }
}


