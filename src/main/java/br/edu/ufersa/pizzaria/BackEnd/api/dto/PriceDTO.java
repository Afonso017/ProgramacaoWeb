package br.edu.ufersa.pizzaria.BackEnd.api.dto;

import java.math.BigDecimal;

import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.PriceEntry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import utils.PizzaSizes;

public class PriceDTO {
  public record PriceResponse(
      Long id,
      String variation,
      double value,
      Long flavorId) {

    public PriceResponse(PriceEntry priceEntry) {
      this(priceEntry.getId(),
          priceEntry.getVariation().name(),
          priceEntry.getValue().doubleValue(),
          priceEntry.getFlavor().getId());
    }
  }

  public record PriceCreate(
      @NotBlank String variation,
      @NotNull BigDecimal value,
      @NotNull Long flavorId) {

    public PriceEntry toEntity() {
      Flavor flavor = new Flavor();
      flavor.setId(flavorId);

      return new PriceEntry(PizzaSizes.fromString(variation), value, flavor);
    }
  }

  public record PriceUpdate(
      @NotNull Long id,
      @NotBlank String variation,
      @NotNull BigDecimal value,
      @NotNull Long flavorId) {

    public PriceEntry toEntity() {
      Flavor flavor = new Flavor();
      flavor.setId(flavorId);

      return new PriceEntry(id, PizzaSizes.fromString(variation), value, flavor);
    }
  }
}