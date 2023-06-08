package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a coordinate Json.
 *
 * @param listOfCoord a list of CoordJson
 */
public record CoordinatesJson(
    @JsonProperty("coordinates") CoordJson[] listOfCoord) {

  public CoordJson[] getListOfCoord() {
    return this.listOfCoord;
  }
}
