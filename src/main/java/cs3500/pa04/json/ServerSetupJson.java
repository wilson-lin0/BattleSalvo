package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Reresents a ServerSetupJson to set up the server.
 *
 * @param width the width of the board
 * @param height the height of the board
 * @param fleetSpec the fleet specifications
 */
public record ServerSetupJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") FleetSpec fleetSpec) {

  /**
   * Returns the width.
   *
   * @return int the width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the height.
   *
   * @return int the height
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the fleetSpec.
   *
   * @return FleetSpec the fleetSpec
   */
  public FleetSpec getFleetSpec() {
    return this.fleetSpec;
  }
}
