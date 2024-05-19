package edu.ntnu.idatt2003.group25.model;

/**
 * The Validation class verifies user input is valid datatype and value.
 */

public class Validation {

  /**
   * The verifyInt method validate int input or returns dummy values if user input is invalid.
   *
   * @param input The given input from user.
   * @param dummy Provided default value which is return if input is not valid.
   * @return The input parsed to int or dummy value.
   */
  public static Integer verifyInt(String input, int dummy) {
    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException e) {
      return dummy;
    }
  }

  /**
   * The method verifies user input is a positive int.
   *
   * @param input The given input from user.
   * @param dummy Provided default value which is returned if input is invalid.
   * @return The input parsed to int or the given dummy value.
   */
  public static Integer verifyPositiveInt(String input, int dummy) {
    int value = verifyInt(input, dummy);
    if (value >= 0) {
      return value;
    } else {
      return dummy;
    }
  }
  /**
   * The verifyDouble method validate double input or returns dummy values if the input is invalid.
   *
   * @param input The given input from user.
   * @param dummy Provided default which is return if input is not valid.
   *
   * @return The input parsed to double or dummy value.
   */

  public static double verifyDouble(String input, double dummy) {
    try {
      return Double.parseDouble(input);
    } catch (NumberFormatException e) {
      return dummy;
    }
  }

  /**
   * The verifyString method validate user input strings are not empty.
   *
   * @param input The input String given by user
   * @return the string if valid input, otherwise null.
   */
  public static String verifyString(String input) {
    if (!input.isEmpty() && !input.equals(" ")) {
      return input;
    } else {
      return null;
    }
  }
}
