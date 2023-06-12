package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.enumerations.ShipType;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the fleet specifications
 *
 * @param carrier how many carrier ships
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
   * Gets the specifications.
   *
   * @return Map the specifications
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
