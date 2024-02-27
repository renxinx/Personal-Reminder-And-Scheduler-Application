package problem1.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.controller.CmdLineParser;

class ToDoListTest {

  private String path1;
  private ToDo todo1;
  private ToDo todo2;
  private ToDoList tld1;
  private ArrayList<Integer> list;
  private Map<Integer, ToDo> map;

  @BeforeEach
  void setUp() {
    path1 = "src/test/resources/my_todo_2.csv";
    todo1 = new ToDo("study");
    todo2 = new ToDo("hiking");
    list = new ArrayList<>();
    list.add(1);
    map = new HashMap<>();
    map.put(1, todo1);
  }

  @Test
  void getMap() {
    tld1 = ToDoList.getToDoList(path1);
    todo1.setPriority(1);
    tld1.addToDo(todo1, path1);
    assertEquals(tld1.getMap(), map);
  }

  @Test
  void getId() {
    tld1 = ToDoList.getToDoList(path1);
    tld1.addToDo(todo2, path1);
    assertEquals(tld1.getId(), 3);
  }

  @Test
  void updateCompleted() {
    tld1 = ToDoList.getToDoList(path1);
    tld1.updateCompleted(list, path1);
    assertTrue(tld1.getMap().get(1).getCompleted());
  }

  @Test
  void testEquals_SameMemoryLocation() {
    tld1 = ToDoList.getToDoList(path1);
    assertTrue(tld1.equals(tld1));
  }

  @Test
  void testEquals_NullObject() {
    tld1 = ToDoList.getToDoList(path1);
    assertFalse(tld1.equals(null));
  }

  @Test
  void testEquals_DifferentDataTypes() {
    tld1 = ToDoList.getToDoList(path1);
    CmdLineParser parser = new CmdLineParser();
    assertFalse(tld1.equals(parser));
  }

  @Test
  void testToString() {
    tld1 = ToDoList.getToDoList(path1);
    String str = "ToDoList{" +
        "map=" + tld1.getMap() +
        ", id=" + tld1.getId() +
        '}';
    assertEquals(str, tld1.toString());
  }
}