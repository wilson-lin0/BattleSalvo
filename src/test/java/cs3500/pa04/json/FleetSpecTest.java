package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.Ship;
import cs3500.pa04.enumerations.ShipType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FleetSpecTest {
  FleetSpec test;
  Map<ShipType, Integer> testMap;

  @BeforeEach
  public void init() {
    test = new FleetSpec(1, 1, 1, 1);
    testMap = new HashMap<>();
    testMap.put(ShipType.B, 1);
    testMap.put(ShipType.C, 1);
    testMap.put(ShipType.D, 1);
    testMap.put(ShipType.S, 1);
  }

  @Test
  public void testGetSpecifications() {
    assertEquals(testMap, test.getSpecifications());
  }

}