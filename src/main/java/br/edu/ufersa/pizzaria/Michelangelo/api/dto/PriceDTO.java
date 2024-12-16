package br.edu.ufersa.pizzaria.Michelangelo.api.dto;

import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.PriceEntry;

public class PriceDTO {

  public record PriceResponse(
      String variation,
      double value,
      Long flavorId) {

    public PriceResponse(PriceEntry priceEntry) {
      this(
          priceEntry.getVariation().name(),
          priceEntry.getValue().doubleValue(),
          priceEntry.getFlavor().getId());
    }
  }
}