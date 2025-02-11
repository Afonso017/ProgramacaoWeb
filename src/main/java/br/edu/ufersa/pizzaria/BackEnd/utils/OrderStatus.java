package br.edu.ufersa.pizzaria.backend.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
  PENDING("Pendente"),
  IN_PROGRESS("Em andamento"),
  FINALIZED("Finalizado"),
  DELIVERED("Entregue"),
  CANCELED("Cancelado");

  private final String status;

  OrderStatus(String status) { this.status = status; }

  @JsonValue
  public String getStatus() {
    return status;
  }

  @JsonCreator
  public static OrderStatus fromString(String value) {
    for (OrderStatus status : OrderStatus.values()) {
      if (status.getStatus().equalsIgnoreCase(value)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Unknown status: " + value);
  }
}