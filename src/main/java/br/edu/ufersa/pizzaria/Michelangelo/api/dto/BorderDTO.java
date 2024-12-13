package br.edu.ufersa.pizzaria.Michelangelo.api.dto;

import java.math.BigDecimal;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Border;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class BorderDTO {
  public record BorderResponse(
      String name,
      BigDecimal price) {

    public BorderResponse(Border border) {
      this(
          border.getName(),
          border.getPrice());
    }
  }

  // DTO's Requests \/

  public record BorderCreate(
      @NotBlank String name,
      @PositiveOrZero @Column(precision = 10, scale = 2, nullable = false) BigDecimal price) {

    public Border toEntity() {
      return new Border(name(), price());
    }
  }
}