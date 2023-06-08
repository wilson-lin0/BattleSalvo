package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.Enumerations.GameResult;
import org.junit.jupiter.api.Test;

class GameResultTest {
  @Test
  public void testGetResult() {
    assertEquals("Win", GameResult.WIN.getResult());
    assertEquals("Lose", GameResult.LOSE.getResult());
    assertEquals("Draw", GameResult.DRAW.getResult());
  }
}