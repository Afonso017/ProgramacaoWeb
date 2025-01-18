package br.edu.ufersa.pizzaria.BackEnd.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;

@Entity
@PrimaryKeyJoinColumn(name = "client_id")
public class ClientDelivery extends Client {
  @NotBlank
  private String address; // Específico para clientes de entrega

  public ClientDelivery() {
  }

  public ClientDelivery(String address) {
    this.address = address;
  }

  public ClientDelivery(String email, String password, String phone, String address) {
    super(email, password, phone);
    this.address = address;
  }

  /**
   * @return String return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }
}