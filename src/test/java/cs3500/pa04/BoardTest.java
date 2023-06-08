package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.enumerations.ShipType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
  Coord coordinate0;
  Coord coordinate1;
  Coord coordinate2;
  Coord coordinate3;
  Coord coordinate4;
  Coord coordinate5;
  Coord badNegativeX;
  Coord badBoundX;
  Coord badNegativeY;
  Coord badBoundY;
  List<Coord> listOfCoord1;
  List<Coord> listOfCoord2;
  List<Coord> listOfCoord3;
  Ship testShip1;
  Ship testShip2;
  List<Ship> listOfShip;
  Board testBoard0;
  Board testBoard1;
  Board testBoard2;

  @BeforeEach
  public void init() {
    coordinate0 = new Coord(0, 0);
    coordinate1 = new Coord(0, 1);
    coordinate2 = new Coord(0, 2);
    coordinate3 = new Coord(0, 3);
    coordinate4 = new Coord(0, 4);
    coordinate5 = new Coord(1, 0);
    badNegativeX = new Coord(-1, 0);
    badBoundX = new Coord(20, 0);
    badNegativeY = new Coord(0, -1);
    badBoundY = new Coord(0, 20);

    listOfCoord1 = new ArrayList<>();
    listOfCoord1.add(coordinate0);
    listOfCoord1.add(coordinate1);

    listOfCoord2 = new ArrayList<>();
    listOfCoord2.add(coordinate2);

    listOfCoord3 = new ArrayList<>();
    listOfCoord3.add(coordinate0);
    listOfCoord3.add(coordinate5);

    testShip1 = new Ship(2, ShipType.B, listOfCoord1);
    testShip2 = new Ship(1, ShipType.C, listOfCoord2);

    listOfShip = new ArrayList<>();
    listOfShip.add(testShip1);
    listOfShip.add(testShip2);

    testBoard0 = new Board(4, 4);
    testBoard1 = new Board(2, 3);
    testBoard2 = new Board(2, 2);
  }

  @Test
  public void testUpdatePosition() {
    assertThrows(IllegalArgumentException.class,
        () -> testBoard0.updatePosition(badBoundX, 'C'));
    assertThrows(IllegalArgumentException.class,
        () -> testBoard0.updatePosition(badNegativeX, 'C'));
    assertThrows(IllegalArgumentException.class,
        () -> testBoard0.updatePosition(badBoundY, 'C'));
    assertThrows(IllegalArgumentException.class,
        () -> testBoard0.updatePosition(badNegativeY, 'C'));

    testBoard0.updatePosition(coordinate0, 'C');
    assertEquals('C', testBoard0.getCoord(coordinate0));
  }

  @Test
  public void testGetCoord() {
    assertEquals('O', testBoard0.getCoord(coordinate0));
    assertThrows(IllegalArgumentException.class, () -> testBoard0.getCoord(badBoundX));
    assertThrows(IllegalArgumentException.class, () -> testBoard0.getCoord(badNegativeX));
    assertThrows(IllegalArgumentException.class, () -> testBoard0.getCoord(badBoundY));
    assertThrows(IllegalArgumentException.class, () -> testBoard0.getCoord(badNegativeY));
  }

  @Test
  public void testTakeHit() {
    assertThrows(IllegalArgumentException.class, () -> testBoard0.takeHit(badBoundX));
    assertThrows(IllegalArgumentException.class, () -> testBoard0.takeHit(badNegativeX));
    assertThrows(IllegalArgumentException.class, () -> testBoard0.takeHit(badBoundY));
    assertThrows(IllegalArgumentException.class, () -> testBoard0.takeHit(badNegativeY));

    testBoard0.placeShip(testShip1);
    testBoard0.placeShip(testShip2);

    assertTrue(testBoard0.takeHit(coordinate0));
    assertFalse(testBoard0.takeHit(coordinate3));
    assertTrue(testBoard0.takeHit(coordinate1));
    assertFalse(testBoard0.takeHit(coordinate0));
    assertTrue(testBoard0.takeHit(coordinate2));
  }

  @Test
  public void testFindEmptySpace() {
    assertEquals(3, testBoard0.findEmptySpace(3).size());
  }

  @Test
  public void testPlaceShip() {
    testBoard0.placeShip(testShip1);
    assertEquals('B', testBoard0.getCoord(coordinate0));
    assertEquals('B', testBoard0.getCoord(coordinate1));
  }

  @Test
  public void testNumOfAliveShips() {
    assertEquals(0, testBoard0.numOfAliveShips());
  }

  @Test
  public void testReturnHeight() {
    assertEquals(2, testBoard2.returnHeight());
  }

  @Test
  public void testReturnWidth() {
    assertEquals(2, testBoard2.returnWidth());
  }

  @Test
  public void testReturnBoard() {
    assertNotNull(testBoard0.returnBoard());
  }
}