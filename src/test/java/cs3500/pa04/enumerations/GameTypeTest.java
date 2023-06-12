package cs3500.pa04.enumerations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GameTypeTest {
  @Test
  public void testGetGameType() {
    assertEquals("SINGLE", GameType.SINGLE.getGameType());
    assertEquals("MULTI", GameType.MULTI.getGameType());
  }

}