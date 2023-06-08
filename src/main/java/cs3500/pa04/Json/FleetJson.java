package cs3500.pa04.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a list of ships Json.
 *
 * @param listOfShips the list of ships
 */
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
