package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents how to set up the server.
 *
 * @param width of the board
 * @param height of the board
 * @param fleetSpec specifications of the fleet
 */
public record ServerSetupJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") FleetSpec fleetSpec) {

  /**
   * Gets the width.
   *
   * @return the width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the height.
   *
   * @return the height
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the specifications.
   *
   * @return the specifications
   */
  public FleetSpec getFleetSpec() {
    return this.fleetSpec;
  }
}
