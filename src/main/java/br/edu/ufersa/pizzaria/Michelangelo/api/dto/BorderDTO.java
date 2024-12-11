package br.edu.ufersa.pizzaria.Michelangelo.api.dto;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class BorderDTO {
  public record BorderResponse(
      String name,
      BigDecimal price) {
  }

  public record BorderRequest(
      @NotBlank String name,
      @PositiveOrZero @Column(precision = 10, scale = 2) BigDecimal price) {
  }
}
