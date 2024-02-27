package problem1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import problem1.model.ToDo;
import problem1.model.ToDoList;

/**
 * Processes and validates command line arguments. This class doesn't do anything with the values
 * provided by the user beyond initial validation. It is another class's responsibility to determine
 * what to do with the user input.
 */
public class CmdLineParser {

  private static final String CSV_FILE = "--csv-file";
  private static final String ADD_TODO = "--add-todo";
  private static final String TODO_TEXT = "--todo-text";
  private static final String COMPLETED = "--completed";
  private static final String DUE = "--due";
  private static final String PRIORITY = "--priority";
  private static final String CATEGORY = "--category";
  private static final String COMPLETE_TODO = "--complete-todo";
  private static final String DISPLAY = "--display";
  private static final String SHOW_INCOMPLETE = "--show-incomplete";
  private static final String SHOW_CATEGORY = "--show-category";
  private static final String SORT_BY_DATE = "--sort-by-date";
  private static final String SORT_BY_PRIORITY = "--sort-by-priority";
  private static final String CSV = ".csv";
  private static final String OPTION = "--";

  private Boolean hasCsvFile = Boolean.FALSE;
  private Boolean hasCsvFilePath = Boolean.FALSE;
  private String csvFilePath;
  private Boolean hasAddTodo = Boolean.FALSE;
  private Boolean hasToDoText = Boolean.FALSE;
  private Boolean hasToDoDescription = Boolean.FALSE;
  private String todoDescription = "";
  private Boolean hasCompleted = Boolean.FALSE;
  private Boolean hasDue = Boolean.FALSE;
  private Boolean hasDueDate = Boolean.FALSE;
  private String dueDate;
  private Boolean hasPriority = Boolean.FALSE;
  private Boolean hasPriorityNum = Boolean.FALSE;
  private String priorityNum;
  private Boolean hasCategory = Boolean.FALSE;
  private Boolean hasCategoryName = Boolean.FALSE;
  private String category = "";
  private Boolean hasCompletedTodo = Boolean.FALSE;
  private Boolean hasCompletedToDoId = Boolean.FALSE;
  private List<Integer> completedToDoId = new ArrayList<>();
  private Boolean hasDisplay = Boolean.FALSE;
  private Boolean hasShowIncomplete = Boolean.FALSE;
  private Boolean hasShowCategory = Boolean.FALSE;
  private Boolean hasShowCategoryName = Boolean.FALSE;
  private String showCategoryName = "";
  private Boolean hasSortByDate = Boolean.FALSE;
  private Boolean hasSortByPriority = Boolean.FALSE;

  /**
   * Instantiates a new Cmd line parser.
   */
  public CmdLineParser() {
  }

  /**
   * Process args.
   *
   * @param args the args
   */
  public void processArgs(String[] args) {
    for (int i = 0; i < args.length; i++) {
      if (Objects.equals(args[i], CSV_FILE)) {
        hasCsvFile = Boolean.TRUE;
        if (i < args.length - 1 && this.validatePath(args[i + 1])) {
          hasCsvFilePath = Boolean.TRUE;
          i++;
        }
      } else if (Objects.equals(args[i], ADD_TODO)) {
        hasAddTodo = Boolean.TRUE;
      } else if (Objects.equals(args[i], TODO_TEXT)) {
        hasToDoText = Boolean.TRUE;
        if (i < args.length - 1 && !args[i + 1].startsWith(OPTION)) {
          hasToDoDescription = Boolean.TRUE;
          int j = i + 1;
          while (j < args.length && !args[j].startsWith(OPTION)) {
            todoDescription += args[j];
            todoDescription += " ";
            i++;
            j++;
          }
          todoDescription = todoDescription.trim();
        }
      } else if (Objects.equals(args[i], COMPLETED)) {
        hasCompleted = Boolean.TRUE;
      } else if (Objects.equals(args[i], DUE)) {
        hasDue = Boolean.TRUE;
        if (i < args.length - 1 && this.validateDueDate(args[i + 1])) {
          hasDueDate = Boolean.TRUE;
          dueDate = args[i + 1];
          i++;
        }
      } else if (Objects.equals(args[i], PRIORITY)) {
        hasPriority = Boolean.TRUE;
        if (i < args.length - 1 && this.validatePriorityNum(args[i + 1])) {
          hasPriorityNum = Boolean.TRUE;
          priorityNum = args[i + 1];
          i++;
        }
      } else if (Objects.equals(args[i], CATEGORY)) {
        hasCategory = Boolean.TRUE;
        if (i < args.length - 1 && !args[i + 1].startsWith(OPTION)) {
          hasCategoryName = Boolean.TRUE;
          int j = i + 1;
          while (j < args.length && !args[j].startsWith(OPTION)) {
            category += args[j];
            category += " ";
            i++;
            j++;
          }
          category = category.trim();
        }
      } else if (Objects.equals(args[i], COMPLETE_TODO)) {
        hasCompletedTodo = Boolean.TRUE;
        if (i < args.length - 1 && this.validateId(args[i + 1])) {
          hasCompletedToDoId = Boolean.TRUE;
          completedToDoId.add(Integer.parseInt(args[i + 1]));
          i++;
        }
      } else if (Objects.equals(args[i], DISPLAY)) {
        hasDisplay = Boolean.TRUE;
      } else if (Objects.equals(args[i], SHOW_INCOMPLETE)) {
        hasShowIncomplete = Boolean.TRUE;
      } else if (Objects.equals(args[i], SHOW_CATEGORY)) {
        hasShowCategory = Boolean.TRUE;
        if (i < args.length - 1 && !args[i + 1].startsWith(OPTION)) {
          hasShowCategoryName = Boolean.TRUE;
          int j = i + 1;
          while (j < args.length && !args[j].startsWith(OPTION)) {
            showCategoryName += args[j];
            showCategoryName += " ";
            i++;
            j++;
          }
          showCategoryName = showCategoryName.trim();
        }
      } else if (Objects.equals(args[i], SORT_BY_DATE)) {
        hasSortByDate = Boolean.TRUE;
      } else if (Objects.equals(args[i], SORT_BY_PRIORITY)) {
        hasSortByPriority = Boolean.TRUE;
      } else {
        throw new CmdLineExceptions.InvalidArgumentsException("Unknown option: " + args[i]);
      }
    }
  }

