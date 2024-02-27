package problem1.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.view.DisplayList;

class CmdLineExceptionsTest {

  CmdLineExceptions e;

  @BeforeEach
  void setUp() {
    e = new CmdLineExceptions("Error");
  }

  @Test
  void testEquals_SameMemoryLocation() {
    assertTrue(e.equals(e));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(e.equals(null));
  }

  @Test
  void testEquals_DifferentDataTypes() {
    DisplayList displayList = new DisplayList();
    assertFalse(e.equals(displayList));
  }

  @Test
  void testToString() {
    String str = "CmdLineExceptions{}";
    assertEquals(e.toString(), str);
  }
}