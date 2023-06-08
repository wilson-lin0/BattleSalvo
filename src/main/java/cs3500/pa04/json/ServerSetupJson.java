package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServerSetupJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") FleetSpec fleetSpec) {

  public int getWidth() {
    return this.width;
  }
  public int getHeight() {
    return this.height;
  }
  public FleetSpec getFleetSpec() {
    return this.fleetSpec;
  }
}
