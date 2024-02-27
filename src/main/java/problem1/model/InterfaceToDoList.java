package problem1.model;

import java.util.List;

/**
 * The interface to do list.
 */
public interface InterfaceToDoList {

  /**
   * Add to do.
   *
   * @param toDo the to do
   * @param path the path
   */
  void addToDo(ToDo toDo, String path);

  /**
   * Update completed.
   *
   * @param idList the id list
   * @param path   the path
   */
  void updateCompleted(List<Integer> idList, String path);
}
