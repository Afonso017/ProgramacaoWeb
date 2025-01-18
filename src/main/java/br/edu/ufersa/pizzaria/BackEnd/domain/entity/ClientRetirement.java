package br.edu.ufersa.pizzaria.BackEnd.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Random;

@Entity
@PrimaryKeyJoinColumn(name = "client_id")
public class ClientRetirement extends Client {
  @PositiveOrZero
  private int code; // Código de retirada

  public ClientRetirement() {
    this.generateCode();
  }

  public ClientRetirement(int code) {
    this.code = code;
  }

  public ClientRetirement(String email, String password, String phone, int code) {
    super(email, password, phone);
    this.code = code;
  }

  /**
   * @return int return the code
   */
  public int getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(int code) {
    this.code = code;
  }

  public void generateCode() {
    Random random = new Random();
    this.code = random.nextInt(1000); // Gera um número entre 0 e 999
  }
}