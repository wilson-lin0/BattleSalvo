package cs3500.pa04;

import java.nio.CharBuffer;
import java.util.ArrayList;

/**
 * Mock Input for testing.
 */
class MockInput implements Readable {
  ArrayList<String> nextStrings;

  MockInput() {
    nextStrings = new ArrayList<>();
  }

  @Override
  public int read(CharBuffer cb) {
    String str = nextStrings.get(0);
    nextStrings.remove(0);
    cb.put(str).put("\n");
    return str.length() + 1;
  }

  /**
   * Mock input add string.
   *
   * @param string the string to append
   */
  public void expectedNextString(String string) {
    this.nextStrings.add(string);
  }
}

