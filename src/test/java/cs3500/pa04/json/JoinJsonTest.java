package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.enumerations.GameType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JoinJsonTest {
  JoinJson test;

  @BeforeEach
  public void init() {
    test = new JoinJson("Hi", GameType.MULTI.getGameType());
  }

  @Test
  public void testJoinJson() {
    assertEquals(test, test);
  }

}