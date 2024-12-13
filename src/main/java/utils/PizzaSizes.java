package utils;

public enum PizzaSizes {
  SMALL("Pequena"),
  MEDIUM("Média"),
  LARGE("Grande");

  private final String size;

  PizzaSizes(String size) {
    this.size = size;
  }

  public String getSize() {
    return size;
  }
}