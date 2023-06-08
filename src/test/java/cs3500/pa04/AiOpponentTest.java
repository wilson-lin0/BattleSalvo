package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import cs3500.pa04.enumerations.GameResult;
import cs3500.pa04.enumerations.ShipType;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AiOpponentTest {
  AiOpponent testPlayer0;
  AiOpponent testPlayer1;
  AiOpponent testPlayer2;
  AiOpponent testPlayer3;
  AiOpponent testPlayer4;
  Map<ShipType, Integer> specifications;
  Map<ShipType, Integer> specifications1;
  Coord coordinate0;
  Coord coordinate1;
  Coord coordinate2;
  Coord coordinate3;
  Coord coordinate4;
  Coord coordinate5;
  Coord coordinate6;
  Coord coordinate7;
  Coord coordinate8;
  Coord coordinate9;
  List<Coord> coordList;
  List<Coord> coordList1;
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void init() {
    testPlayer0 = new AiOpponent("Daniel");
    testPlayer1 = new AiOpponent("Emma");
    testPlayer2 = new AiOpponent("Elle");
    testPlayer3 = new AiOpponent("Bob");
    testPlayer4 = new AiOpponent("Bill");

    specifications = new HashMap<>();
    specifications.put(ShipType.S, 1);
    specifications.put(ShipType.D, 1);
    specifications.put(ShipType.B, 1);
    specifications.put(ShipType.C, 1);

    specifications1 = new HashMap<>();
    specifications1.put(ShipType.S, 1);

    coordinate0 = new Coord(0, 0);
    coordinate1 = new Coord(0, 1);
    coordinate2 = new Coord(5, 5);
    coordinate3 = new Coord(0, 2);
    coordinate4 = new Coord(1, 0);
    coordinate5 = new Coord(1, 1);
    coordinate6 = new Coord(1, 2);
    coordinate7 = new Coord(2, 0);
    coordinate8 = new Coord(2, 1);
    coordinate9 = new Coord(2, 2);

    coordList = new ArrayList<>();
    coordList.add(coordinate0);
    coordList.add(coordinate1);
    coordList.add(coordinate2);

    coordList1 = new ArrayList<>();
    coordList1.add(coordinate0);
    coordList1.add(coordinate1);
    coordList1.add(coordinate3);
    coordList1.add(coordinate4);
    coordList1.add(coordinate5);
    coordList1.add(coordinate6);
    coordList1.add(coordinate7);
    coordList1.add(coordinate8);
    coordList1.add(coordinate9);

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
    testPlayer1.setup(3, 3, specifications1);
    testPlayer1.successfulHits(coordList1);
    assertEquals(0, testPlayer1.takeShots().size());
  }

  @Test
  public void testReportDamage() {
    testPlayer1.setup(3, 3, specifications1);
    testPlayer1.successfulHits(coordList1);
    assertEquals(3, testPlayer1.reportDamage(coordList1).size());
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