  /**
   * Validate path.
   *
   * @param path the path of csv file
   * @return true if its valid, false if not.
   */
  private Boolean validatePath(String path) {
    if (hasCsvFile && path.endsWith(CSV)) {
      this.csvFilePath = path;
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  /**
   * Validate due date.
   *
   * @param dueDate a string represents todo due date.
   * @return true if its valid, false if not.
   */
  private Boolean validateDueDate(String dueDate) {
    Pattern re = Pattern.compile("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])");
    Matcher m = re.matcher(dueDate);
    if (m.matches()) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  /**
   * Validate priority number.
   *
   * @param priorityNum a string represents priority number.
   * @return true if its valid, false if not.
   */
  private Boolean validatePriorityNum(String priorityNum) {
    Pattern re = Pattern.compile("[1-3]");
    Matcher m = re.matcher(priorityNum);
    if (m.matches()) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  /**
   * Validate todo id.
   *
   * @param completedToDoId a string represents id of todo item.
   * @return true if its valid, false if not.
   */
  private Boolean validateId(String completedToDoId) {
    Pattern re = Pattern.compile("\\d+");
    Matcher m = re.matcher(completedToDoId);
    if (m.matches()) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  /**
   * Validate args.
   */
  public void validateArgs() {
    if (!hasCsvFile) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No --csv-file was given.");
    }
    if (!hasCsvFilePath) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No <path/to/file> was given.");
    }
    if (hasAddTodo && !hasToDoText) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No --todo-text for -add--todo was given."
      );
    }
    if (hasToDoText && !hasToDoDescription) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No <description of todo> for --todo-text was given."
      );
    }
    if (!hasAddTodo && hasCompleted) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No --add-todo for --completed was given.");
    }

    if (!hasAddTodo && hasDue) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No --add-todo for --due was given.");
    }

    if (hasAddTodo && hasDue && !hasDueDate) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No <due date> for --due was given.");
    }
    if (!hasAddTodo && hasPriority) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No --add-todo for --priority was given.");
    }
    if (hasAddTodo && hasPriority && !hasPriorityNum) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No priority number <1, 2, or 3> for --priority was given.");
    }
    if (!hasAddTodo && hasCategory) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No --add-todo for --category was given."
      );
    }
    if (hasAddTodo && hasCategory && !hasCategoryName) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No <a category name> for --category was given.");
    }
    if (hasCompletedTodo && !hasCompletedToDoId) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No <id> for --complete-todo was given.");
    }
    if (!hasDisplay && hasShowIncomplete) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No --display for --show-incomplete was given.");
    }
    if (!hasDisplay && hasShowCategory) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No --display for --show-category was given.");
    }
    if (hasDisplay && hasShowCategory && !hasShowCategoryName) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: --show-category provided but no <category> was given.");
    }
    if (!hasDisplay && hasSortByDate) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No --display for --sort-by-date was given.");
    }
    if (!hasDisplay && hasSortByPriority) {
      throw new CmdLineExceptions.MissingCommandException(
          "Error: No --display for --sort-by-priority was given.");
    }
    if (hasDisplay && hasSortByDate && hasSortByPriority) {
      throw new CmdLineExceptions.InvalidArgumentsException(
          "Error: --sort-by-date and --sort-by-priority cannot be provided at the same time.");
    }
  }

  /**
   * Create todo item.
   *
   * @return the to do
   */
  public ToDo createToDo() {
    ToDo todo = null;
    if (getHasCsvFile() && getHasCsvFilePath() && getHasToDoText() && getHasAddTodo()
        && getHasToDoDescription()) {
      todo = new ToDo(todoDescription);
      if (getHasCompleted()) {
        todo.setCompleted(Boolean.TRUE);
      }
      if (getHasCompletedToDoId()) {
        ToDoList.getToDoList(csvFilePath).updateCompleted(completedToDoId, csvFilePath);
      }
      if (getHasDue()) {
        todo.setDue(dueDate);
      }
      if (getHasPriority()) {
        todo.setPriority(Integer.valueOf(priorityNum));
      }
      if (getHasCategory()) {
        todo.setCategory(category);
      }
    }
    return todo;
  }

  /**
   * Gets has csv file.
   *
   * @return the has csv file
   */
  public Boolean getHasCsvFile() {
    return hasCsvFile;
  }

  /**
   * Gets has csv file path.
   *
   * @return the has csv file path
   */
  public Boolean getHasCsvFilePath() {
    return hasCsvFilePath;
  }

  /**
   * Gets csv file path.
   *
   * @return the csv file path
   */
  public String getCsvFilePath() {
    return csvFilePath;
  }

  /**
   * Gets has add todo.
   *
   * @return the has add todo
   */
  public Boolean getHasAddTodo() {
    return hasAddTodo;
  }

  /**
   * Gets has to do text.
   *
   * @return the has to do text
   */
  public Boolean getHasToDoText() {
    return hasToDoText;
  }

  /**
   * Gets has to do description.
   *
   * @return the has to do description
   */
  public Boolean getHasToDoDescription() {
    return hasToDoDescription;
  }

  /**
   * Gets todo description.
   *
   * @return the todo description
   */
  public String getTodoDescription() {
    return todoDescription;
  }

  /**
   * Gets has completed.
   *
   * @return the has completed
   */
  public Boolean getHasCompleted() {
    return hasCompleted;
  }

  /**
   * Gets has due.
   *
   * @return the has due
   */
  public Boolean getHasDue() {
    return hasDue;
  }

  /**
   * Gets has due date.
   *
   * @return the has due date
   */
  public Boolean getHasDueDate() {
    return hasDueDate;
  }

  /**
   * Gets due date.
   *
   * @return the due date
   */
  public String getDueDate() {
    return dueDate;
  }

  /**
   * Gets has priority.
   *
   * @return the has priority
   */
  public Boolean getHasPriority() {
    return hasPriority;
  }

  /**
   * Gets has priority num.
   *
   * @return the has priority num
   */
  public Boolean getHasPriorityNum() {
    return hasPriorityNum;
  }

  /**
   * Gets priority num.
   *
   * @return the priority num
   */
  public String getPriorityNum() {
    return priorityNum;
  }

  /**
   * Gets has category.
   *
   * @return the has category
   */
  public Boolean getHasCategory() {
    return hasCategory;
  }

  /**
   * Gets has category name.
   *
   * @return the has category name
   */
  public Boolean getHasCategoryName() {
    return hasCategoryName;
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
   * Gets has completed todo.
   *
   * @return the has completed todo
   */
  public Boolean getHasCompletedTodo() {
    return hasCompletedTodo;
  }

  /**
   * Gets has completed to do id.
   *
   * @return the has completed to do id
   */
  public Boolean getHasCompletedToDoId() {
    return hasCompletedToDoId;
  }

  /**
   * Gets completed to do id.
   *
   * @return the completed to do id
   */
  public List<Integer> getCompletedToDoId() {
    return completedToDoId;
  }

  /**
   * Gets has display.
   *
   * @return the has display
   */
  public Boolean getHasDisplay() {
    return hasDisplay;
  }

  /**
   * Gets has show incomplete.
   *
   * @return the has show incomplete
   */
  public Boolean getHasShowIncomplete() {
    return hasShowIncomplete;
  }

  /**
   * Gets has show category.
   *
   * @return the has show category
   */
  public Boolean getHasShowCategory() {
    return hasShowCategory;
  }

  /**
   * Gets has show category name.
   *
   * @return the has show category name
   */
  public Boolean getHasShowCategoryName() {
    return hasShowCategoryName;
  }

  /**
   * Gets show category name.
   *
   * @return the show category name
   */
  public String getShowCategoryName() {
    return showCategoryName;
  }

  /**
   * Gets has sort by date.
   *
   * @return the has sort by date
   */
  public Boolean getHasSortByDate() {
    return hasSortByDate;
  }

  /**
   * Gets has sort by priority.
   *
   * @return the has sort by priority
   */
  public Boolean getHasSortByPriority() {
    return hasSortByPriority;
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
    CmdLineParser that = (CmdLineParser) o;
    return Objects.equals(hasCsvFile, that.hasCsvFile) && Objects.equals(
        hasCsvFilePath, that.hasCsvFilePath) && Objects.equals(csvFilePath,
        that.csvFilePath) && Objects.equals(hasAddTodo, that.hasAddTodo)
        && Objects.equals(hasToDoText, that.hasToDoText) && Objects.equals(
        hasToDoDescription, that.hasToDoDescription) && Objects.equals(todoDescription,
        that.todoDescription) && Objects.equals(hasCompleted, that.hasCompleted)
        && Objects.equals(hasDue, that.hasDue) && Objects.equals(hasDueDate,
        that.hasDueDate) && Objects.equals(dueDate, that.dueDate)
        && Objects.equals(hasPriority, that.hasPriority) && Objects.equals(
        hasPriorityNum, that.hasPriorityNum) && Objects.equals(priorityNum,
        that.priorityNum) && Objects.equals(hasCategory, that.hasCategory)
        && Objects.equals(hasCategoryName, that.hasCategoryName)
        && Objects.equals(category, that.category) && Objects.equals(
        hasCompletedTodo, that.hasCompletedTodo) && Objects.equals(hasCompletedToDoId,
        that.hasCompletedToDoId) && Objects.equals(completedToDoId, that.completedToDoId)
        && Objects.equals(hasDisplay, that.hasDisplay) && Objects.equals(
        hasShowIncomplete, that.hasShowIncomplete) && Objects.equals(hasShowCategory,
        that.hasShowCategory) && Objects.equals(hasShowCategoryName,
        that.hasShowCategoryName) && Objects.equals(showCategoryName, that.showCategoryName)
        && Objects.equals(hasSortByDate, that.hasSortByDate) && Objects.equals(
        hasSortByPriority, that.hasSortByPriority);
  }

  /**
   * Return the hashCode of this object.
   *
   * @return the hashCode of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(hasCsvFile, hasCsvFilePath, csvFilePath, hasAddTodo, hasToDoText,
        hasToDoDescription, todoDescription, hasCompleted, hasDue, hasDueDate, dueDate, hasPriority,
        hasPriorityNum, priorityNum, hasCategory, hasCategoryName, category, hasCompletedTodo,
        hasCompletedToDoId, completedToDoId, hasDisplay, hasShowIncomplete, hasShowCategory,
        hasShowCategoryName, showCategoryName, hasSortByDate, hasSortByPriority);
  }

  /**
   * Return the string representation of the object.
   *
   * @return the string representation of the object.
   */
  @Override
  public String toString() {
    return "CmdLineParser{" +
        "hasCsvFile=" + hasCsvFile +
        ", hasCsvFilePath=" + hasCsvFilePath +
        ", csvFilePath='" + csvFilePath + '\'' +
        ", hasAddTodo=" + hasAddTodo +
        ", hasToDoText=" + hasToDoText +
        ", hasToDoDescription=" + hasToDoDescription +
        ", todoDescription='" + todoDescription + '\'' +
        ", hasCompleted=" + hasCompleted +
        ", hasDue=" + hasDue +
        ", hasDueDate=" + hasDueDate +
        ", dueDate='" + dueDate + '\'' +
        ", hasPriority=" + hasPriority +
        ", hasPriorityNum=" + hasPriorityNum +
        ", priorityNum='" + priorityNum + '\'' +
        ", hasCategory=" + hasCategory +
        ", hasCategoryName=" + hasCategoryName +
        ", category='" + category + '\'' +
        ", hasCompletedTodo=" + hasCompletedTodo +
        ", hasCompletedToDoId=" + hasCompletedToDoId +
        ", completedToDoId=" + completedToDoId +
        ", hasDisplay=" + hasDisplay +
        ", hasShowIncomplete=" + hasShowIncomplete +
        ", hasShowCategory=" + hasShowCategory +
        ", hasShowCategoryName=" + hasShowCategoryName +
        ", showCategoryName='" + showCategoryName + '\'' +
        ", hasSortByDate=" + hasSortByDate +
        ", hasSortByPriority=" + hasSortByPriority +
        '}';
  }
}
