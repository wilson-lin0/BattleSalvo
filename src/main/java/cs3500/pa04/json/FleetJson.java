package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a list of ships Json.
 *
 * @param listOfShips the list of ships
 */
public record FleetJson(
    @JsonProperty("fleet") ShipJson[] listOfShips) {
}
