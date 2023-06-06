package cs3500.pa04;

/**
 * Displays the game to the viewer.
 */
public class View {
  /**
   * Displays the player board.
   *
   * @param board the board to display
   */
  public void displayBoard(Board board) {
    for (int i = 0; i < board.returnHeight(); i++) {
      for (int j = 0; j < board.returnWidth(); j++) {
        System.out.print(board.returnBoard()[i][j]);
        if (j == board.returnWidth() - 1) {
          System.out.print("\n");
        }
      }
    }
  }

  /**
   * Prints out a message.
   *
   * @param message the message to be printed
   */
  public void printMessage(String message) {
    System.out.println(message);
  }
}
