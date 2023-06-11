package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.enumerations.ShipType;
import java.util.HashMap;
import java.util.Map;

public record FleetSpec(
    @JsonProperty("CARRIER") int CARRIER,
    @JsonProperty("BATTLESHIP") int BATTLESHIP,
    @JsonProperty("DESTROYER") int DESTROYER,
    @JsonProperty("SUBMARINE") int SUBMARINE) {

  public Map<ShipType, Integer> getSpecifications() {
    Map<ShipType, Integer> specifications = new HashMap<>();

    specifications.put(ShipType.B, BATTLESHIP);
    specifications.put(ShipType.C, CARRIER);
    specifications.put(ShipType.D, DESTROYER);
    specifications.put(ShipType.S, SUBMARINE);

    return specifications;
  }
}
