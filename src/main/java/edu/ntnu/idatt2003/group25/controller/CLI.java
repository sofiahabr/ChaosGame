package edu.ntnu.idatt2003.group25.controller;

import edu.ntnu.idatt2003.group25.model.ChaosCanvas;
import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CLI {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner scanner = new Scanner(System.in);

    ChaosGameFileHandler fileHandler =
        new ChaosGameFileHandler(new ArrayList<>(), new Vector2D(0, 0), new Vector2D(1, 1));
    ChaosGameDescription description =
        fileHandler.readFromFile("src/main/resources/AffineTransform");
    ChaosCanvas canvas =
        new ChaosCanvas(100, 50, description.getMinCoords(), description.getMaxCoords());
    ChaosGame chaosGame = new ChaosGame(description, canvas.getWidth(), canvas.getHeight());
    chaosGame.runSteps(1000);

    while (true) {
      System.out.println("""
          -- Chaos Game Application --
          [1] read from file
          [2] write to file
          [3] run for x iterations
          [4] print fractal
                  
          Enter a number from 1 - 4
          """);

      int choice = 0;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }

      if (choice >= 1 && choice <= 4) {
        switch (choice) {
          case 1:
            System.out.println("""
                -- Choose file --
                [1] Affine Transform
                [2] Julia Transform
                              
                Enter a number from 1-2
                """);
            int choice1 = Integer.parseInt(scanner.nextLine());
            if (choice1 == 1) {
              description = fileHandler.readFromFile("src/main/resources/AffineTransform");
            }
            if (choice1 == 2) {
              description = fileHandler.readFromFile("src/main/resources/JuliaTransform");
              canvas =
                  new ChaosCanvas(100, 30, description.getMinCoords(), description.getMaxCoords());
              chaosGame = new ChaosGame(description, canvas.getWidth(), canvas.getHeight());
            } if(choice1 > 2 || choice1 < 1) {
              System.out.println(choice1 + "was not an option");
            }
            break;

          case 2:
            System.out.println("""
                -- Choose file --
                [1] Affine Transform
                [2] Julia Transform
                              
                Enter a number from 1-2
                """);

            // TODO: finish
            break;
          case 3:
            System.out.println("""
                Number of iterations: """);

            int iterations = Integer.parseInt(scanner.nextLine());

            chaosGame.runSteps(iterations);
            break;
          case 4:

            int[][] screen = chaosGame.getCanvas().getCanvasArray();

            for (int i = 0; i < screen.length; i++) {
              // Iterate over each column in the current row
              for (int j = 0; j < screen[i].length; j++) {
//        System.out.print(canvas[i][j] + ", ");
                // Print the value at the current position in the canvas array
                if (screen[i][j] == 1) {
                  System.out.print("X");
                } else {
                  System.out.print(" ");
                }
              }
              // Move to the next line after printing each row
              System.out.println();
            }
        }

      }
    }
  }

}
