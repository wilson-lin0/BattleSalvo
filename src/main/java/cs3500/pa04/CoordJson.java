package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CoordJson(
    @JsonProperty("x") int xCoordinate,
    @JsonProperty("y") int yCoordinate) {

  /**
   * Returns the x coordinate.
   *
   * @return int the x coordinate
   */
  public int returnX() {
    return this.xCoordinate;
  }

  /**
   * Returns the y coordinate.
   *
   * @return int the y coordinate
   */
  public int returnY() {
    return this.yCoordinate;
  }
}
