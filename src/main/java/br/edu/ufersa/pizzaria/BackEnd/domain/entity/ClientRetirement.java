package br.edu.ufersa.pizzaria.backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.util.Random;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "client_id")
public class ClientRetirement extends Client {

  @PositiveOrZero
  @NotNull
  private Integer code;

  public ClientRetirement(String phone, String email, String password, Integer code) {
    super(phone, email, password);
    this.code = code;
  }

  public void generateCode() {
    Random random = new Random();
    this.code = random.nextInt(1000); // Gera um número entre 0 e 999
  }
}