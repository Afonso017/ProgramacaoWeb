package br.edu.ufersa.pizzaria.backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "client_id")
public class ClientDelivery extends Client {
  @NotBlank
  private String address; // Específico para clientes de entrega

  public ClientDelivery(String phone, String email, String password, String address) {
    super(phone, email, password);
    this.address = address;
  }
}