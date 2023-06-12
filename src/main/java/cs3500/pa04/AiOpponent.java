package cs3500.pa04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The opponent.
 */
public class AiOpponent extends AbstractPlayer {
  private List<Coord> listPossibleShots;
  AiOpponent(String name) {
    super(name);
    listPossibleShots = new ArrayList<>();
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

    List<Coord> shotsHit;
    shotsHit = returnShotsHit(shotsAvailable);
    if (shotsHit.isEmpty()) {
      return getRandomShots(shotsAvailable);
    }

    List<Coord> shotsToFire = new ArrayList<>();
    for (Coord coordinate : shotsHit) {
      Boolean full;
      List<Coord> tempShots;
      tempShots = upDownLeftRight(coordinate);

      for (Coord coordinates : tempShots) {
        if (this.coveredBoard.getCoord(coordinates) == 'O') {
          shotsToFire.add(coordinates);
          this.coveredBoard.updatePosition(coordinates, 'M');
          if (shotsToFire.size() == shotsAvailable) {
            break;
          }
        }
      }

      if (returnBoard().isEmpty()) {
        List<Coord> empty = new ArrayList<>();
        return empty;
      }

      while (true) {
        shotsToFire.add(getRandomShots(1).get(0));
        this.coveredBoard.updatePosition(getRandomShots(1).get(0), 'M');
        if (shotsToFire.size() == shotsAvailable) {
          full = true;
          break;
        }
      }

      if (full) {
        break;
      }
    }
    return shotsToFire;
  }

  /**
   * Returns the valid coordinates adjacent to the provided Coord.
   *
   * @param coord the provided coord
   * @return List the list of coordinates that are adjacent
   */
  private List<Coord> upDownLeftRight(Coord coord) {
    Coord down = new Coord(coord.returnX(), coord.returnY() + 1);
    Coord up = new Coord(coord.returnX(), coord.returnY() - 1);
    Coord left = new Coord(coord.returnX() - 1, coord.returnY());
    Coord right = new Coord(coord.returnX() + 1, coord.returnY());

    List<Coord> coordList = new ArrayList<>();
    coordList.add(down);
    coordList.add(up);
    coordList.add(left);
    coordList.add(right);

    List<Coord> validShots;
    validShots = validCoordList(coordList);

    return validShots;
  }

  /**
   * Gets a list of random shots on the board.
   *
   * @param limit the maximum amount of shots
   * @return List the list of coordinates
   */
  private List<Coord> getRandomShots(int limit) {
    List<Coord> listOfRandomShots = new ArrayList<>();
    Random random = new Random();

    for (int i = 0; i < this.coveredBoard.returnHeight(); i++) {
      for (int j = 0; j < this.coveredBoard.returnWidth(); j++) {
        this.listPossibleShots.add(new Coord(j, i));
      }
    }

    for (int i = 0; i < limit; i++) {
      int randomNum = random.nextInt(listPossibleShots.size());
      listOfRandomShots.add(listPossibleShots.get(randomNum));
      listPossibleShots.remove(randomNum);
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

  /**
   * Returns a list of valid coordinates.
   *
   * @param coordinates the list of coordinates to test
   * @return List the list of valid coordinates
   */
  private List<Coord> validCoordList(List<Coord> coordinates) {
    List<Coord> returnList = new ArrayList<>();
    for (Coord coordinate : coordinates) {
      if (validCoordinate(coordinate)) {
        returnList.add(coordinate);
      }
    }
    return returnList;
  }

  /**
   * Returns the shots that hit a ship.
   *
   * @param shotsAvailable the number of shots available
   * @return List the list of shots that hit the ship
   */
  private List<Coord> returnShotsHit(int shotsAvailable) {
    List<Coord> shotsHit = new ArrayList<>();
    for (int i = 0; i < this.coveredBoard.returnHeight() && shotsAvailable > 0; i++) {
      for (int j = 0; j < this.coveredBoard.returnWidth() && shotsAvailable > 0; j++) {
        if (this.coveredBoard.getCoord(new Coord(j, i)) == 'H') {
          shotsHit.add(new Coord(j, i));
        }
      }
    }
    return shotsHit;
  }

  /**
   * Returns the coordinates of the board.
   *
   * @return List the list of coordinates
   */
  private List<Coord> returnBoard() {
    List<Coord> shotsHit = new ArrayList<>();
    for (int i = 0; i < this.coveredBoard.returnHeight(); i++) {
      for (int j = 0; j < this.coveredBoard.returnWidth(); j++) {
        if (this.coveredBoard.getCoord(new Coord(j, i)) == 'O') {
          shotsHit.add(new Coord(j, i));
        }
      }
    }
    return shotsHit;
  }
}
