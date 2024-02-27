package problem1.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.controller.CmdLineParser;

class PriorityComparatorTest {

  PriorityComparator pc;
  PriorityComparator pc2;
  private ToDo todo1;
  private ToDo todo2;
  private ToDo todo3;
  private ToDo todo4;
  private Map<Integer, ToDo> map;
  private Map<Integer, ToDo> map2;

  @BeforeEach
  void setUp() {
    todo1 = new ToDo("study");
    todo2 = new ToDo("hiking");
    todo3 = new ToDo("eat");
    todo4 = new ToDo("play");
    todo1.setPriority(1);
    todo3.setPriority(2);
    map = new HashMap<>();
    map.put(1, todo1);
    map.put(2, todo2);
    map.put(3, todo3);
    map.put(4, todo4);
    pc = new PriorityComparator(map);
    map2 = new HashMap<>();
    map2.put(1, todo1);
    pc2 = new PriorityComparator(map2);
  }

  @Test
  void compare() {
    assertEquals(pc.compare(todo1, todo2), -1);
    assertEquals(pc.compare(todo2, todo3), 1);
    assertEquals(pc.compare(todo1, todo3), -1);
  }

  @Test
  void testEquals_SameMemoryLocation() {
    assertTrue(pc.equals(pc));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(pc.equals(null));
  }

  @Test
  void testEquals_DifferentDataTypes() {
    CmdLineParser parser = new CmdLineParser();
    assertFalse(pc.equals(parser));
  }

  @Test
  void testEquals_DifferentFields() {
    assertFalse(pc.equals(pc2));
  }

  @Test
  void testHashCode() {
    int expectedHashCode = Objects.hash(map);
    System.out.println(expectedHashCode);
    assertEquals(pc.hashCode(), expectedHashCode);
  }

  @Test
  void testToString() {
    String str = "PriorityComparator{map={1=ToDo{text='study', completed=false, due='null', "
        + "priority=1, category='null'}, 2=ToDo{text='hiking', completed=false, due='null', "
        + "priority=3, category='null'}, 3=ToDo{text='eat', completed=false, due='null', "
        + "priority=2, category='null'}, 4=ToDo{text='play', completed=false, due='null', "
        + "priority=3, category='null'}}}";
    assertEquals(pc.toString(), str);
  }
}