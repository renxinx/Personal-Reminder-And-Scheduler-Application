/**
 We are using Singleton Pattern for this assignment, it`s when we designed the ToDoList Class.
 The reason why we chose this design pattern is that we only need one single instance of todo list,
 we do not need to recreate a new one each time we add a todo item,
 hence restrict the instantiation of the class to one "single" instance.
 */
package problem1.controller;

import problem1.controller.CmdLineExceptions.InvalidArgumentsException;
import problem1.controller.CmdLineExceptions.MissingCommandException;
import problem1.model.ToDoList;
import problem1.view.DisplayList;

/**
 * Class Main, driver code.
 */
public class Main {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    try {
      CmdLineParser parser = new CmdLineParser();
      parser.processArgs(args);
      parser.validateArgs();
      ToDoList todoList = ToDoList.getToDoList(parser.getCsvFilePath());

      if(parser.getHasAddTodo()){
        todoList.addToDo(parser.createToDo(), parser.getCsvFilePath());
      }

      if(parser.getHasCompletedTodo()){
        todoList.updateCompleted(parser.getCompletedToDoId(), parser.getCsvFilePath());
      }

      if (parser.getHasDisplay()) {
        DisplayList displaylist = new DisplayList();
        displaylist.display(todoList.getMap(), parser.getHasShowIncomplete(),
            parser.getHasShowCategory(), parser.getShowCategoryName(), parser.getHasSortByDate(),
            parser.getHasSortByPriority());
      }

    } catch (InvalidArgumentsException | MissingCommandException e) {
      System.out.println(e.getMessage());
    }
  }
}

