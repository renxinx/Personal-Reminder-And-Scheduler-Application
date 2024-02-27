package problem1.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.ToDo;
import problem1.view.DisplayList;

class CmdLineParserTest {

  private String[] args1;
  private CmdLineParser parser1;
  private String[] args2;
  private CmdLineParser parser2;
  private String[] args3;
  private CmdLineParser parser3;
  private String[] args4;
  private CmdLineParser parser4;
  private String[] args5;
  private CmdLineParser parser5;
  private String[] argsNoCsvFile;
  private CmdLineParser parserNoCsvFile;
  private String[] argsNoCsvFilePath;
  private CmdLineParser parserNoCsvFilePath;
  private String[] argsHasAddNoText;
  private CmdLineParser parserHasAddNoText;
  private String[] argsHasAddNoDescription;
  private CmdLineParser parserHasAddNoDescription;
  private String[] argsNoAddHasCompleted;
  private CmdLineParser parserNoAddHasCompleted;
  private String[] argsNoAddHasDue;
  private CmdLineParser parserNoAddHasDue;
  private String[] argsHasAddHasDueNoDueDate;
  private CmdLineParser parserHasAddHasDueNoDueDate;
  private String[] argsNoAddHasPriority;
  private CmdLineParser parserNoAddHasPriority;
  private String[] argsHasAddHasPriorityNoPriorityNum;
  private CmdLineParser parserHasAddHasPriorityNoPriorityNum;
  private String[] argsNoAddHasCategory;
  private CmdLineParser parserNoAddHasCategory;
  private String[] argsHasAddHasCategoryNoCategoryNum;
  private CmdLineParser parserHasAddHasCategoryNoCategoryNum;
  private String[] argsHasCompletedToDoNoId;
  private CmdLineParser parserHasCompletedToDoNoId;
  private String[] argsNoDisplayHasShowIncomplete;
  private CmdLineParser parserNoDisplayHasShowIncomplete;
  private String[] argsNoDisplayHasShowCategory;
  private CmdLineParser parserNoDisplayHasShowCategory;
  private String[] argsNoDisplayHasShowCategoryNoName;
  private CmdLineParser parserNoDisplayHasShowCategoryNoName;
  private String[] argsNoDisplayHasSortByDate;
  private CmdLineParser parserNoDisplayHasSortByDate;
  private String[] argsNoDisplayHasSortByPriority;
  private CmdLineParser parserNoDisplayHasSortByPriority;
  private String[] argsHasDisplayHasSortByDateHasSortByPriority;
  private CmdLineParser parserHasDisplayHasSortByDateHasSortByPriority;

