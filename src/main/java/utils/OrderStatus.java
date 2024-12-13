package utils;

public enum OrderStatus {
  PENDING("Pendente"),
  IN_PROGRESS("Em andamento"),
  DELIVERED("Entregue"),
  CANCELED("Cancelado");

  private final String status;

  OrderStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

}