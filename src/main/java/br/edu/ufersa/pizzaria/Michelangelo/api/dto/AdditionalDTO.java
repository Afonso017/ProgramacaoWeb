package br.edu.ufersa.pizzaria.Michelangelo.api.dto;

import java.math.BigDecimal;

import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Additional;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class AdditionalDTO {
  public record AdditionalResponse(
      String name,
      String description,
      BigDecimal price,
      String image) {

    public AdditionalResponse(Additional additional) {
      this(
          additional.getName(),
          additional.getDescription(),
          additional.getPrice(),
          additional.getImage());
    }
  }

  // DTO's Requests \/

  public record AdditionalCreate(
      @NotBlank String name,
      @NotBlank String description,
      @PositiveOrZero @Column(precision = 10, scale = 2, nullable = false) BigDecimal price,
      String image) {

    public Additional toEntity() {
      return new Additional(name(), description(), price(), image());
    }
  }
}