package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.Coord;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordinatesJsonTest {
  CoordJson test;
  CoordJson test2;
  CoordJson test3;

  CoordJson[] listCoord;

  CoordinatesJson testList;

  @BeforeEach
  public void init() {
    test = new CoordJson(3, 3);
    test2 = new CoordJson(2, 3);
    test3 = new CoordJson(4, 5);

    listCoord = new CoordJson[3];
    listCoord[0] = test;
    listCoord[1] = test2;
    listCoord[2] = test3;

    testList = new CoordinatesJson(listCoord);
  }

  @Test
  public void testGetListOfCoord() {

    assertEquals(listCoord, testList.getListOfCoord());
  }

}