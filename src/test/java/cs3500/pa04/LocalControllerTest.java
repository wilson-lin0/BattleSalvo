package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocalControllerTest {
  LocalController playerLocalController;
  View view;
  Coord coordinate0;
  List<Coord> listOfCoord0;

  @BeforeEach
  public void init() {
    view = new View();
    playerLocalController = new LocalController(new StringReader("0 2"), view);
    coordinate0 = new Coord(0, 2);
    listOfCoord0 = new ArrayList<>();
    listOfCoord0.add(coordinate0);
  }

  @Test
  public void testAskForShots() {
    assertEquals(listOfCoord0.size(), playerLocalController.askForShots(1).size());
  }

  @Test
  public void testStartGame() {
    MockInput mock = new MockInput();
    mock.expectedNextString("emily");
    mock.expectedNextString("6 6");
    mock.expectedNextString("1 1 1 1");
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        mock.expectedNextString(i + " " + j);
      }
    }
    LocalController player = new LocalController(mock, view);
    player.run();
  }

  @Test
  public void testStartGame2() {
    MockInput mock = new MockInput();
    mock.expectedNextString("emily");
    mock.expectedNextString("-1 7");
    mock.expectedNextString("7 -1");
    mock.expectedNextString("17 7");
    mock.expectedNextString("7 17");
    mock.expectedNextString("6 6");
    mock.expectedNextString("1 1 1 1");
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        mock.expectedNextString(i + " " + j);
      }
    }
    LocalController player = new LocalController(mock, view);
    player.run();
  }

  @Test
  public void testStartGame3() {
    MockInput mock = new MockInput();
    mock.expectedNextString("emily");
    mock.expectedNextString("6 6");
    mock.expectedNextString("3 3 3 3");
    mock.expectedNextString("1 1 1 1");
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        mock.expectedNextString(i + " " + j);
      }
    }
    LocalController player = new LocalController(mock, view);
    player.run();
  }

  @Test
  public void testStartGame4() {
    MockInput mock = new MockInput();
    mock.expectedNextString("emily");
    mock.expectedNextString("6 6");
    mock.expectedNextString("1 1 1 1");
    mock.expectedNextString("1 18");
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        mock.expectedNextString(i + " " + j);
      }
    }
    LocalController player = new LocalController(mock, view);
    player.run();
  }
}