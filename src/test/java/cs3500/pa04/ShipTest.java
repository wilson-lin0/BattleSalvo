package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.Enumerations.ShipType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {
  Coord coordinate0;
  Coord coordinate1;
  Coord coordinate2;
  Coord coordinate3;

  List<Coord> submarineCoordinate;

  Ship submarine;

  @BeforeEach
  public void init() {
    coordinate0 = new Coord(0, 1);
    coordinate1 = new Coord(0, 2);
    coordinate2 = new Coord(0, 3);
    coordinate3 = new Coord(0, 4);

    submarineCoordinate = new ArrayList<>();
    submarineCoordinate.add(coordinate0);
    submarineCoordinate.add(coordinate1);
    submarineCoordinate.add(coordinate2);

    submarine = new Ship(3, ShipType.S, submarineCoordinate);
  }

  @Test
  public void testGetSize() {
    assertEquals(3, submarine.getSize());
  }

  @Test
  public void testGetType() {
    assertEquals(ShipType.S, submarine.getType());
  }

  @Test
  public void testGetLocation() {
    assertEquals(submarineCoordinate, submarine.getLocation());
  }

  @Test
  public void testTakeHit() {
    // shot on ship
    assertEquals(2, submarine.takeHit(coordinate0));
    // shot missed
    assertEquals(-1, submarine.takeHit(coordinate3));
  }
}