package problem1.model;

import java.util.Objects;

/**
 * The class ToDo consist of information of:
 *  string text-a description of the task to be done.
 *  boolean completed-indicates whether the task is completed or incomplete.
 *  string due - a due date.
 *  integer priority-an integer indicating the priority of the todo.
 *  string category-a user-specified String that can be used to group related todos.
 */
public class ToDo {

  private String text;
  private Boolean completed = Boolean.FALSE;
  private String due = null;
  private Integer priority = 3;
  private String category = null;

  /**
   * Instantiates a new To do.
   *
   * @param text the text
   */
  public ToDo(String text) {
    this.text = text;
  }

  /**
   * Gets text.
   *
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * Gets completed.
   *
   * @return the completed
   */
  public Boolean getCompleted() {
    return completed;
  }

  /**
   * Gets due.
   *
   * @return the due
   */
  public String getDue() {
    return due;
  }

  /**
   * Gets priority.
   *
   * @return the priority
   */
  public Integer getPriority() {
    return priority;
  }

  /**
   * Gets category.
   *
   * @return the category
   */
  public String getCategory() {
    return category;
  }

  /**
   * Sets completed.
   *
   * @param completed the completed
   */
  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  /**
   * Sets due.
   *
   * @param due the due
   */
  public void setDue(String due) {
    this.due = due;
  }

  /**
   * Sets priority.
   *
   * @param priority the priority
   */
  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  /**
   * Sets category.
   *
   * @param category the category
   */
  public void setCategory(String category) {
    this.category = category;
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
    ToDo toDo = (ToDo) o;
    return Objects.equals(text, toDo.text) && Objects.equals(completed,
        toDo.completed) && Objects.equals(due, toDo.due) && Objects.equals(
        priority, toDo.priority) && Objects.equals(category, toDo.category);
  }

  /**
   * Return the hashCode of this object.
   *
   * @return the hashCode of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(text, completed, due, priority, category);
  }

  /**
   * Return the string representation of the object.
   *
   * @return the string representation of the object.
   */
  @Override
  public String toString() {
    return "ToDo{" +
        "text='" + text + '\'' +
        ", completed=" + completed +
        ", due='" + due + '\'' +
        ", priority=" + priority +
        ", category='" + category + '\'' +
        '}';
  }
}
