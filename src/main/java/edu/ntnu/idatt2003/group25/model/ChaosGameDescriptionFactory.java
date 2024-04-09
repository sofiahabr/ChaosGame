package edu.ntnu.idatt2003.group25.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ChaosGameDescriptionFactory {
  static Vector2D nullVector = new Vector2D(1,0);
  static ChaosGameFileHandler fileHandler = new ChaosGameFileHandler( new ArrayList<>(), nullVector, nullVector);

  public static ChaosGameDescription getSierpinskiTriangle() throws FileNotFoundException {
  return fileHandler.readFromFile("src/main/resources/Sierpinski");
}

  public static ChaosGameDescription getBarnsleyFern() throws FileNotFoundException {
  return fileHandler.readFromFile("src/main/resources/BarnsleyFern");
}

  public static ChaosGameDescription getJuliaTransform() throws FileNotFoundException {
  return fileHandler.readFromFile("src/main/resources/JuliaTransform");
}
}
