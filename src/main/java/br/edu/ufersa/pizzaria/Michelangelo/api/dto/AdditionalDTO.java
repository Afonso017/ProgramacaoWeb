package br.edu.ufersa.pizzaria.Michelangelo.api.dto;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class AdditionalDTO {
  public record AdditionalResponse(
      String name,
      String description,
      BigDecimal price,
      String image) {
  }

  public record AdditionalRequest(
      @NotBlank String name,
      @NotBlank String description,
      @PositiveOrZero @Column(precision = 10, scale = 2) BigDecimal price,
      String image) {
  }
}