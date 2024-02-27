package problem1.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.controller.CmdLineParser;

class ToDoTest {

  private ToDo todo1;
  private ToDo todo2;
  private ToDo todo3;
  private ToDo todo4;
  private ToDo todo5;
  private ToDo todo6;

  @BeforeEach
  void setUp() {
    todo1 = new ToDo("Study");
    todo1.setDue("2015-04-16");
    todo1.setCategory("Daily");

    todo2 = new ToDo("Run");
    todo2.setDue("2015-04-16");
    todo2.setCategory("Daily");

    todo3 = new ToDo("Study");
    todo3.setCategory("Daily");

    todo4 = new ToDo("Study");
    todo4.setDue("2015-04-16");
    todo4.setCategory("Eat");

    todo5 = new ToDo("Study");
    todo5.setDue("2015-04-16");
    todo5.setCategory("Daily");
    todo5.setCompleted(true);

    todo6 = new ToDo("Study");
    todo6.setDue("2015-04-16");
    todo6.setPriority(2);
  }

  @Test
  void testEquals_SameMemoryLocation() {
    assertTrue(todo1.equals(todo1));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(todo1.equals(null));
  }

  @Test
  void testEquals_DifferentDataTypes() {
    CmdLineParser parser = new CmdLineParser();
    assertFalse(todo1.equals(parser));
  }

  @Test
  void testEquals_DifferentFields() {
    assertFalse(todo1.equals(todo2));
    assertFalse(todo1.equals(todo3));
    assertFalse(todo1.equals(todo4));
    assertFalse(todo1.equals(todo5));
    assertFalse(todo1.equals(todo6));
  }

  @Test
  void testHashCode() {
    int expectedHashCode = Objects.hash("Study", false, "2015-04-16", 3, "Daily");
    System.out.println(expectedHashCode);
    assertEquals(todo1.hashCode(), expectedHashCode);
  }

  @Test
  void testToString() {
    String str = "ToDo{text='Study', completed=false, due='2015-04-16', priority=3, category='Daily'}";
    assertEquals(todo1.toString(), str);
  }
}