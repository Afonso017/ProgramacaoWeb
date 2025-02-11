package br.edu.ufersa.pizzaria.backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "client_id")
public class ClientLocal extends Client {

  @PositiveOrZero
  @NotNull
  private Integer clientTable;

  public ClientLocal(String phone, String email, String password, Integer clientTable) {
    super(phone, email, password);
    this.clientTable = clientTable;
  }

  public ClientLocal(Long id, String email, String password, String phone, Integer clientTable) {
    super(id, phone, email, password);
    this.clientTable = clientTable;
  }

}