package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordJsonTest {
  CoordJson test;

  @BeforeEach
  public void init() {
    test = new CoordJson(3, 3);
  }

  @Test
  public void testReturnX() {
    assertEquals(3, test.returnX());
  }

  @Test
  public void testReturny() {
    assertEquals(3, test.returnY());
  }
}