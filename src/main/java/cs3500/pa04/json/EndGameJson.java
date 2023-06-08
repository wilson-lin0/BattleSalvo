package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.enumerations.GameResult;

public record EndGameJson(
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason) {

  public GameResult returnResult() {
    return this.result;
  }

  public String returnReason() {
    return this.reason;
  }
}
