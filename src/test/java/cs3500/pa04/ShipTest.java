package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.enumerations.Direction;
import cs3500.pa04.enumerations.ShipType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {
  Coord coordinate0;
  Coord coordinate1;
  Coord coordinate2;
  Coord coordinate3;
  Coord coordinate4;
  Coord coordinate5;
  Coord coordinate6;

  List<Coord> submarineCoordinate;
  List<Coord> submarineCoordinate2;

  Ship submarine;
  Ship submarine2;

  @BeforeEach
  public void init() {
    coordinate0 = new Coord(0, 1);
    coordinate1 = new Coord(0, 2);
    coordinate2 = new Coord(0, 3);
    coordinate3 = new Coord(0, 4);
    coordinate4 = new Coord(0, 0);
    coordinate5 = new Coord(1, 0);
    coordinate6 = new Coord(2, 0);

    submarineCoordinate = new ArrayList<>();
    submarineCoordinate.add(coordinate0);
    submarineCoordinate.add(coordinate1);
    submarineCoordinate.add(coordinate2);

    submarineCoordinate2 = new ArrayList<>();
    submarineCoordinate2.add(coordinate4);
    submarineCoordinate2.add(coordinate5);
    submarineCoordinate2.add(coordinate6);

    submarine = new Ship(3, ShipType.S, submarineCoordinate);
    submarine2 = new Ship(3, ShipType.S, submarineCoordinate2);
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

  @Test
  public void testGetDirection() {
    assertEquals(Direction.VERTICAL, submarine.getDirection());
    assertEquals(Direction.HORIZONTAL, submarine2.getDirection());
  }
}