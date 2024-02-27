package problem1.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import problem1.controller.CmdLineExceptions.InvalidArgumentsException;

/**
 * The class ToDoList implements interface ToDoList.
 */
public class ToDoList implements InterfaceToDoList {

  private static final String TRUE = "true";
  private static final String COMMA = ",";
  private static final String DOUBLE_QUOTES = "\"";
  private static final String HEADER_ID = "id";
  private static final String HEADER_TEXT = "text";
  private static final String HEADER_COMPLETED = "completed";
  private static final String HEADER_DUE = "due";
  private static final String HEADER_PRIORITY = "priority";
  private static final String HEADER_CATEGORY = "category";
  private static final Integer HEADER_ROW = 0;
  private static final Integer ID_COL = 1;
  private static final Integer TEXT_COL = 2;
  private static final Integer COMPLETED_COL = 3;
  private static final Integer DUE_COL = 4;
  private static final Integer PRIORITY_COL = 5;
  private static final Integer CATEGORY_COL = 6;
  private static final String QUESTION_MARK = "?";

  private Map<Integer, ToDo> map;
  private Integer id;
  private static ToDoList toDoList;

  private ToDoList(String path) {
    this.map = new HashMap<>();
    this.id = 0;
    this.readFromCsv(path);
    if (map.isEmpty()) {
      this.writeHeader(path);
    }
  }

  /**
   * Gets to do list.
   *
   * @param path the path
   * @return the to do list
   */
  public static synchronized ToDoList getToDoList(String path) {
    if (toDoList == null) {
      toDoList = new ToDoList(path);
    }
    return toDoList;
  }

