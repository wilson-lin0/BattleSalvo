package cs3500.pa04;

import java.util.List;

/**
 * Controls the flow of BattleSalvo.
 */
public interface Controller {
  /**
   * Asks the player for shots.
   *
   * @param shotsAvailable the number of shots available
   * @return List the list of coordinates of the shots
   */
  List<Coord> askForShots(int shotsAvailable);

  /**
   * Starts the game of BattleSalvo.
   */
  void run();
}
