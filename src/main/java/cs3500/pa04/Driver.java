package cs3500.pa04;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
    if (args.length == 2) {
      try {
        Driver.runClient(args[0], Integer.parseInt(args[1]));
      } catch (IOException e) {
        throw new IllegalArgumentException("You provided an invalid host and/or port!");
      }
    } else if (args.length == 0) {
      View view = new View();
      LocalController control = new LocalController(new InputStreamReader(System.in), view);
      control.run();
    } else {
      throw new IllegalArgumentException("You provided an incorrect amount of arguments!");
    }
  }

  /**
   * This method connects to the server at the given host and port, builds a proxy referee
   * to handle communication with the server, and sets up a client player.
   *
   * @param host the server host
   * @param port the server port
   * @throws IOException if there is a communication issue with the server
   */
  private static void runClient(String host, int port)
      throws IOException, IllegalStateException {
    Socket server = new Socket(host, port);
    AiOpponent player = new AiOpponent("Emily");
    ProxyController proxyController = new ProxyController(server, player);
    proxyController.run();
  }
}