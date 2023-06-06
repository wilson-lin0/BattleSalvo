package cs3500.pa04;

/**
 * A pair of numbers, most often to describe a coordinate X Y.
 */
public class Coord {
  private int coordinateX;
  private int coordinateY;

  Coord(int coordinateX, int coordinateY) {
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
  }

  /**
   * Returns the X value of the Coord.
   *
   * @return int the X value
   */
  public int returnX() {
    return this.coordinateX;
  }

  /**
   * Returns the Y value of the Coord.
   *
   * @return int the Y value
   */
  public int returnY() {
    return this.coordinateY;
  }

  /**
   * Whether the coordinates are equal.
   *
   * @param coordinate the coordinate to be compared
   * @return whether the coordinates are equal
   */
  public Boolean isEqual(Coord coordinate) {
    return this.coordinateX == coordinate.coordinateX && this.coordinateY == coordinate.coordinateY;
  }
}
