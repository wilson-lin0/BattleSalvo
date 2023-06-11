package cs3500.pa04.enumerations;

public enum GameType {
  SINGLE("SINGLE"),
  MULTI("MULTI");

  private final String gameType;

  GameType(String gameType) {
    this.gameType = gameType;
  }

  /**
   * Gets the String of the gameType.
   *
   * @return String the String gameType
   */
  public String getGameType() {
    return this.gameType;
  }
}
