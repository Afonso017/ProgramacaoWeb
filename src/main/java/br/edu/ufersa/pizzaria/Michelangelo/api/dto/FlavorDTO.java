package br.edu.ufersa.pizzaria.Michelangelo.api.dto;

import java.util.List;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.PriceEntry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FlavorDTO {
  public record FlavorResponse(
      String name,
      String description,
      List<PriceEntry> price) {

    public FlavorResponse(Flavor flavor) {
      this(
          flavor.getName(),
          flavor.getDescription(),
          flavor.getPrice());
    }
  }

  // DTO's Requests \/

  public record FlavorCreate(
      @NotBlank(message = "Name is mandatory") String name,
      @NotBlank(message = "Description is mandatory") String description,
      @NotNull(message = "Price is mandatory") @Size(min = 3, max = 3, message = "Price list must have exactly 3 entries") List<PriceEntry> price) {

    public Flavor toEntity() {
      return new Flavor(name(), description(), price());
    }
  }
}