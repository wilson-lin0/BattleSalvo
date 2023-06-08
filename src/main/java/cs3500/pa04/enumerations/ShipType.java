package cs3500.pa04.enumerations;

/**
 * Represents all the ship types.
 */
public enum ShipType {
  B('B'),
  C('C'),
  D('D'),
  S('S');

  private final char shipName;
  private final int size;

  ShipType(char shipName) {
    this.shipName = shipName;
    switch (shipName) {
      case 'B': this.size = 5;
      break;
      case 'C': this.size = 6;
      break;
      case 'D': this.size = 4;
      break;
      default: this.size = 3;
    }
  }

  /**
   * Returns the name of the ship.
   *
   * @return String the name of the ship
   */
  public char getShipName() {
    return this.shipName;
  }

  /**
   * Returns the size of the ship.
   *
   * @return int the size of the ship
   */
  public int getSize() {
    return this.size;
  }
}
