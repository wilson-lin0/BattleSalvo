package cs3500.pa04.enumerations;

public enum Direction {
  VERTICAL("VERTICAL"),
  HORIZONTAL("HORIZONTAL");

  private final String direction;
  Direction(String direction) {
    this.direction = direction;
  }

  /**
   * Gets the string form of direction.
   *
   * @return String the string form of direction
   */
  public String getDirection() {
    return this.direction;
  }

}
