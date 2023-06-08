package cs3500.pa04;

public enum Direction {
  VERTICAL("Vertical"),
  HORIZONTAL("Horizontal");

  private String direction;
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
