package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.enumerations.Direction;

/**
 * Represents a ship Json.
 *
 * @param coord the starting coordinate of the ship
 * @param length the length of the ship
 * @param direction the direction of the ship
 */
public record ShipJson(
    @JsonProperty ("coord") CoordJson coord, // starting coordinate
    @JsonProperty("length") int length,
    @JsonProperty("direction") Direction direction) {

  /**
   * Retusn the coordinate.
   *
   * @return CoordJson the coordinate
   */
  public CoordJson returnCoord() {
    return this.coord;
  }

  /**
   * Returns the length.
   *
   * @return int the length
   */
  public int returnLength() {
    return this.length;
  }

  /**
   * Returns the direction.
   *
   * @return Direction the direction
   */
  public Direction returnDirection() {
    return this.direction;
  }
}
