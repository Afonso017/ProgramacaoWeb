package br.edu.ufersa.pizzaria.BackEnd.api.dto;

import java.math.BigDecimal;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Additional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

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

  // DTO's Requests \/

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