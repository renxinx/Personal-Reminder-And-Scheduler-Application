package problem1.view;

import static org.junit.jupiter.api.Assertions.*;

import com.sun.tools.javac.comp.Todo;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.controller.CmdLineParser;
import problem1.model.ToDo;

class DisplayListTest {

  ToDo todo1;
  ToDo todo2;
  ToDo todo3;
  Map<Integer, ToDo> map;
  DisplayList testDisplayList;
  DisplayList testDisplayList2;

  @BeforeEach
  void setUp() {
    todo1 = new ToDo("apple");
    todo1.setPriority(1);
    todo1.setDue("2020-03-09");
    todo1.setCategory("eat");
    todo2 = new ToDo("banana");
    todo2.setPriority(2);
    todo2.setCompleted(true);
    todo2.setDue("2020-01-20");
    todo2.setCategory("eat");
    todo3 = new ToDo("grape");
    todo3.setDue("2021-04-18");
    todo3.setCategory("eat");
    map = new HashMap<>();
    map.put(1, todo1);
    map.put(2, todo2);
    map.put(3, todo3);
    testDisplayList = new DisplayList();
    testDisplayList2 = new DisplayList();
  }

  @Test
  void display() {
    testDisplayList.display(map, true, true,
        "eat", true, true);
    testDisplayList2.display(map, false, false,
        "", false, false);
  }

  @Test
  void testEquals_SameMemoryLocation() {
    assertTrue(testDisplayList.equals(testDisplayList));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(testDisplayList.equals(null));
  }

  @Test
  void testEquals_DifferentDataTypes() {
    CmdLineParser parser = new CmdLineParser();
    assertFalse(testDisplayList.equals(parser));
  }

  @Test
  void testToString() {
    String str = "DisplayList{contents={}}";
    assertEquals(testDisplayList.toString(), str);
  }
}