  /**
   * Read from csv using bufferedreader.
   *
   * @param path the path of file
   */
  private void readFromCsv(String path) {
    try (BufferedReader inputFile = new BufferedReader(new FileReader(path))) {
      String line;
      int cnt = 0;
      while ((line = inputFile.readLine()) != null) {
        if (cnt != HEADER_ROW) {
          String[] words = line.split("\"*,*\"");
          this.id = Integer.parseInt(words[ID_COL]);
          map.put(this.id, new ToDo(words[TEXT_COL]));
          map.get(this.id).setCompleted(Boolean.valueOf(words[COMPLETED_COL]));
          map.get(this.id)
              .setDue(Objects.equals(words[DUE_COL], QUESTION_MARK) ? null : words[DUE_COL]);
          map.get(this.id).setPriority(Integer.parseInt(words[PRIORITY_COL]));
          map.get(this.id)
              .setCategory(
                  Objects.equals(words[CATEGORY_COL], QUESTION_MARK) ? null : words[CATEGORY_COL]);
        }
        cnt++;
      }
      this.id++;
    } catch (FileNotFoundException fnfe) {
      if (!this.map.isEmpty()) {
        System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
        fnfe.printStackTrace();
      }
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

  /**
   * Write the header of todo list using stringbuilder.
   *
   * @param path the path of file
   */
  private void writeHeader(String path) {
    StringBuilder sb = new StringBuilder();
    sb.append(DOUBLE_QUOTES).append(HEADER_ID).append(DOUBLE_QUOTES).append(COMMA)
        .append(DOUBLE_QUOTES).append(HEADER_TEXT).append(DOUBLE_QUOTES).append(COMMA)
        .append(DOUBLE_QUOTES).append(HEADER_COMPLETED).append(DOUBLE_QUOTES).append(COMMA)
        .append(DOUBLE_QUOTES).append(HEADER_DUE).append(DOUBLE_QUOTES).append(COMMA)
        .append(DOUBLE_QUOTES).append(HEADER_PRIORITY).append(DOUBLE_QUOTES).append(COMMA)
        .append(DOUBLE_QUOTES).append(HEADER_CATEGORY).append(DOUBLE_QUOTES).append(System.lineSeparator());
    String str = sb.toString();
    writeToCsv(str, path);
    this.id++;
  }

  /**
   * Convert the todo object to string and write it to csv file.
   *
   * @param toDo ToDo object
   * @param path the path of file
   */
  @Override
  public void addToDo(ToDo toDo, String path) {
    map.put(id, toDo);
    String str = convertToStr(id, toDo);
    writeToCsv(str, path);
    this.id++;
  }

  /**
   * Convert todo to string.
   *
   * @param id an integer indicating identical number for different todo item
   * @param toDo ToDo object
   * @return the string representation of a todo
   */
  private String convertToStr(Integer id, ToDo toDo) {
    StringBuilder sb = new StringBuilder();
    sb.append(DOUBLE_QUOTES).append(String.valueOf(id)).append(DOUBLE_QUOTES).append(COMMA)
        .append(DOUBLE_QUOTES).append(toDo.getText()).append(DOUBLE_QUOTES).append(COMMA)
        .append(DOUBLE_QUOTES).append(String.valueOf(toDo.getCompleted())).append(DOUBLE_QUOTES)
        .append(COMMA).append(DOUBLE_QUOTES)
        .append(toDo.getDue() == null ? QUESTION_MARK : toDo.getDue())
        .append(DOUBLE_QUOTES).append(COMMA).append(DOUBLE_QUOTES)
        .append(String.valueOf(toDo.getPriority())).append(DOUBLE_QUOTES).append(COMMA)
        .append(DOUBLE_QUOTES).append(toDo.getCategory() == null ? QUESTION_MARK : toDo.getCategory())
        .append(DOUBLE_QUOTES).append(System.lineSeparator());
    return sb.toString();
  }

  /**
   * Write string to csv.
   *
   * @param str an integer indicating identical number for different todo item
   * @param path the path to file
   */
  private void writeToCsv(String str, String path) {
    try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(path, true))) {
      outputFile.append(str);
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

  /**
   * Update the completion status of todo item.
   *
   * @param idList list of todo IDs
   * @param path the path to file
   */
  @Override
  public void updateCompleted(List<Integer> idList, String path) {
    for (Integer id : idList) {
      if (map.containsKey(id)) {
        // update map
        map.get(id).setCompleted(Boolean.TRUE);
        // update csv
        try {
          Path in = Paths.get(path);
          Path out = Paths.get(path);
          List<String> lines = Files.readAllLines(in);
          List<String> newLines = new ArrayList<>();
          for (int i = 0; i < lines.size(); i++) {
            if (i == id) {
              String[] words = lines.get(i).split("\"*,*\"");
              StringBuilder sb = new StringBuilder();
              sb.append(DOUBLE_QUOTES).append(words[ID_COL]).append(DOUBLE_QUOTES).append(COMMA)
                  .append(DOUBLE_QUOTES).append(words[TEXT_COL]).append(DOUBLE_QUOTES).append(COMMA)
                  .append(DOUBLE_QUOTES).append(TRUE).append(DOUBLE_QUOTES).append(COMMA)
                  .append(DOUBLE_QUOTES).append(words[DUE_COL]).append(DOUBLE_QUOTES).append(COMMA)
                  .append(DOUBLE_QUOTES).append(words[PRIORITY_COL]).append(DOUBLE_QUOTES).append(COMMA)
                  .append(DOUBLE_QUOTES).append(words[CATEGORY_COL]).append(DOUBLE_QUOTES);
              newLines.add(sb.toString());
            } else {
              newLines.add(lines.get(i));
            }
          }
          Files.write(out, newLines);
        } catch (FileNotFoundException fnfe) {
          System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
          fnfe.printStackTrace();
        } catch (IOException ioe) {
          System.out.println("Something went wrong! : " + ioe.getMessage());
          ioe.printStackTrace();
        }
      } else {
        throw new InvalidArgumentsException("ID not found.");
      }
    }
  }

  /**
   * Gets map.
   *
   * @return the map
   */
  public Map<Integer, ToDo> getMap() {
    return map;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Integer getId() {
    return id;
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
    ToDoList toDoList = (ToDoList) o;
    return Objects.equals(map, toDoList.map) && Objects.equals(id, toDoList.id);
  }

  /**
   * Return the hashCode of this object.
   *
   * @return the hashCode of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(map, id);
  }

  /**
   * Return the string representation of the object.
   *
   * @return the string representation of the object.
   */
  @Override
  public String toString() {
    return "ToDoList{" +
        "map=" + map +
        ", id=" + id +
        '}';
  }
}

