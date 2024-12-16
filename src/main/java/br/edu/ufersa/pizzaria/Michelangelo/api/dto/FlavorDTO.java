package br.edu.ufersa.pizzaria.Michelangelo.api.dto;

import java.util.List;
import java.util.stream.Collectors;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.PriceDTO.PriceResponse;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.PriceEntry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FlavorDTO {
  public record FlavorResponse(
      String name,
      String description,
      List<PriceResponse> price) {

    public FlavorResponse(Flavor flavor) {
      this(
          flavor.getName(),
          flavor.getDescription(),
          flavor.getPrice().stream().map(PriceResponse::new).collect(Collectors.toList()));
    }
  }

  // DTO's Requests \/

  public record FlavorCreate(
      @NotBlank(message = "Name is mandatory") String name,
      @NotBlank(message = "Description is mandatory") String description,
      @Size(min = 3, max = 3, message = "Price list must have exactly 3 entries") List<PriceEntry> price) {

    public Flavor toEntity() {
      Flavor flavor = new Flavor(name(), description(), price());
      for (PriceEntry priceEntry : price()) {
        priceEntry.setFlavor(flavor);
      }
      return flavor;
    }
  }
}