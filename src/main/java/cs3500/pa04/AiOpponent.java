package cs3500.pa04;

import java.util.ArrayList;
import java.util.List;

/**
 * The opponent.
 */
public class AiOpponent extends AbstractPlayer {

  AiOpponent(String name) {
    super(name);
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
    List<Coord> locations = new ArrayList<>();
    for (int j = 0; j < this.coveredBoard.returnHeight() && shotsAvailable > 0; j++) {
      for (int k = 0; k < this.coveredBoard.returnWidth() && shotsAvailable > 0; k++) {
        if (this.coveredBoard.getCoord(new Coord(k, j)) == 'O') {
          locations.add(new Coord(k, j));
          this.coveredBoard.takeHit(new Coord(k, j));
          shotsAvailable--;
        }
      }
    }
    return locations;
  }
}
