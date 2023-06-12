package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.enumerations.GameResult;

/**
 * Represents the end of the game.
 *
 * @param result the result of the game
 * @param reason the reason the game ended
 */
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
