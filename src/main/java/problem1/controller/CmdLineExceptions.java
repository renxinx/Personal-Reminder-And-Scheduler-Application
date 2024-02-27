package problem1.controller;

import java.util.Objects;

/**
 *  Class to handle all Command Line Arguments related exceptions.
 */
public class CmdLineExceptions extends RuntimeException {

  private static final String USAGE_HELP = "Usage:\n"
      + "--csv-file <path/to/file>  The CSV file containing the todos. This option is required. "
      + "--add-todo Add a new todo. If this option is \n"
      + "provided, then --todo-text must \n"
      + "also be provided."
      + "--todo-text <description of todo>  A description of the todo.  "
      + "--completed (Optional) Sets the completed \n"
      + "status of a new todo to true."
      + "--due <due date> (Optional) Sets the due date of a \n"
      + "new todo. You may choose how the \n"
      + "date should be formatted. "
      + "--priority <1, 2, or 3>  (Optional) Sets the priority of a \n"
      + "new todo. The value can be 1, 2, \n"
      + "or 3."
      + "--category <a category name> (Optional) Sets the category of a \n"
      + "new todo. The value can be any \n"
      + "String.  Categories do not need \n"
      + "to be pre-defined. "
      + "--complete-todo <id> Mark the Todo with the provided \n"
      + "ID as complete. "
      + "--display Display todos. If none of the \n"
      + "following optional arguments are \n"
      + "provided, displays all todos.  "
      + "--show-incomplete (Optional) If --display is \n"
      + "provided, only incomplete todos \n"
      + "should be displayed. "
      + "--show-category <category> (Optional) If --display is \n"
      + "provided, only todos with the \n"
      + "given category should be \n"
      + "displayed. "
      + "--sort-by-date (Optional) If --display is \n"
      + "provided, sort the list of todos \n"
      + "by date order (ascending). Cannot \n"
      + "be combined with --sort-by-\n"
      + "priority."
      + "--sort-by-priority (Optional) If --display is \n"
      + "provided, sort the list of todos \n"
      + "by priority (ascending). Cannot \n"
      + "be combined with --sort-by-date. \n"
      + "Examples:\n"
      + "--csv-file src/main/resources/my_todo.csv"
      + "--add-todo --todo-text run --due 2022-05-19 "
      + "--complete-todo 3 "
      + "--display --show-incomplete --sort-by-priority";

  /**
   * This constructor prints the error message on command line and exits successfully.
   *
   * @param msg Error message
   */
  public CmdLineExceptions(String msg) {
    super(msg + "\n" + USAGE_HELP);
  }


  /**
   * Class to handle errors related to invalid arguments passed in command line arguments.
   */
  public static class InvalidArgumentsException extends CmdLineExceptions {

    /**
     * This constructor passes the error message to its super class.
     *
     * @param msg Error message
     */
    public InvalidArgumentsException(String msg) {
      super(msg);
    }
  }

  /**
   * Class to handle errors related to missing command line arguments.
   */
  public static class MissingCommandException extends CmdLineExceptions {

    /**
     * This constructor passes the error message to its super class.
     *
     * @param msg Error message
     */
    public MissingCommandException(String msg) {
      super(msg);
    }
  }

  /**
   * Return true if Object o equals to the object.
   *
   * @param o the object to compare with the current object.
   * @return true if the field is same for both objects, otherwise, return false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return true;
  }

  /**
   * Return the hashCode of this object.
   *
   * @return the hashCode of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getClass().getName());
  }

  /**
   * Return the string representation of the object.
   *
   * @return the string representation of the object.
   */
  @Override
  public String toString() {
    return "CmdLineExceptions{}";
  }
}

