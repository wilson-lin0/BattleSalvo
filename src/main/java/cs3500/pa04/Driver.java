package cs3500.pa04;

import java.io.InputStreamReader;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    View view = new View();
    LocalController control = new LocalController(new InputStreamReader(System.in), view);
    control.run();
  }
}