package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageJsonTest {
  MessageJson test;
  JsonNode testCoord;

  @BeforeEach
  public void init() {
    test = new MessageJson("hello-method", testCoord);
  }

  @Test
  public void testReturnMethodName() {
    assertEquals("hello-method", test.returnMethodName());
  }

  @Test
  public void testReturnArguments() {
    assertEquals(testCoord, test.returnArguments());
  }
}