import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the ChaosGame class.

 */
class TestChaosGame {

  /**
   * Test ChaosGameFileHandler object.

   */
  ChaosGameFileHandler fileHandler = new ChaosGameFileHandler(new ArrayList<>(), new Vector2D(0,0), new Vector2D(1,1));
  /**
   * Test ChaosGameDescription object defined from path.

   */
  ChaosGameDescription description =
      fileHandler.readFromFile("src/main/resources/AffineTransform");
  /**
   * Test ChaosGame object.


   */
  ChaosGame test = new ChaosGame(description, 110, 40);

  /**
   * Constructor for the test class.
   *
   * @throws FileNotFoundException if file for affine path is invalid.
   */
  TestChaosGame() throws FileNotFoundException {
  }

  /**
   * Test the getCanvas method returns correct values.
   */
  @Test
  void getCanvas() {
    assertEquals(40, test.getCanvas().getCanvasArray().length);
    assertEquals(110, test.getCanvas().getCanvasArray()[0].length);
  }

  /**
   * Test the runSteps method returns correct values.
   */
  @Test
  void runSteps() {
    test.runSteps(100000);

    int[][] canvas = test.getCanvas().getCanvasArray();

    for (int i = 0; i < canvas.length; i++) {
      // Iterate over each column in the current row
      for (int j = 0; j < canvas[i].length; j++) {
//        System.out.print(canvas[i][j] + ", ");
        // Print the value at the current position in the canvas array
        if(canvas[i][j] == 1){
          System.out.print("X");
        }
        else {
          System.out.print(" ");
        }
      }
      // Move to the next line after printing each row
      System.out.println();
    }
  }
}