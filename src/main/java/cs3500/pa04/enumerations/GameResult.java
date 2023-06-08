package cs3500.pa04.enumerations;

/**
 * Different ways a game can end.
 */
public enum GameResult {
  WIN("Win"),
  LOSE("Lose"),
  DRAW("Draw");

  private String result;

  GameResult(String result) {
    this.result = result;
  }

  /**
   * Gets the String of the GameResult.
   *
   * @return String the String GameResult
   */
  public String getResult() {
    return this.result;
  }
}
