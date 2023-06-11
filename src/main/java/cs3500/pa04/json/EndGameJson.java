package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.enumerations.GameResult;

/**
 * To represent the end of a game in Json.
 *
 * @param result the result of the game
 * @param reason the reason of the result
 */
public record EndGameJson(
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason) {

  /**
   * Returns the result of the game
   *
   * @return GameResult the result
   */
  public GameResult returnResult() {
    return this.result;
  }

  /**
   * Returns the reason the game ended.
   *
   * @return String the reason
   */
  public String returnReason() {
    return this.reason;
  }
}
