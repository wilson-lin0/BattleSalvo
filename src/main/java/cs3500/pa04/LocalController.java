package cs3500.pa04;

import cs3500.pa04.enumerations.GameResult;
import cs3500.pa04.enumerations.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Controls the flow of BattleSalvo.
 */
public class LocalController implements Controller {
  private Readable input;
  private View view;

  LocalController(Readable input, View view) {
    this.input = input;
    this.view = view;
  }

  /**
   * Welcomes the player to BattleSalvo.
   */
  private void welcome() {
    view.printMessage("Welcome to BattleSalvo!");
  }

  /**
   * Asks for the player name.
   *
   * @return String the name
   */
  private String enterName() {
    view.printMessage("Please enter your name: ");
    Scanner scanner = new Scanner(input);
    return scanner.nextLine();
  }

  /**
   * Asks for the width and height of the board as a Coord. Has to be between [6, 15].
   *
   * @return Coord the width as x and height as y
   */
  private Coord enterWidthHeight() {
    Scanner scanner = new Scanner(input);
    int coordX = -1;
    int coordY = -1;
    while (coordX == -1 && coordY == -1) {
      view.printMessage("Please enter the width and height. The dimensions must be in the range"
          + " (6, 15), inclusive.");
      coordX = scanner.nextInt();
      coordY = scanner.nextInt();
      if (coordX < 6 || coordX > 15 || coordY < 6 || coordY > 15) {
        coordX = -1;
        coordY = -1;
        view.printMessage("Invalid input. Please try again.");
      }
    }
    return new Coord(coordX, coordY);
  }

  /**
   * Asks for the fleet in the order [Battleship, Carrier, Destroyer, Submarine]. Fleet cannot
   * exceed the size of the smallest board parameter. Displays max fleet size.
   *
   * @param dimensions the dimensions of the board
   * @return Map of ships
   */
  private Map<ShipType, Integer> enterFleet(Coord dimensions) {
    Scanner scanner = new Scanner(input);
    Map<ShipType, Integer> specifications = new HashMap<>();
    int max = 0;
    if (dimensions.returnX() > dimensions.returnY()) {
      max = dimensions.returnY();
    } else {
      max = dimensions.returnX();
    }
    while (true) {
      int sum = 0;
      view.printMessage("Please enter your fleet in the order [Battleship, Carrier, Destroyer"
          + " Submarine]. Your fleet size may not exceed " + max);
      int battleshipNum = scanner.nextInt();
      int carrierNum = scanner.nextInt();
      int destroyerNum = scanner.nextInt();
      int submarineNum = scanner.nextInt();
      sum = battleshipNum + carrierNum + destroyerNum + submarineNum;
      if (sum <= max && sum >= 1) {
        specifications.put(ShipType.B, battleshipNum);
        specifications.put(ShipType.C, carrierNum);
        specifications.put(ShipType.D, destroyerNum);
        specifications.put(ShipType.S, submarineNum);
        break;
      }
      view.printMessage("Invalid fleet size, please try again.");
    }
    return specifications;
  }

  /**
   * Asks the player for shots.
   *
   * @param shotsAvailable the number of shots available
   * @return List the list of coordinates of the shots
   */
  public List<Coord> askForShots(int shotsAvailable) {
    view.printMessage("Please enter " + shotsAvailable + " shots.");
    List<Coord> locationList = new ArrayList<>();
    Scanner scanner = new Scanner(input);
    for (int i = 0; i < shotsAvailable; i++) {
      int coordX = scanner.nextInt();
      int coordY = scanner.nextInt();
      Coord location = new Coord(coordX, coordY);
      locationList.add(location);
    }
    return locationList;
  }

  /**
   * Starts the game of BattleSalvo.
   */
  public void run() {
    // sets up the game
    welcome();
    ConsolePlayer consolePlayer = new ConsolePlayer(enterName(), this);
    AiOpponent opponent = new AiOpponent("AI");
    Coord widthHeight = enterWidthHeight();
    int width = widthHeight.returnX();
    int height = widthHeight.returnY();
    Map<ShipType, Integer> specifications = enterFleet(widthHeight);
    consolePlayer.setup(height, width, specifications);
    opponent.setup(height, width, specifications);
    View view = new View();

    // plays the game
    while (consolePlayer.returnPlayerBoard().numOfAliveShips() > 0
      && opponent.returnPlayerBoard().numOfAliveShips() > 0) {
      // displays the board
      view.displayBoard(consolePlayer.returnCoveredBoard());
      view.printMessage("\n");
      view.displayBoard(consolePlayer.returnPlayerBoard());
      // take shots
      List<Coord> opponentShots = opponent.takeShots();
      List<Coord> consolePlayerShots;
      while (true) {
        try {
          consolePlayerShots = consolePlayer.takeShots();
          break;
        } catch (IllegalArgumentException e) {
          view.printMessage("Coordinates are out of bounds. Please try again.");
          continue;
        }
      }
      // reports back the shots that hit
      List<Coord> opponentShotsHit = consolePlayer.reportDamage(opponentShots);
      List<Coord> consolePlayerShotsHit = opponent.reportDamage(consolePlayerShots);
      // successful hits reported
      consolePlayer.successfulHits(consolePlayerShotsHit);
      opponent.successfulHits(opponentShotsHit);
    }

    // who won/lost/drew
    if (consolePlayer.returnPlayerBoard().numOfAliveShips()
        > opponent.returnPlayerBoard().numOfAliveShips()) {
      consolePlayer.endGame(GameResult.WIN, "You hit all the opponent's ships!");
    } else if ((consolePlayer.returnPlayerBoard().numOfAliveShips()
        < opponent.returnPlayerBoard().numOfAliveShips())) {
      consolePlayer.endGame(GameResult.LOSE, "All your ships sunk!");
    } else {
      consolePlayer.endGame(GameResult.DRAW, "All ships on both sides sunk!");
    }
  }
}
