package utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PizzaSizes {
  SMALL("Pequena"),
  MEDIUM("Média"),
  LARGE("Grande");

  private final String size;

  PizzaSizes(String size) {
    this.size = size;
  }

  @JsonValue
  public String getSize() {
    return size;
  }

  @JsonCreator
  public static PizzaSizes fromString(String value) {
    for (PizzaSizes size : PizzaSizes.values()) {
      if (size.name().equalsIgnoreCase(value)) {
        return size;
      }
    }
    throw new IllegalArgumentException("Unknown size: " + value);
  }
}