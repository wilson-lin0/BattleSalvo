package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.enumerations.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FleetJsonTest {
  FleetJson test;
  ShipJson testShip;
  ShipJson[] listOfShips;
  CoordJson testCoord;

  @BeforeEach
  public void init() {
    testCoord = new CoordJson(3, 3);
    testShip = new ShipJson(testCoord, 3, Direction.HORIZONTAL);
    listOfShips = new ShipJson[1];
    listOfShips[0] = testShip;
    test = new FleetJson(listOfShips);
  }

  @Test
  public void testFleet() {
    assertEquals(test, test);
  }
}