package br.edu.ufersa.pizzaria.backend.api.dto;

import br.edu.ufersa.pizzaria.backend.api.dto.PriceDTO.PriceCreate;
import br.edu.ufersa.pizzaria.backend.api.dto.PriceDTO.PriceResponse;
import br.edu.ufersa.pizzaria.backend.api.dto.PriceDTO.PriceUpdate;
import br.edu.ufersa.pizzaria.backend.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.backend.domain.entity.PriceEntry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class FlavorDTO {
  public record FlavorResponse(
      Long id,
      String name,
      String description,
      List<PriceResponse> price
  ) {
    public FlavorResponse(Flavor flavor) {
      this(
          flavor.getId(),
          flavor.getName(),
          flavor.getDescription(),
          flavor.getPrice().stream().map(PriceResponse::new).collect(Collectors.toList())
      );
    }
  }

  // DTO's Requests \/

  public record FlavorCreate(
      @NotBlank String name,
      @NotBlank String description,
      @Size(min = 3, max = 3, message = "Mínimo e Máximo de 3 preços") List<PriceCreate> price) {

    public Flavor toEntity() {
      List<PriceEntry> priceEntries = price.stream().map(PriceCreate::toEntity).collect(Collectors.toList());
      return new Flavor(name(), description(), priceEntries);
    }
  }

  public record FlavorUpdate(
      @NotNull Long id,
      @NotBlank String name,
      @NotBlank String description,
      @Size(min = 3, max = 3, message = "Mínimo e Máximo de 3 preços") List<PriceUpdate> price) {

    public Flavor toEntity() {
      List<PriceEntry> priceEntries = price.stream().map(PriceUpdate::toEntity).collect(Collectors.toList());
      Flavor flavor = new Flavor(id, name, description, priceEntries);
      for (PriceEntry priceEntry : priceEntries) {
        priceEntry.setFlavor(flavor);
      }
      return flavor;
    }
  }
}