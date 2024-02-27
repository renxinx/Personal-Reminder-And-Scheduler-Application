package problem1.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.controller.CmdLineParser;

class DateComparatorTest {

  DateComparator dc;
  DateComparator dc2;
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
    todo1.setDue("2021-03-24");
    todo3.setDue("2021-03-29");
    map = new HashMap<>();
    map.put(1, todo1);
    map.put(2, todo2);
    map.put(3, todo3);
    map.put(4, todo4);
    dc = new DateComparator(map);
    map2 = new HashMap<>();
    map2.put(1, todo1);
    dc2 = new DateComparator(map2);
  }

  @Test
  void compare() {
    assertEquals(dc.compare(todo1, todo2), -1);
    assertEquals(dc.compare(todo2, todo3), 1);
    assertEquals(dc.compare(todo2, todo4), 0);
    assertEquals(dc.compare(todo1, todo3), -5);
  }

  @Test
  void testEquals_SameMemoryLocation() {
    assertTrue(dc.equals(dc));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(dc.equals(null));
  }

  @Test
  void testEquals_DifferentDataTypes() {
    CmdLineParser parser = new CmdLineParser();
    assertFalse(dc.equals(parser));
  }

  @Test
  void testEquals_DifferentFields() {
    assertFalse(dc.equals(dc2));
  }

  @Test
  void testHashCode() {
    int expectedHashCode = Objects.hash(map);
    System.out.println(expectedHashCode);
    assertEquals(dc.hashCode(), expectedHashCode);
  }

  @Test
  void testToString() {
    String str = "DateComparator{map={1=ToDo{text='study', completed=false, due='2021-03-24', "
        + "priority=3, category='null'}, 2=ToDo{text='hiking', completed=false, due='null', "
        + "priority=3, category='null'}, 3=ToDo{text='eat', completed=false, due='2021-03-29', "
        + "priority=3, category='null'}, 4=ToDo{text='play', completed=false, due='null', "
        + "priority=3, category='null'}}}";
    assertEquals(dc.toString(), str);
  }
}