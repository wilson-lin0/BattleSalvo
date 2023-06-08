package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TakeShotsJson(
    @JsonProperty("coordinates") CoordinatesJson coordinates) {
}
