package br.edu.ufersa.pizzaria.Michelangelo.api.dto;

import java.util.List;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.PriceEntry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FlavorDTO {
  public record FlavorResponse(
      String name,
      String description,
      List<PriceEntry> price) {
  }

  public record FlavorRequest(
      @NotBlank String name,
      @NotBlank String description,
      @NotNull List<PriceEntry> price) {
  }
}