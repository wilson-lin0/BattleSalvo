package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.enumerations.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EndGameJsonTest {
  EndGameJson test;

  @BeforeEach
  public void init() {
    test = new EndGameJson(GameResult.WIN, "Ships sunk");
  }

  @Test
  public void testReturnResult() {
    assertEquals(GameResult.WIN, test.returnResult());
  }

  @Test
  public void testReturnReason() {
    assertEquals("Ships sunk", test.returnReason());
  }
}