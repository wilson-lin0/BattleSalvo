package cs3500.pa04;

import java.util.List;

/**
 * The player.
 */
public class ConsolePlayer extends AbstractPlayer {
  private LocalController playerLocalController;

  ConsolePlayer(String name, LocalController playerLocalController) {
    super(name);
    this.playerLocalController = playerLocalController;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    int shotsAvailable = this.playerBoard.numOfAliveShips();
    List<Coord> locations = this.playerLocalController.askForShots(shotsAvailable);
    for (Coord coordinate : locations) {
      if (coordinate.returnX() < 0 || coordinate.returnX() > playerBoard.returnWidth()
          || coordinate.returnY() < 0 || coordinate.returnY() > playerBoard.returnHeight()) {
        throw new IllegalArgumentException("Please enter valid shots.");
      }
    }
    for (Coord coord : locations) {
      this.coveredBoard.updatePosition(coord, 'M');
    }
    return locations;
  }
}
