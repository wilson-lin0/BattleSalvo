package cs3500.pa04.enumerations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.enumerations.ShipType;
import org.junit.jupiter.api.Test;

class ShipTypeTest {
  @Test
  public void testGetShipName() {
    assertEquals('B', ShipType.B.getShipName());
    assertEquals('C', ShipType.C.getShipName());
    assertEquals('D', ShipType.D.getShipName());
    assertEquals('S', ShipType.S.getShipName());
  }

  @Test
  public void testGetSize() {
    assertEquals(5, ShipType.B.getSize());
    assertEquals(6, ShipType.C.getSize());
    assertEquals(4, ShipType.D.getSize());
    assertEquals(3, ShipType.S.getSize());
  }
}