package cs3500.pa04;

import java.net.Socket;
import java.util.List;

public class ProxyController implements Controller {
  private Socket input;
  private Player player;
  private View view;

  ProxyController(Socket input, Player player, View view) {
    this.input = input;
    this.player = player;
    this.view = view;
  }

  /**
   * Welcomes the player to BattleSalvo.
   */
  private void welcome() {
    view.printMessage("Welcome to BattleSalvo!");
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height the height of the board, range: [6, 15] inclusive
   * @param width the width of the board, range: [6, 15] inclusive
   * @param fleet the fleet of ships
   * @return the placements of each ship on the board
   */
  public List<Ship> setup(int width, int height, FleetJson fleet) {
    return null;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  public List<Coord> takeShots() {
    return null;
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *     ship on this board
   */
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return null;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
  }

  /**
   * Asks the player for shots.
   *
   * @param shotsAvailable the number of shots available
   * @return List the list of coordinates of the shots
   */
  @Override
  public List<Coord> askForShots(int shotsAvailable) {
    return null;
  }

  /**
   * Starts the game of BattleSalvo.
   */
  @Override
  public void run() {

  }
}
