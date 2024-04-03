package edu.ntnu.idatt2003.group25;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class TestChaosGame {
  ChaosGameFileHandler fileHandler = new ChaosGameFileHandler(new ArrayList<>(), new Vector2D(0,0), new Vector2D(1,1));
  ChaosGameDescription description = fileHandler.readFromFile("src/main/resources/AffineTransform");
  ChaosGame test = new ChaosGame(description, 110, 40);

  TestChaosGame() throws FileNotFoundException {
  }

  @Test
  void getCanvas() {
    assertEquals(40, test.getCanvas().getCanvasArray().length);
    assertEquals(110, test.getCanvas().getCanvasArray()[0].length);
  }

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