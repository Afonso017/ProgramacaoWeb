package br.edu.ufersa.pizzaria.backend.api.dto;

import br.edu.ufersa.pizzaria.backend.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.backend.domain.entity.PriceEntry;
import br.edu.ufersa.pizzaria.backend.utils.PizzaSizes;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PriceDTO {
  public record PriceResponse(
      Long id,
      @NotNull @Enumerated(EnumType.STRING) PizzaSizes variation,
      BigDecimal value
  ) {
    public PriceResponse(PriceEntry priceEntry) {
      this(priceEntry.getId(),
          priceEntry.getVariation(),
          priceEntry.getValue()
      );
    }
  }

  public record PriceCreate(
      @NotNull @Enumerated(EnumType.STRING) PizzaSizes variation,
      @NotNull BigDecimal value,
      @NotNull Long flavorId
  ) {
    public PriceEntry toEntity() {
      Flavor flavor = new Flavor();
      flavor.setId(flavorId);

      return new PriceEntry(variation, value, flavor);
    }
  }

  public record PriceUpdate(
      @NotNull Long id,
      @NotNull @Enumerated(EnumType.STRING) PizzaSizes variation,
      @NotNull BigDecimal value,
      @NotNull Long flavorId
  ) {
    public PriceEntry toEntity() {
      Flavor flavor = new Flavor();
      flavor.setId(flavorId);

      return new PriceEntry(id, variation, value, flavor);
    }
  }
}