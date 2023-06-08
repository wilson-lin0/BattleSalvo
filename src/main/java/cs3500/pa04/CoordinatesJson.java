package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CoordinatesJson(
    @JsonProperty("coordinates") CoordJson[] listOfCoord) {
}