  @BeforeEach
  void setUp() {
    args1 = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--todo-text",
        "eat", "apple", "--completed", "--due", "2022-01-23", "--priority", "1", "--category",
        "eat"};
    parser1 = new CmdLineParser();
    args2 = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--todo-text",
        "banana", "--due", "2021-03-19", "--category", "eat"};
    parser2 = new CmdLineParser();
    args3 = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--complete-todo", "2"};
    parser3 = new CmdLineParser();
    args4 = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--display", "--show-incomplete"
        , "--show-category", "eat", "--sort-by-date"};
    parser4 = new CmdLineParser();
    args5 = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--display", "--show-incomplete"
        , "--show-category", "eat", "--sort-by-priority"};
    parser5 = new CmdLineParser();
    argsNoCsvFile = new String[]{
        "--add-todo", "--todo-text",
        "eat", "apple", "--completed", "--due", "2022-01-23", "--priority", "1", "--category",
        "eat"};
    parserNoCsvFile = new CmdLineParser();
    argsNoCsvFilePath = new String[]{
        "--csv-file", "--add-todo", "--todo-text",
        "eat", "apple", "--completed", "--due", "2022-01-23", "--priority", "1", "--category",
        "eat"};
    parserNoCsvFilePath = new CmdLineParser();
    argsHasAddNoText = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--completed",
        "--due", "2022-01-23", "--priority", "1", "--category", "eat"};
    parserHasAddNoText = new CmdLineParser();
    argsHasAddNoDescription = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--todo-text",
        "--completed", "--due", "2022-01-23", "--priority", "1", "--category", "eat"};
    parserHasAddNoDescription = new CmdLineParser();
    argsNoAddHasCompleted = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--completed"};
    parserNoAddHasCompleted = new CmdLineParser();
    argsNoAddHasDue = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--due", "2022-01-23"};
    parserNoAddHasDue = new CmdLineParser();
    argsHasAddHasDueNoDueDate = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--todo-text",
        "eat", "apple", "--completed", "--due"};
    parserHasAddHasDueNoDueDate = new CmdLineParser();
    argsNoAddHasPriority = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--priority", "1"};
    parserNoAddHasPriority = new CmdLineParser();
    argsHasAddHasPriorityNoPriorityNum = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--todo-text",
        "eat", "apple", "--completed", "--due", "2022-01-23", "--priority"};
    parserHasAddHasPriorityNoPriorityNum = new CmdLineParser();
    argsNoAddHasCategory = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--category", "eat"};
    parserNoAddHasCategory = new CmdLineParser();
    argsHasAddHasCategoryNoCategoryNum = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--todo-text",
        "eat", "apple", "--completed", "--due", "2022-01-23", "--priority", "1", "--category"};
    parserHasAddHasCategoryNoCategoryNum = new CmdLineParser();
    argsHasCompletedToDoNoId = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--complete-todo"};
    parserHasCompletedToDoNoId = new CmdLineParser();
    argsNoDisplayHasShowIncomplete = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--show-incomplete"
        , "--show-category", "eat", "--sort-by-priority"};
    parserNoDisplayHasShowIncomplete = new CmdLineParser();
    argsNoDisplayHasShowCategory = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv"
        , "--show-category", "eat", "--sort-by-priority"};
    parserNoDisplayHasShowCategory = new CmdLineParser();
    argsNoDisplayHasShowCategoryNoName = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--display", "--show-incomplete"
        , "--show-category"};
    parserNoDisplayHasShowCategoryNoName = new CmdLineParser();
    argsNoDisplayHasSortByDate = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--sort-by-date"};
    parserNoDisplayHasSortByDate = new CmdLineParser();
    argsNoDisplayHasSortByPriority = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--sort-by-priority"};
    parserNoDisplayHasSortByPriority = new CmdLineParser();
    argsHasDisplayHasSortByDateHasSortByPriority = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--display", "--sort-by-date",
        "--sort-by-priority"};
    parserHasDisplayHasSortByDateHasSortByPriority = new CmdLineParser();
  }

  @Test
  void processArgs() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasCsvFile());
  }

  @Test
  void validateArgs1_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("CSV file was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException1() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserNoCsvFile.processArgs(argsNoCsvFile);
      parserNoCsvFile.validateArgs();
    });
  }

  @Test
  void validateArgs2_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("CSV file path was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException2() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserNoCsvFilePath.processArgs(argsNoCsvFilePath);
      parserNoCsvFilePath.validateArgs();
    });
  }

  @Test
  void validateArgs3_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("--todo-text for -add--todo was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException3() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserHasAddNoText.processArgs(argsHasAddNoText);
      parserHasAddNoText.validateArgs();
    });
  }

  @Test
  void validateArgs4_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("<description of todo> for --todo-text was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException4() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserHasAddNoDescription.processArgs(argsHasAddNoDescription);
      parserHasAddNoDescription.validateArgs();
    });
  }

  @Test
  void validateArgs5_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("--add-todo for --completed was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException5() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserNoAddHasCompleted.processArgs(argsNoAddHasCompleted);
      parserNoAddHasCompleted.validateArgs();
    });
  }

  @Test
  void validateArgs6_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("--add-todo for --due was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException6() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserNoAddHasDue.processArgs(argsNoAddHasDue);
      parserNoAddHasDue.validateArgs();
    });
  }

  @Test
  void validateArgs7_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("<due date> for --due was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException7() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserHasAddHasDueNoDueDate.processArgs(argsHasAddHasDueNoDueDate);
      parserHasAddHasDueNoDueDate.validateArgs();
    });
  }

  @Test
  void validateArgs8_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("--add-todo for --priority was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException8() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserNoAddHasPriority.processArgs(argsNoAddHasPriority);
      parserNoAddHasPriority.validateArgs();
    });
  }

  @Test
  void validateArgs9_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("priority num for --priority was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException9() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserHasAddHasPriorityNoPriorityNum.processArgs(argsHasAddHasPriorityNoPriorityNum);
      parserHasAddHasPriorityNoPriorityNum.validateArgs();
    });
  }

  @Test
  void validateArgs10_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("--add-todo for --category was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException10() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserNoAddHasCategory.processArgs(argsNoAddHasCategory);
      parserNoAddHasCategory.validateArgs();
    });
  }

  @Test
  void validateArgs11_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("<a category name> for --category was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException11() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserHasAddHasCategoryNoCategoryNum.processArgs(argsHasAddHasCategoryNoCategoryNum);
      parserHasAddHasCategoryNoCategoryNum.validateArgs();
    });
  }

  @Test
  void validateArgs12_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("<id> for --complete-todo was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException12() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserHasCompletedToDoNoId.processArgs(argsHasCompletedToDoNoId);
      parserHasCompletedToDoNoId.validateArgs();
    });
  }

  @Test
  void validateArgs13_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("--display for --show-incomplete was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException13() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserNoDisplayHasShowIncomplete.processArgs(argsNoDisplayHasShowIncomplete);
      parserNoDisplayHasShowIncomplete.validateArgs();
    });
  }

  @Test
  void validateArgs14_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("--display for --show-category was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException14() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserNoDisplayHasShowCategory.processArgs(argsNoDisplayHasShowCategory);
      parserNoDisplayHasShowCategory.validateArgs();
    });
  }

  @Test
  void validateArgs15_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("<category> was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException15() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserNoDisplayHasShowCategoryNoName.processArgs(argsNoDisplayHasShowCategoryNoName);
      parserNoDisplayHasShowCategoryNoName.validateArgs();
    });
  }

  @Test
  void validateArgs16_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("--display for --sort-by-date was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException16() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserNoDisplayHasSortByDate.processArgs(argsNoDisplayHasSortByDate);
      parserNoDisplayHasSortByDate.validateArgs();
    });
  }

  @Test
  void validateArgs17_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("display for --sort-by-priority was given.");
    }
  }

  @Test
  void CmdLineExceptions_MissingCommandException17() {
    Assertions.assertThrows(CmdLineExceptions.MissingCommandException.class, () -> {
      parserNoDisplayHasSortByPriority.processArgs(argsNoDisplayHasSortByPriority);
      parserNoDisplayHasSortByPriority.validateArgs();
    });
  }

  @Test
  void validateArgs18_Success() {
    try {
      parser1.processArgs(args1);
      parser1.validateArgs();
    } catch (CmdLineExceptions.MissingCommandException e) {
      fail("--sort-by-date or --sort-by-priority was given.");
    }
  }

  @Test
  void CmdLineExceptions_InvalidArgumentsException18() {
    Assertions.assertThrows(CmdLineExceptions.InvalidArgumentsException.class, () -> {
      parserHasDisplayHasSortByDateHasSortByPriority.processArgs(
          argsHasDisplayHasSortByDateHasSortByPriority);
      parserHasDisplayHasSortByDateHasSortByPriority.validateArgs();
    });
  }

  @Test
  void createToDo() {
    parser1.processArgs(args1);
    parser1.validateArgs();
    ToDo todo = parser1.createToDo();
    assertTrue(todo.getCompleted());
    assertEquals(todo.getDue(), "2022-01-23");
    assertEquals(todo.getPriority(), 1);
    assertEquals(todo.getCategory(), "eat");
  }

  @Test
  void getHasCsvFilePath() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasCsvFilePath());
  }

  @Test
  void getCsvFilePath() {
    parser1.processArgs(args1);
    assertEquals(parser1.getCsvFilePath(), "src/test/resources/my_todo_parser_test.csv");
  }

  @Test
  void getHasAddTodo() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasAddTodo());
  }

  @Test
  void getHasToDoText() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasToDoText());
  }

  @Test
  void getHasToDoDescription() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasToDoDescription());
  }

  @Test
  void getTodoDescription() {
    parser1.processArgs(args1);
    assertEquals(parser1.getTodoDescription(), "eat apple");
  }

  @Test
  void getHasCompleted() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasCompleted());
  }

  @Test
  void getHasDue() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasDue());
  }

  @Test
  void getHasDueDate() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasDueDate());
  }

  @Test
  void getDueDate() {
    parser1.processArgs(args1);
    assertEquals(parser1.getDueDate(), "2022-01-23");
  }

  @Test
  void getHasPriority() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasPriority());
  }

  @Test
  void getHasPriorityNum() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasPriorityNum());
  }

  @Test
  void getPriorityNum() {
    parser1.processArgs(args1);
    assertEquals(parser1.getPriorityNum(), "1");
  }

  @Test
  void getHasCategory() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasCategory());
  }

  @Test
  void getHasCategoryName() {
    parser1.processArgs(args1);
    assertTrue(parser1.getHasCategoryName());
  }

  @Test
  void getCategory() {
    parser1.processArgs(args1);
    assertEquals(parser1.getCategory(), "eat");
  }

  @Test
  void getHasCompletedTodo() {
    parser1.processArgs(args1);
    parser2.processArgs(args2);
    parser3.processArgs(args3);
    assertTrue(parser3.getHasCompletedTodo());
  }

  @Test
  void getHasCompletedToDoId() {
    parser1.processArgs(args1);
    parser2.processArgs(args2);
    parser3.processArgs(args3);
    assertTrue(parser3.getHasCompletedToDoId());
  }

  @Test
  void getCompletedToDoId() {
    parser1.processArgs(args1);
    parser2.processArgs(args2);
    parser3.processArgs(args3);
    List<Integer> ids = new ArrayList<>();
    ids.add(2);
    assertEquals(parser3.getCompletedToDoId(), ids);
  }

  @Test
  void getHasDisplay() {
    parser1.processArgs(args1);
    parser2.processArgs(args2);
    parser4.processArgs(args4);
    assertTrue(parser4.getHasDisplay());
  }

  @Test
  void getHasShowIncomplete() {
    parser1.processArgs(args1);
    parser2.processArgs(args2);
    parser4.processArgs(args4);
    assertTrue(parser4.getHasShowIncomplete());
  }

  @Test
  void getHasShowCategory() {
    parser1.processArgs(args1);
    parser2.processArgs(args2);
    parser4.processArgs(args4);
    assertTrue(parser4.getHasShowCategory());
  }

  @Test
  void getHasShowCategoryName() {
    parser1.processArgs(args1);
    parser2.processArgs(args2);
    parser4.processArgs(args4);
    assertTrue(parser4.getHasShowCategoryName());
  }

  @Test
  void getShowCategoryName() {
    parser1.processArgs(args1);
    parser2.processArgs(args2);
    parser4.processArgs(args4);
    assertEquals(parser4.getShowCategoryName(), "eat");
  }

  @Test
  void getHasSortByDate() {
    parser1.processArgs(args1);
    parser2.processArgs(args2);
    parser4.processArgs(args4);
    assertTrue(parser4.getHasSortByDate());
  }

  @Test
  void getHasSortByPriority() {
    parser1.processArgs(args1);
    parser2.processArgs(args2);
    parser5.processArgs(args5);
    assertTrue(parser5.getHasSortByPriority());
  }

  @Test
  void testEquals_SameMemoryLocation() {
    assertTrue(parser1.equals(parser1));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(parser1.equals(null));
  }

  @Test
  void testEquals_DifferentDataTypes() {
    DisplayList displayList = new DisplayList();
    assertFalse(parser1.equals(displayList));
  }

  @Test
  void testEquals_DifferentArgs1() {
    parser1.processArgs(args1);
    CmdLineParser testParser = new CmdLineParser();
    String[] testArgs = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--todo-text",
        "eat", "apple"};
    testParser.processArgs(testArgs);
    assertFalse(parser1.equals(testParser));
  }

  @Test
  void testEquals_DifferentArgs2() {
    parser1.processArgs(args1);
    CmdLineParser testParser = new CmdLineParser();
    String[] testArgs = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--todo-text",
        "eat", "apple", "--completed"};
    testParser.processArgs(testArgs);
    assertFalse(parser1.equals(testParser));
  }

  @Test
  void testEquals_DifferentArgs3() {
    parser1.processArgs(args1);
    CmdLineParser testParser = new CmdLineParser();
    String[] testArgs = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--todo-text",
        "eat", "apple", "--completed", "--due", "2022-01-23"};
    testParser.processArgs(testArgs);
    assertFalse(parser1.equals(testParser));
  }

  @Test
  void testEquals_DifferentArgs4() {
    parser1.processArgs(args1);
    CmdLineParser testParser = new CmdLineParser();
    String[] testArgs = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--todo-text",
        "eat", "apple", "--completed", "--due", "2022-01-23", "--priority", "1"};
    testParser.processArgs(testArgs);
    assertFalse(parser1.equals(testParser));
  }

  @Test
  void testEquals_DifferentArgs5() {
    parser1.processArgs(args1);
    CmdLineParser testParser = new CmdLineParser();
    String[] testArgs = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--add-todo", "--todo-text",
        "eat", "apple", "--completed", "--due", "2022-01-23", "--priority", "1", "--category",
        "work"};
    testParser.processArgs(testArgs);
    assertFalse(parser1.equals(testParser));
  }

  @Test
  void testEquals_DifferentArgs6() {
    parser3.processArgs(args3);
    CmdLineParser testParser = new CmdLineParser();
    String[] testArgs = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--complete-todo", "1"};
    testParser.processArgs(testArgs);
    assertFalse(parser3.equals(testParser));
  }

  @Test
  void testEquals_DifferentArgs7() {
    parser4.processArgs(args4);
    CmdLineParser testParser = new CmdLineParser();
    String[] testArgs = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--display", "--show-incomplete"
        , "--show-category", "eat"};
    testParser.processArgs(testArgs);
    assertFalse(parser4.equals(testParser));
  }

  @Test
  void testEquals_DifferentArgs8() {
    parser5.processArgs(args5);
    CmdLineParser testParser = new CmdLineParser();
    String[] testArgs = new String[]{
        "--csv-file", "src/test/resources/my_todo_parser_test.csv", "--display", "--show-incomplete"
        , "--show-category", "eat"};
    testParser.processArgs(testArgs);
    assertFalse(parser5.equals(testParser));
  }

  @Test
  void testToString() {
    String str = "CmdLineParser{hasCsvFile=false, hasCsvFilePath=false, csvFilePath='null', "
        + "hasAddTodo=false, hasToDoText=false, hasToDoDescription=false, todoDescription='', "
        + "hasCompleted=false, hasDue=false, hasDueDate=false, dueDate='null', hasPriority=false, "
        + "hasPriorityNum=false, priorityNum='null', hasCategory=false, hasCategoryName=false, "
        + "category='', hasCompletedTodo=false, hasCompletedToDoId=false, completedToDoId=[], "
        + "hasDisplay=false, hasShowIncomplete=false, hasShowCategory=false, "
        + "hasShowCategoryName=false, showCategoryName='', hasSortByDate=false, "
        + "hasSortByPriority=false}";
    assertEquals(parser1.toString(), str);
  }
}