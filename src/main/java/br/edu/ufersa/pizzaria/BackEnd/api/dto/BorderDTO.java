package br.edu.ufersa.pizzaria.backend.api.dto;

import br.edu.ufersa.pizzaria.backend.domain.entity.Border;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class BorderDTO {
  public record BorderResponse(
      Long id,
      String name,
      BigDecimal price
  ) {
    public BorderResponse(Border border) {
      this(
          border.getId(),
          border.getName(),
          border.getPrice()
      );
    }
  }

  // DTO's Requests

  public record BorderCreate(
      @NotBlank String name,
      @PositiveOrZero BigDecimal price) {

    public Border toEntity() {
      return new Border(name(), price());
    }
  }
}