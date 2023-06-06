package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordTest {
  Coord coordinate1;
  Coord coordinate2;
  Coord coordinate3;
  Coord coordinate4;

  @BeforeEach
  public void init() {
    coordinate1 = new Coord(5, 10);
    coordinate2 = new Coord(5, 10);
    coordinate3 = new Coord(10, 15);
    coordinate4 = new Coord(5, 15);
  }

  @Test
  public void testReturnX() {
    assertEquals(5, coordinate1.returnX());
  }

  @Test
  public void testReturnY() {
    assertEquals(10, coordinate1.returnY());
  }

  @Test
  public void testIsEqual() {
    assertTrue(coordinate1.isEqual(coordinate2));
    assertFalse(coordinate1.isEqual(coordinate3));
    assertFalse(coordinate1.isEqual(coordinate4));
  }
}