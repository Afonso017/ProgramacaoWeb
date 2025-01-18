package br.edu.ufersa.pizzaria.BackEnd.api.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ufersa.pizzaria.BackEnd.api.dto.PriceDTO.PriceCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.PriceDTO.PriceResponse;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.PriceDTO.PriceUpdate;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.PriceEntry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FlavorDTO {
  public record FlavorResponse(
      Long id,
      String name,
      String description,
      List<PriceResponse> price) {

    public FlavorResponse(Flavor flavor) {
      this(
          flavor.getId(),
          flavor.getName(),
          flavor.getDescription(),
          flavor.getPrice().stream().map(PriceResponse::new).collect(Collectors.toList()));
    }
  }

  // DTO's Requests \/

  public record FlavorCreate(
      @NotBlank(message = "Name is mandatory") String name,
      @NotBlank(message = "Description is mandatory") String description,
      @Size(min = 3, max = 3, message = "Price list must have exactly 3 entries") List<PriceCreate> price) {

    public Flavor toEntity() {
      List<PriceEntry> priceEntries = price.stream().map(PriceCreate::toEntity).collect(Collectors.toList());
      Flavor flavor = new Flavor(name(), description(), priceEntries);
      for (PriceEntry priceEntry : priceEntries) {
        priceEntry.setFlavor(flavor);
      }
      return flavor;
    }
  }

  public record FlavorUpdate(
      @NotNull(message = "Id is mandatory") Long id,
      @NotBlank(message = "Name is mandatory") String name,
      @NotBlank(message = "Description is mandatory") String description,
      @Size(min = 3, max = 3, message = "Price list must have exactly 3 entries") List<PriceUpdate> price) {

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