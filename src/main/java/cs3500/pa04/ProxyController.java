package cs3500.pa04;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.enumerations.Direction;
import cs3500.pa04.enumerations.GameResult;
import cs3500.pa04.enumerations.GameType;
import cs3500.pa04.enumerations.ShipType;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.CoordinatesJson;
import cs3500.pa04.json.EndGameJson;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.FleetSpec;
import cs3500.pa04.json.JoinJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.ServerSetupJson;
import cs3500.pa04.json.ShipJson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controls the flow of BattleSalvo
 */
public class ProxyController implements Controller {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final AiOpponent player;
  private final ObjectMapper mapper = new ObjectMapper();
  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");


  /**
   * Construct an instance of a ProxyPlayer.
   *
   * @param server the socket connection to the server
   * @param player the instance of the player
   * @throws IOException if
   */
  public ProxyController(Socket server, AiOpponent player) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = player;
  }

  /**
   * Starts the game of BattleSalvo.
   */
  @Override
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);
      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // Does not throw an error because server is always right and so we can run the tests without
      // having to close the server when calling controller.run()
    }
  }

  private void delegateMessage(MessageJson message) {
    String name = message.returnMethodName();

    if ("join".equals(name)) {
      handleJoin();
    } else if ("setup".equals(name)) {
      JsonNode arguments = message.arguments();
      handleSetup(arguments);
    } else if ("take-shots".equals(name)) {
      handleTakeShots();
    } else if ("report-damage".equals(name)) {
      JsonNode arguments = message.arguments();
      handleReportDamage(arguments);
    } else if ("successful-hits".equals(name)) {
      JsonNode arguments = message.arguments();
      handleSuccessfulHits(arguments);
    } else if ("end-game".equals(name)) {
      JsonNode arguments = message.arguments();
      handleEndGame(arguments);
    }
  }

  private void handleJoin() {
    String name = "jea-e";
    GameType gameType = GameType.SINGLE;

    JoinJson joinJson = new JoinJson(name, gameType.getGameType());
    JsonNode jsonResponse = JsonUtils.serializeRecord(joinJson);
    MessageJson messageJson = new MessageJson("join", jsonResponse);
    this.out.println(JsonUtils.serializeRecord(messageJson));
  }

  private void handleSetup(JsonNode arguments) {
    ServerSetupJson setup = this.mapper.convertValue(arguments, ServerSetupJson.class);

    List<Ship> listOfShips = getPlayerSetup(setup);
    int amountOfShips = listOfShips.size();
    ShipJson[] listOfShipsJsons = new ShipJson[amountOfShips];
    for (int i = 0; i < amountOfShips; i++) {
      List<Coord> coords = listOfShips.get(i).getLocation();
      Coord startingCoord = coords.get(0);
      CoordJson cj = new CoordJson(startingCoord.returnX(), startingCoord.returnY());
      int length = listOfShips.get(i).getSize();
      Direction direction = listOfShips.get(i).getDirection();
      ShipJson shipJson = new ShipJson(cj, length, direction);
      listOfShipsJsons[i] = shipJson;
    }

    FleetJson fleetJson = new FleetJson(listOfShipsJsons);
    JsonNode jsonResponse = JsonUtils.serializeRecord(fleetJson);
    MessageJson setupResponse = new MessageJson("setup", jsonResponse);
    this.out.println(JsonUtils.serializeRecord(setupResponse));
  }

  private List<Ship> getPlayerSetup(ServerSetupJson setup) {
    int width = setup.getWidth();
    int height = setup.getHeight();
    FleetSpec fleetSpec = setup.getFleetSpec();
    Map<ShipType, Integer> specifications = fleetSpec.getSpecifications();

    return player.setup(height, width, specifications);
  }


  private void handleTakeShots() {
    List<Coord> takeShots = player.takeShots();
    CoordJson[] listOfCoord = new CoordJson[takeShots.size()];

    for (int i = 0; i < takeShots.size(); i++) {
      int x = takeShots.get(i).returnX();
      int y = takeShots.get(i).returnY();
      CoordJson coordJson = new CoordJson(x, y);
      listOfCoord[i] = coordJson;
    }

    CoordinatesJson coordinates = new CoordinatesJson(listOfCoord);
    JsonNode jsonResponse = JsonUtils.serializeRecord(coordinates);
    MessageJson takeShotResponse = new MessageJson("take-shots", jsonResponse);
    this.out.println(JsonUtils.serializeRecord(takeShotResponse));
  }

  private void handleReportDamage(JsonNode arguments) {
    CoordinatesJson coordinatesJson = this.mapper.convertValue(arguments, CoordinatesJson.class);
    CoordJson[] coordJsons = coordinatesJson.getListOfCoord();
    List<Coord> enemyShots = new ArrayList<>();
    for (CoordJson cj : coordJsons) {
      int x = cj.returnX();
      int y = cj.returnY();
      Coord c = new Coord(x, y);
      enemyShots.add(c);
    }
    List<Coord> reportDamage = player.reportDamage(enemyShots);

    coordJsons = new CoordJson[reportDamage.size()];
    for (int i = 0; i < reportDamage.size(); i++) {
      CoordJson cj = new CoordJson(reportDamage.get(i).returnX(), reportDamage.get(i).returnY());
      coordJsons[i] = cj;
    }

    coordinatesJson = new CoordinatesJson(coordJsons);
    JsonNode jsonResponse = JsonUtils.serializeRecord(coordinatesJson);
    MessageJson reportDamageResponse = new MessageJson("report-damage", jsonResponse);
    this.out.println(JsonUtils.serializeRecord(reportDamageResponse));
  }

  private void handleSuccessfulHits(JsonNode arguments) {
    CoordinatesJson coordinatesJson = this.mapper.convertValue(arguments, CoordinatesJson.class);
    CoordJson[] coordJsons = coordinatesJson.getListOfCoord();
    List<Coord> myShots = new ArrayList<>();
    for (CoordJson cj : coordJsons) {
      int x = cj.returnX();
      int y = cj.returnY();
      Coord c = new Coord(x, y);
      myShots.add(c);
    }
    player.successfulHits(myShots);
    MessageJson reportDamageResponse = new MessageJson("successful-hits",
        VOID_RESPONSE);
    this.out.println(JsonUtils.serializeRecord(reportDamageResponse));
  }

  private void handleEndGame(JsonNode arguments) {
    EndGameJson endGameJson = this.mapper.convertValue(arguments, EndGameJson.class);
    GameResult result = endGameJson.returnResult();
    String reason = endGameJson.reason();
    this.player.endGame(result, reason);

    MessageJson reportDamageResponse = new MessageJson("end-game",
        VOID_RESPONSE);
    this.out.println(JsonUtils.serializeRecord(reportDamageResponse));

    try {
      server.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}