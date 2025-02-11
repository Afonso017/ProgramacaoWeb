package br.edu.ufersa.pizzaria.backend.api.dto;

import br.edu.ufersa.pizzaria.backend.domain.entity.Additional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class AdditionalDTO {
  public record AdditionalResponse(
      Long id,
      String name,
      String description,
      BigDecimal price,
      String image) {

    public AdditionalResponse(Additional additional) {
      this(
          additional.getId(),
          additional.getName(),
          additional.getDescription(),
          additional.getPrice(),
          additional.getImage());
    }
  }

  // DTO's Requests

  public record AdditionalCreate(
      @NotBlank String name,
      @NotBlank String description,
      @PositiveOrZero BigDecimal price,
      String image) {

    public Additional toEntity() {
      return new Additional(name(), description(), price(), image());
    }
  }
}