package cs3500.pa04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * To represent the board of BattleSalvo.
 */
public class Board {
  private int width;
  private int height;
  private List<Ship> listOfShips; // unsunk ships
  private char[][] playingBoard;

  /**
   * Constructor, sets up the initial board with 'O'.
   *
   * @param width  the width of the board
   * @param height the height of the board
   */
  Board(int width, int height) {
    this.width = width;
    this.height = height;
    this.listOfShips = new ArrayList<>();
    this.playingBoard = new char[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.playingBoard[i][j] = 'O';
      }
    }
  }

  /**
   * Whether the coordinate is valid.
   *
   * @param coord the coordinate
   * @throws IllegalArgumentException when the coordinates are not valid
   */
  private void validCoordinate(Coord coord) {
    if (coord.returnX() >= this.width || coord.returnX() < 0
        || coord.returnY() >= this.height || coord.returnY() < 0) {
      throw new IllegalArgumentException("Coordinates are out of bounds.");
    }
  }

  /**
   * Update the given location with the specific state (i.e. 'O', 'C', 'B', 'D', 'S', 'X').
   *
   * @param coord the coordinate to update
   * @param state the state of the coordinate
   * @throws IllegalArgumentException when the coordinates are not valid
   */
  public void updatePosition(Coord coord, char state) {
    validCoordinate(coord);
    this.playingBoard[coord.returnY()][coord.returnX()] = state;
  }

  /**
   * Gets the state of the given coordinate.
   *
   * @param coord the coordinate to get the state from
   * @return char the state of the coordinate
   * @throws IllegalArgumentException when the coordinate is not valid
   */
  public char getCoord(Coord coord) {
    validCoordinate(coord);
    return this.playingBoard[coord.returnY()][coord.returnX()];
  }

  /**
   * Returns true if the Coord has a ship.
   *
   * @param coord the coordinate
   * @return Boolean whether the Coord has a ship
   * @throws IllegalArgumentException when the coordinate is not valid
   */
  public Boolean takeHit(Coord coord) {
    validCoordinate(coord);
    if (this.playingBoard[coord.returnY()][coord.returnX()] == 'O') {
      this.playingBoard[coord.returnY()][coord.returnX()] = 'M';
      return false;
    } else if (this.playingBoard[coord.returnY()][coord.returnX()] == 'H') {
      return false;
    }
    this.playingBoard[coord.returnY()][coord.returnX()] = 'H';
    for (Ship ship : this.listOfShips) {
      int result = ship.takeHit(coord);
      if (result == 0) {
        listOfShips.remove(ship);
        return true;
      } else if (result > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Finds a ship a vertical space.
   *
   * @param startingX starting x coordinate
   * @param startingY starting y coordinate
   * @param size size of the ship
   * @return List the list of coordinates
   */
  private List<Coord> findVerticalSpace(int startingX, int startingY, int size) {
    List<Coord> returnList =  new ArrayList<>();
    int count = 0;

    // vertical
    for (int i = startingY; i < size && i < this.height; i++) {
      if (this.playingBoard[i][startingX] != 'O') {
        break;
      }
      returnList.add(new Coord(startingX, i));
      count++;
    }
    if (count == size) {
      return returnList;
    }

    returnList.clear();
    return returnList;
  }

  /**
   * Finds a ship a horizontal space.
   *
   * @param startingX starting x coordinate
   * @param startingY starting y coordinate
   * @param size size of the ship
   * @return List the list of coordinates
   */
  private List<Coord> findHorizontalSpace(int startingX, int startingY, int size) {
    List<Coord> returnList = new ArrayList<>();
    int count = 0;

    // horizontal
    for (int i = startingX; i < size && i < this.width; i++) {
      if (playingBoard[startingY][i] != 'O') {
        break;
      }
      returnList.add(new Coord(i, startingY));
      count++;
    }
    if (count == size) {
      return returnList;
    }

    returnList.clear();
    return returnList;
  }

  /**
   * Return a list of connected coordinates that fit size and state = 'O'.
   *
   * @param size the size of the ship
   * @return List of coordinates
   */
  public List<Coord> findEmptySpace(int size) {
    List<Coord> returnList = new ArrayList<>();
    Random randomX = new Random();
    Random randomY = new Random();
    Random random = new Random();

    while (true) {
      int startingX = randomX.nextInt(width);
      int startingY = randomY.nextInt(height);
      int randInt = random.nextInt(2);

      if (playingBoard[startingY][startingX] != 'O') {
        continue;
      }

      if (randInt == 0) {
        // horizontal first
        returnList = findHorizontalSpace(startingX, startingY, size);
        if (!returnList.isEmpty()) {
          return returnList;
        }
        returnList = findVerticalSpace(startingX, startingY, size);
        if (!returnList.isEmpty()) {
          return returnList;
        }
      } else {
        // vertical first
        returnList = findVerticalSpace(startingX, startingY, size);
        if (!returnList.isEmpty()) {
          return returnList;
        }
        returnList = findHorizontalSpace(startingX, startingY, size);
        if (!returnList.isEmpty()) {
          return returnList;
        }
      }
      returnList.clear();
    }
  }

  /**
   * Marks the ship location with the ship type using the "update" method.
   * Updates the location of the ship. Adds the ship to the list of ships.
   *
   * @param ship the ship to be placed
   */
  public void placeShip(Ship ship) {
    for (Coord coord : ship.getLocation()) {
      this.playingBoard[coord.returnY()][coord.returnX()] = ship.getType().getShipName();
    }
    listOfShips.add(ship);
  }

  /**
   * Returns the number of ships that are still alive.
   *
   * @return int the number of alive ships
   */
  public int numOfAliveShips() {
    return this.listOfShips.size();
  }

  /**
   * Returns the board height.
   *
   * @return int the board height
   */
  public int returnHeight() {
    return this.height;
  }

  /**
   * Returns the board width.
   *
   * @return int the board width
   */
  public int returnWidth() {
    return this.width;
  }

  /**
   * Returns the board.
   *
   * @return char[][] the board
   */
  public char[][] returnBoard() {
    return this.playingBoard;
  }
}
