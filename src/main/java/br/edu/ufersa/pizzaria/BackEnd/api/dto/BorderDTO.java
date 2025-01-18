package br.edu.ufersa.pizzaria.BackEnd.api.dto;

import java.math.BigDecimal;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Border;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class BorderDTO {
  public record BorderResponse(
      Long id,
      String name,
      BigDecimal price) {

    public BorderResponse(Border border) {
      this(
          border.getId(),
          border.getName(),
          border.getPrice());
    }
  }

  // DTO's Requests \/

  public record BorderCreate(
      @NotBlank String name,
      @PositiveOrZero BigDecimal price) {

    public Border toEntity() {
      return new Border(name(), price());
    }
  }
}