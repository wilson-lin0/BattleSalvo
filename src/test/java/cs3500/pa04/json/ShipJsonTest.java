package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.enumerations.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipJsonTest {
  ShipJson test;
  CoordJson testCoord;

  @BeforeEach
  public void init() {
    testCoord = new CoordJson(3, 3);
    test = new ShipJson(testCoord, 3, Direction.HORIZONTAL);
  }

  @Test
  public void testReturnCoord() {
    assertEquals(testCoord, test.returnCoord());
  }

  @Test
  public void testReturnLength() {
    assertEquals(3, test.returnLength());
  }

  @Test
  public void testReturnDirection() {
    assertEquals(Direction.HORIZONTAL, test.returnDirection());
  }

}