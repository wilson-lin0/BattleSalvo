package cs3500.pa04.enumerations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DirectionTest {
  @Test
  public void testGetDirection() {
    assertEquals("HORIZONTAL", Direction.HORIZONTAL.getDirection());
    assertEquals("VERTICAL", Direction.VERTICAL.getDirection());
  }
}