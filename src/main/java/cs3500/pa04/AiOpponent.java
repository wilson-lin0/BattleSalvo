package cs3500.pa04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    List<Coord> shotsHit = new ArrayList<>();
    for (int i = 0; i < this.coveredBoard.returnHeight() && shotsAvailable > 0; i++) {
      for (int j = 0; j < this.coveredBoard.returnWidth() && shotsAvailable > 0; j++) {
        if (this.coveredBoard.getCoord(new Coord(j, i)) == 'H') {
          shotsHit.add(new Coord(j, i));
        }
      }
    }

    if (shotsHit.isEmpty()) {
      return getRandomShots(this.playerBoard.numOfAliveShips());
    }

    List<Coord> shotsToFire = new ArrayList<>();
    for (Coord coordinate : shotsHit) {
      Boolean full = false;
      List<Coord> tempShots = new ArrayList<>();
      Coord down = new Coord(coordinate.returnX(), coordinate.returnY() + 1);
      Coord up = new Coord(coordinate.returnX(), coordinate.returnY() - 1);
      Coord left = new Coord(coordinate.returnX() - 1, coordinate.returnY());
      Coord right = new Coord(coordinate.returnX() + 1, coordinate.returnY());

      if (validCoordinate(down)) {
        tempShots.add(down);
      }

      if (validCoordinate(up)) {
        tempShots.add(up);
      }

      if (validCoordinate(left)) {
        tempShots.add(left);
      }

      if (validCoordinate(right)) {
        tempShots.add(right);
      }

      for (Coord coordinates : tempShots) {
        if (this.coveredBoard.getCoord(coordinates) == 'O') {
          shotsToFire.add(coordinates);
          this.coveredBoard.updatePosition(coordinates, 'M');
          if (shotsToFire.size() == 4) {
            full = true;
            break;
          }
        }
      }

      if (full) {
        break;
      }
    }

    return shotsToFire;

  }

  /**
   * Gets a list of random shots on the board.
   *
   * @param limit the maximum amount of shots
   * @return List the list of coordinates
   */
  private List<Coord> getRandomShots(int limit) {
    List<Coord> listOfRandomShots = new ArrayList<>();
    Random randomX = new Random();
    Random randomY = new Random();
    for (int i = 0; i < limit;) {
      int randomCoordX = randomX.nextInt(this.coveredBoard.returnWidth());
      int randomCoordY = randomY.nextInt(this.coveredBoard.returnHeight());
      Coord randomCoord = new Coord(randomCoordX, randomCoordY);
      if (this.coveredBoard.getCoord(randomCoord) == 'O') {
        listOfRandomShots.add(randomCoord);
        i++;
      }
    }
    return listOfRandomShots;
  }

  /**
   * Whether the coordinate is valid.
   *
   * @param coord the coordinate
   * @return Boolean whether the coordinate is valid
   */
  private boolean validCoordinate(Coord coord) {
    if (coord.returnX() >= this.coveredBoard.returnWidth() || coord.returnX() < 0
        || coord.returnY() >= this.coveredBoard.returnHeight() || coord.returnY() < 0) {
      return false;
    }
    return true;
  }
}
