package problem1.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import problem1.model.DateComparator;
import problem1.model.PriorityComparator;
import problem1.model.ToDo;

/**
 * The Class Display list used for displaying a list of Todos.
 */
public class DisplayList {

  private Map<Integer, ToDo> contents;
  private static final String HEADER_ID = "id";
  private static final String HEADER_TEXT = "text";
  private static final String HEADER_COMPLETED = "completed";
  private static final String HEADER_DUE = "due";
  private static final String HEADER_PRIORITY = "priority";
  private static final String HEADER_CATEGORY = "category";
  private static final String NEW_LINE = "\n";
  private static final String QUESTION_MARK = "?";

  /**
   * Instantiates a new Display list.
   */
  public DisplayList() {
    this.contents = new HashMap<>();
  }

  /**
   * Display the content of to do list.
   *
   * @param map              the map
   * @param isShowIncomplete boolean indicating only showing incomplete Todos or not
   * @param isShowCategory   boolean indicating only showing Todos with a particular category or not
   * @param category         the particular category shown
   * @param isSortByDate     boolean indicating the todo list is sort by date or not
   * @param isSortByPriority boolean indicating the todo list is sort by priority or not
   */
  public void display(Map<Integer, ToDo> map, Boolean isShowIncomplete,
      Boolean isShowCategory, String category, Boolean isSortByDate, Boolean isSortByPriority) {
    List<List<String>> rows = displayHelper(map, isShowIncomplete, isShowCategory, category,
        isSortByDate, isSortByPriority);
    System.out.println(formatAsTable(rows));
  }

  /**
   * Display helper method used for sorting and converting operation.
   *
   * @param map              the todo map
   * @param isShowIncomplete boolean indicating only showing incomplete Todos or not
   * @param isShowCategory   boolean indicating only showing Todos with a particular category or not
   * @param category         the particular category shown
   * @param isSortByDate     boolean indicating the todo list is sort by date or not
   * @param isSortByPriority boolean indicating the todo list is sort by priority or not
   * @return the filtered list of todos to display
   */
  private List<List<String>> displayHelper(Map<Integer, ToDo> map, Boolean isShowIncomplete,
      Boolean isShowCategory, String category, Boolean isSortByDate, Boolean isSortByPriority) {
    contents = map;
    if (isShowIncomplete) {
      contents = showIncomplete(contents);
    }
    if (isShowCategory) {
      contents = showCategory(contents, category);
    }
    if (isSortByDate) {
      DateComparator dc = new DateComparator(contents);
      contents = sortToDoList(contents, dc);
    }
    if (isSortByPriority) {
      PriorityComparator pc = new PriorityComparator(contents);
      contents = sortToDoList(contents, pc);
    }
    List<List<String>> rows = convertToList(contents);
    return rows;
  }

  /**
   * Filter the list to only include incomplete Todo
   *
   * @param map the todo map
   * @return the filtered todo map
   */
  private Map<Integer, ToDo> showIncomplete(Map<Integer, ToDo> map) {
    Map<Integer, ToDo> contents = new HashMap<>();
    Iterator<Entry<Integer, ToDo>> itr = map.entrySet().iterator();
    while (itr.hasNext()) {
      Map.Entry<Integer, ToDo> entry = itr.next();
      Boolean isCompleted = entry.getValue().getCompleted();
      if (!isCompleted) {
        contents.put(entry.getKey(), entry.getValue());
      }
    }
    return contents;
  }

  /**
   * Filter the list to only include Todos with a particular category
   *
   * @param map the todo map
   * @param category the particular category shown
   * @return the filtered todo map
   */
  private Map<Integer, ToDo> showCategory(Map<Integer, ToDo> map, String category) {
    Map<Integer, ToDo> contents = new HashMap<>();
    Iterator<Entry<Integer, ToDo>> itr = map.entrySet().iterator();
    while (itr.hasNext()) {
      Map.Entry<Integer, ToDo> entry = itr.next();
      String currCategory = entry.getValue().getCategory();
      if (currCategory.equals(category)) {
        contents.put(entry.getKey(), entry.getValue());
      }
    }
    return contents;
  }

  /**
   * Sort the Todos by date (ascending)
   *
   * @param map the todo map
   * @param comparator the comparator for sorting
   * @return the sorted todo map
   */
  private Map<Integer, ToDo> sortToDoList(Map<Integer, ToDo> map, Comparator comparator) {
    List<Entry<Integer, ToDo>> list = new ArrayList<>(map.entrySet());
    list.sort(Entry.comparingByValue(comparator));

    Map<Integer, ToDo> newMap = new LinkedHashMap<>();
    for (Entry<Integer, ToDo> entry : list) {
      newMap.put(entry.getKey(), entry.getValue());
    }
    return newMap;
  }

  /**
   * Convert the todo map into list of rows
   *
   * @param map the todo map
   * @return the converted list
   */
  private List<List<String>> convertToList(Map<Integer, ToDo> map) {
    List<List<String>> rows = new ArrayList<>();
    List<String> headers = Arrays.asList(HEADER_ID, HEADER_TEXT, HEADER_COMPLETED,
        HEADER_DUE, HEADER_PRIORITY, HEADER_CATEGORY);
    rows.add(headers);
    Iterator<Entry<Integer, ToDo>> itr = map.entrySet().iterator();
    while (itr.hasNext()) {
      List<String> list = new ArrayList<>();
      Map.Entry<Integer, ToDo> entry = itr.next();
      list.add(String.valueOf(entry.getKey()));
      list.add(entry.getValue().getText());
      list.add(String.valueOf(entry.getValue().getCompleted()));
      if (entry.getValue().getDue() == null) {
        list.add(QUESTION_MARK);
      } else {
        list.add(entry.getValue().getDue());
      }
      list.add(String.valueOf(entry.getValue().getPriority()));
      if (entry.getValue().getCategory() == null) {
        list.add(QUESTION_MARK);
      } else {
        list.add(entry.getValue().getCategory());
      }
      rows.add(list);
    }
    return rows;
  }

  /**
   * Formatting list of rows into table using formatbuilder.
   *
   * @param rows list of rows of todo list
   * @return a String represent as a todo list
   */
  private static String formatAsTable(List<List<String>> rows) {
    int[] maxLengths = new int[rows.get(0).size()];
    for (List<String> row : rows) {
      for (int i = 0; i < row.size(); i++) {
        maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
      }
    }

    StringBuilder formatBuilder = new StringBuilder();
    for (int maxLength : maxLengths) {
      formatBuilder.append("%-").append(maxLength + 2).append("s");
    }
    String format = formatBuilder.toString();

    StringBuilder result = new StringBuilder();
    for (List<String> row : rows) {
      result.append(String.format(format, row.toArray(new String[0]))).append(NEW_LINE);
    }
    return result.toString();
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
    DisplayList that = (DisplayList) o;
    return Objects.equals(contents, that.contents);
  }

  /**
   * Return the hashCode of this object.
   *
   * @return the hashCode of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(contents);
  }

  /**
   * Return the string representation of the object.
   *
   * @return the string representation of the object.
   */
  @Override
  public String toString() {
    return "DisplayList{" +
        "contents=" + contents +
        '}';
  }
}
