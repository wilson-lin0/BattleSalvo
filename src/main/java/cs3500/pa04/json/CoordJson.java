package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a coord Json.
 *
 * @param x the x coordinate of a Coord
 * @param y the y coordinate of a Coord
 */
public record CoordJson(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y) {

  /**
   * Returns the x coordinate.
   *
   * @return int the x coordinate
   */
  public int returnX() {
    return this.x;
  }

  /**
   * Returns the y coordinate.
   *
   * @return int the y coordinate
   */
  public int returnY() {
    return this.y;
  }
}
