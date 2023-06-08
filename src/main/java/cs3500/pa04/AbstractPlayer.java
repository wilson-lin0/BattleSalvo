package cs3500.pa04;

import cs3500.pa04.enumerations.GameResult;
import cs3500.pa04.enumerations.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * To represent a player in BattleSalvo.
 */
public abstract class AbstractPlayer implements Player {
  protected Board playerBoard;
  protected Board coveredBoard;
  protected String name;

  public AbstractPlayer(String name) {
    this.name = name;
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return this.name;
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height the height of the board, range: [6, 15] inclusive
   * @param width the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    List<Ship> results = new ArrayList<>();
    this.coveredBoard = new Board(width, height);
    this.playerBoard = new Board(width, height);
    for (Map.Entry<ShipType, Integer> entry : specifications.entrySet()) {
      int count = entry.getValue();
      ShipType type = entry.getKey();
      for (int i = 0; i < count; i++) {
        List<Coord> locations = this.playerBoard.findEmptySpace(type.getSize());
        Ship ship = new Ship(type.getSize(), type, locations);
        this.playerBoard.placeShip(ship);
        results.add(ship);
      }
    }
    return results;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public abstract List<Coord> takeShots();

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *     ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> results = new ArrayList<>();
    for (Coord coordinate : opponentShotsOnBoard) {
      if (this.playerBoard.takeHit(coordinate)) {
        results.add(coordinate);
      }
    }
    return results;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    for (Coord coordinate : shotsThatHitOpponentShips) {
      this.coveredBoard.updatePosition(coordinate, 'H');
    }
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    if (result.equals(GameResult.WIN)) {
      System.out.println("You won!");
    } else if (result.equals(GameResult.LOSE)) {
      System.out.println("You lost!");
    } else {
      System.out.println("You drew!");
    }
    System.out.println(reason);
  }

  /**
   * Returns the player's board.
   *
   * @return Board the player's board
   */
  public Board returnPlayerBoard() {
    return this.playerBoard;
  }

  /**
   * Returns the covered board.
   *
   * @return Board the covered board
   */
  public Board returnCoveredBoard() {
    return this.coveredBoard;
  }
}
