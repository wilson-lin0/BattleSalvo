package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.enumerations.GameResult;
import cs3500.pa04.enumerations.ShipType;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.CoordinatesJson;
import cs3500.pa04.json.EndGameJson;
import cs3500.pa04.json.FleetSpec;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.ServerSetupJson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test correct responses for different requests from the socket using a Mock Socket (mocket)
 */
public class ProxyControllerTest {

  private ByteArrayOutputStream testLog;
  private ProxyController controller;
  ObjectMapper objectMapper;
  AiOpponent player;


  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());

    objectMapper = new ObjectMapper();


    player = new AiOpponent("Emily");
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.C, 1);
    specifications.put(ShipType.B, 1);
    specifications.put(ShipType.D, 1);
    specifications.put(ShipType.S, 1);
    player.setup(6, 6, specifications);
  }

  /**
   * Check that the server returns a guess when given a hint.
   */
  @Test
  public void testJoin() {
    MessageJson messageJson = new MessageJson("join", objectMapper.createObjectNode());
    JsonNode joinMessage = JsonUtils.serializeRecord(messageJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(joinMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, new AiOpponent("Emily"));
    } catch (IOException e) {
      fail();
    }

    // run the dealer and verify the response
    this.controller.run();
    responseToClass(MessageJson.class);
  }

  @Test
  public void testSetup() {
    FleetSpec fleetSpec = new FleetSpec(3, 1, 1, 1);
    ServerSetupJson serverSetupJson = new ServerSetupJson(6, 6, fleetSpec);
    JsonNode sampleMessage = JsonUtils.serializeRecord(serverSetupJson);
    MessageJson messageJson = new MessageJson("setup", sampleMessage);
    JsonNode serverMessage = JsonUtils.serializeRecord(messageJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(serverMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, new AiOpponent("Emily"));
    } catch (IOException e) {
      fail();
    }

    // run the dealer and verify the response
    this.controller.run();
    responseToClass(MessageJson.class);
  }

  @Test
  public void testTakeShots() {

    MessageJson messageJson = new MessageJson("take-shots",
        objectMapper.createObjectNode());
    JsonNode takeShotMessage = JsonUtils.serializeRecord(messageJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(takeShotMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, player);
    } catch (IOException e) {
      fail();
    }

    // run the dealer and verify the response
    this.controller.run();
    responseToClass(MessageJson.class);
  }

  @Test
  public void testReportDamage() {
    CoordJson[] coordJsons = new CoordJson[0];
    CoordinatesJson coordinatesJson = new CoordinatesJson(coordJsons);
    JsonNode sampleMessage = JsonUtils.serializeRecord(coordinatesJson);
    MessageJson messageJson = new MessageJson("report-damage", sampleMessage);
    JsonNode serverMessage = JsonUtils.serializeRecord(messageJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(serverMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, player);
    } catch (IOException e) {
      fail();
    }

    // run the dealer and verify the response
    this.controller.run();
    responseToClass(MessageJson.class);
  }

  @Test
  public void testSuccessfulHits() {
    CoordJson[] coordJsons = new CoordJson[0];
    CoordinatesJson coordinatesJson = new CoordinatesJson(coordJsons);
    JsonNode sampleMessage = JsonUtils.serializeRecord(coordinatesJson);
    MessageJson messageJson = new MessageJson("successful-hits", sampleMessage);
    JsonNode serverMessage = JsonUtils.serializeRecord(messageJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(serverMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, player);
    } catch (IOException e) {
      fail();
    }

    // run the dealer and verify the response
    this.controller.run();
    responseToClass(MessageJson.class);
  }

  @Test
  public void testEndGame() {
    EndGameJson endGameJson = new EndGameJson(GameResult.WIN,
        "Player 1 sank all of Player 2's ships");
    JsonNode sampleMessage = JsonUtils.serializeRecord(endGameJson);
    MessageJson messageJson = new MessageJson("end-game", sampleMessage);
    JsonNode serverMessage = JsonUtils.serializeRecord(messageJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(serverMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, new AiOpponent("Emily"));
    } catch (IOException e) {
      fail();
    }

    // run the dealer and verify the response
    this.controller.run();
    String expected = "{\"method-name\":\"end-game\",\"arguments\":\"void\"}";
    assertEquals(expected, logToString());
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8).trim();
  }

  /**
   * Try converting the current test log to a string of a certain class.
   *
   * @param classRef Type to try converting the current test stream to.
   * @param <T>      Type to try converting the current test stream to.
   */
  private <T> void responseToClass(@SuppressWarnings("SameParameterValue") Class<T> classRef) {
    try {
      JsonParser jsonParser = new ObjectMapper().createParser(logToString());
      jsonParser.readValueAs(classRef);
      // No error thrown when parsing to a GuessJson, test passes!
    } catch (IOException e) {
      // Could not read
      // -> exception thrown
      // -> test fails since it must have been the wrong type of response.
      fail();
    }
  }
}