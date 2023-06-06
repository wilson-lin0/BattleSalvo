package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViewTest {
  Board playerBoard;
  View view;
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


  @BeforeEach
  public void init() {
    view = new View();
    playerBoard = new Board(2, 2);
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  @Test
  public void testDisplayBoard() {
    view.displayBoard(playerBoard);
    assertEquals("OO\nOO", outputStreamCaptor.toString().trim());
  }

  @Test
  public void testPrintMessage() {
    view.printMessage("Hello");
    assertEquals("Hello", outputStreamCaptor.toString().trim());
  }
}