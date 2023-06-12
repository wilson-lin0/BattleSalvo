package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServerSetupJsonTest {
  ServerSetupJson test;
  FleetSpec testFleet;

  @BeforeEach
  public void init() {
    testFleet = new FleetSpec(1, 1, 1, 1);
    test = new ServerSetupJson(1, 1, testFleet);
  }

  @Test
  public void testGetWidth() {
    assertEquals(1, test.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(1, test.getHeight());
  }

  @Test
  public void testGetFleetSpec() {
    assertEquals(testFleet, test.getFleetSpec());
  }

}