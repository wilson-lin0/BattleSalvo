package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa04.enumerations.GameResult;
import cs3500.pa04.enumerations.ShipType;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsolePlayerTest {
  View view;
  ConsolePlayer testPlayer0;
  ConsolePlayer testPlayer1;
  ConsolePlayer testPlayer2;
  ConsolePlayer testPlayer3;
  ConsolePlayer testPlayer4;

  Map<ShipType, Integer> specifications;
  Map<ShipType, Integer> specifications10;

  LocalController playerLocalController;
  LocalController badNegativeX;
  LocalController badNegativeY;
  LocalController badBoundsX;
  LocalController badBoundsY;

  Coord coordinate0;
  Coord coordinate1;
  Coord coordinate2;
  Coord coordinate00;
  Coord coordinate01;
  Coord coordinate02;
  Coord coordinate10;
  Coord coordinate11;
  Coord coordinate12;
  Coord coordinate20;
  Coord coordinate21;
  Coord coordinate22;

  List<Coord> coordList;
  List<Coord> coordList1;

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void init() {
    view = new View();

    playerLocalController = new LocalController(new StringReader("0 2\n0 1\n0 3\n0 4"), view);
    badNegativeX = new LocalController(new StringReader("-1 0\n-2 1\n5 5\n4 5"), view);
    badNegativeY = new LocalController(new StringReader("1 -1\n3 -2\n0 0\n-3 4"), view);
    badBoundsX = new LocalController(new StringReader("10 3\n3 4\n5 6\n3 5"), view);
    badBoundsY = new LocalController(new StringReader("3 10\n2 15\n3 4\n1 2"), view);

    testPlayer0 = new ConsolePlayer("Daniel", playerLocalController);
    testPlayer1 = new ConsolePlayer("Emma", badNegativeX);
    testPlayer2 = new ConsolePlayer("Elle", badNegativeY);
    testPlayer3 = new ConsolePlayer("Bob", badBoundsX);
    testPlayer4 = new ConsolePlayer("Bill", badBoundsY);

    specifications = new HashMap<>();
    specifications.put(ShipType.S, 1);
    specifications.put(ShipType.D, 1);
    specifications.put(ShipType.B, 1);
    specifications.put(ShipType.C, 1);

    specifications10 = new HashMap<>();
    specifications10.put(ShipType.S, 1);

    coordinate00 = new Coord(0, 0);
    coordinate01 = new Coord(0, 1);
    coordinate02 = new Coord(0, 2);
    coordinate10 = new Coord(1, 0);
    coordinate11 = new Coord(1, 1);
    coordinate12 = new Coord(1, 2);
    coordinate20 = new Coord(2, 0);
    coordinate21 = new Coord(2, 1);
    coordinate22 = new Coord(2, 2);

    coordinate0 = new Coord(0, 0);
    coordinate1 = new Coord(0, 1);
    coordinate2 = new Coord(5, 5);

    coordList = new ArrayList<>();
    coordList.add(coordinate0);
    coordList.add(coordinate1);
    coordList.add(coordinate2);

    coordList1 = new ArrayList<>();
    coordList1.add(coordinate00);
    coordList1.add(coordinate01);
    coordList1.add(coordinate02);
    coordList1.add(coordinate10);
    coordList1.add(coordinate11);
    coordList1.add(coordinate12);
    coordList1.add(coordinate20);
    coordList1.add(coordinate21);
    coordList1.add(coordinate22);

    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  @Test
  public void testName() {
    assertEquals("Daniel", testPlayer0.name());
  }

  @Test
  public void testSetup() {
    assertEquals(4, testPlayer0.setup(6, 6, specifications).size());
  }

  @Test
  public void testTakeShots() {
    testPlayer0.setup(6, 6, specifications);
    assertEquals(4, testPlayer0.takeShots().size());

    testPlayer1.setup(6, 6, specifications);
    assertThrows(IllegalArgumentException.class, () -> testPlayer1.takeShots());

    testPlayer2.setup(6, 6, specifications);
    assertThrows(IllegalArgumentException.class, () -> testPlayer2.takeShots());

    testPlayer3.setup(6, 6, specifications);
    assertThrows(IllegalArgumentException.class, () -> testPlayer3.takeShots());

    testPlayer4.setup(6, 6, specifications);
    assertThrows(IllegalArgumentException.class, () -> testPlayer4.takeShots());
  }

  @Test
  public void testReportDamage() {
    testPlayer0.setup(3, 3, specifications10);
    assertEquals(3, testPlayer0.reportDamage(coordList1).size());
  }

  @Test
  public void testSuccessfulHits() {
    testPlayer0.setup(6, 6, specifications);
    testPlayer0.successfulHits(coordList);
    assertEquals('H', testPlayer0.returnCoveredBoard().getCoord(coordinate0));
  }

  @Test
  public void testEndGameWin() {
    testPlayer0.endGame(GameResult.WIN, "You hit all the ships!");
    assertEquals("You won!\nYou hit all the ships!", outputStreamCaptor.toString()
        .trim());
  }

  @Test
  public void testEndGameLost() {
    testPlayer0.endGame(GameResult.LOSE, "You lost all the ships!");
    assertEquals("You lost!\nYou lost all the ships!", outputStreamCaptor.toString()
        .trim());
  }

  @Test
  public void testEndGameDraw() {
    testPlayer0.endGame(GameResult.DRAW, "Ships on both sides are down!");
    assertEquals("You drew!\nShips on both sides are down!", outputStreamCaptor.toString()
        .trim());
  }

  @Test
  public void testReturnPlayerBoard() {
    assertNull(testPlayer0.returnPlayerBoard());
    testPlayer0.setup(6, 6, specifications);
    assertNotNull(testPlayer0.returnPlayerBoard());
  }

  @Test
  public void testReturnCoveredBoard() {
    assertNull(testPlayer0.returnCoveredBoard());
    testPlayer0.setup(6, 6, specifications);
    assertNotNull(testPlayer0.returnPlayerBoard());
  }
}