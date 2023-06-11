package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.enumerations.ShipType;
import java.util.HashMap;
import java.util.Map;

/**
 * To represent a FleetSpec Json.
 *
 * @param carrier how many carriers
 * @param battleship how many battleships
 * @param destroyer how many destroyers
 * @param submarine how many submarines
 */
public record FleetSpec(
    @JsonProperty("CARRIER") int carrier,
    @JsonProperty("BATTLESHIP") int battleship,
    @JsonProperty("DESTROYER") int destroyer,
    @JsonProperty("SUBMARINE") int submarine) {

  /**
   * Gets the ship specifications.
   *
   * @return Map the specifications of a ship, the shiptype and how many ships
   */
  public Map<ShipType, Integer> getSpecifications() {
    Map<ShipType, Integer> specifications = new HashMap<>();

    specifications.put(ShipType.B, battleship);
    specifications.put(ShipType.C, carrier);
    specifications.put(ShipType.D, destroyer);
    specifications.put(ShipType.S, submarine);

    return specifications;
  }
}
