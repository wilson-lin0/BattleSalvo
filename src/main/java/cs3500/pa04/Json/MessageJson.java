package cs3500.pa04.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a message Json.
 *
 * @param methodName the name of the method
 * @param arguments the arguments for the method
 */
public record MessageJson (
  @JsonProperty("method-name") String methodName,
  @JsonProperty("arguments") String[] arguments) {

  /**
   * Returns the method name.
   *
   * @return String the method name
   */
  public String returnMethodName() {
    return this.methodName;
  }

  /**
   * Returns the arguments.
   *
   * @return String[] the arguments
   */
  public String[] returnArguments() {
    return this.arguments;
  }
}
