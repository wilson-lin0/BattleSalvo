package cs3500.pa04;

import cs3500.pa04.enumerations.Direction;
import cs3500.pa04.enumerations.ShipType;
import java.util.List;

/**
 * To represent a ship in the game BattleSalvo.
 */
public class Ship {
  private int size;
  private int hitPoints;
  private ShipType type;
  private List<Coord> coordinate;

  Ship(int size, ShipType type, List<Coord> coordinate) {
    this.size = size;
    this.hitPoints = size;
    this.type = type;
    this.coordinate = coordinate;
  }

  /**
   * Gets the size of this ship.
   *
   * @return int the size ship type
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Gets the ship type of this ship.
   *
   * @return ShipType the ship type
   */
  public ShipType getType() {
    return this.type;
  }

  /**
   * Gets the ship location.
   *
   * @return List the list of coordinates that make up the ship location
   */
  public List<Coord> getLocation() {
    return this.coordinate;
  }

  /**
   * Determines whether the ship is hit. If the ship is hit, deduct 1 hit point. Return -1 if
   * the shot missed.
   *
   * @param coordinate the coordinate of the shot
   * @return int the remaining hit points.
   */
  public int takeHit(Coord coordinate) {
    for (Coord location : this.coordinate) {
      if (location.isEqual(coordinate)) {
        this.hitPoints--;
        return hitPoints;
      }
    }
    return -1;
  }

  /**
   * Gets the direction.
   *
   * @return the direction
   */
  public Direction getDirection() {
    if (this.coordinate.get(0).returnX() < this.coordinate.get(1).returnX()) {
      return Direction.HORIZONTAL;
    } else {
      return Direction.VERTICAL;
    }
  }
}
