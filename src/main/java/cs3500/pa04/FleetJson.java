package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FleetJson(
    @JsonProperty("fleet") ShipJson[] listOfShips) {

  /**
   * Returns the fleet.
   *
   * @return ShipJson[] the fleet
   */
  public ShipJson[] returnFleet() {
    return this.listOfShips;
  }
}
