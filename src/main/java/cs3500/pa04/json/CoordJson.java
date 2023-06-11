package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a coord Json.
 *
 * @param coordinateX the x coordinate of a Coord
 * @param coordinateY the y coordinate of a Coord
 */
public record CoordJson(
    @JsonProperty("x") int coordinateX,
    @JsonProperty("y") int coordinateY) {

  /**
   * Returns the x coordinate.
   *
   * @return int the x coordinate
   */
  public int returnX() {
    return this.coordinateX;
  }

  /**
   * Returns the y coordinate.
   *
   * @return int the y coordinate
   */
  public int returnY() {
    return this.coordinateY;
  }
}
