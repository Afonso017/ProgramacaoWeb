package br.edu.ufersa.pizzaria.BackEnd.api.dto;

import java.math.BigDecimal;
import java.util.List;

import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Additional;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Border;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Pizza;
import br.edu.ufersa.pizzaria.BackEnd.domain.repository.BorderRepository;
import br.edu.ufersa.pizzaria.BackEnd.domain.repository.FlavorRepository;
import br.edu.ufersa.pizzaria.BackEnd.domain.repository.AdditionalRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import utils.PizzaSizes;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.FlavorDTO.FlavorResponse;

public class PizzaDTO {

    public record PizzaResponse(
      Long id,
      String name,
      String description,
      double price,
      FlavorResponse flavorOne,
      FlavorResponse flavorTwo,
      Border border,
      List<Additional> aditionals,
      PizzaSizes size,
      String image) {

    public PizzaResponse(Pizza pizza) {
      this(
          pizza.getId(),
          pizza.getName(),
          pizza.getDescription(),
          pizza.getPrice().doubleValue(),
          pizza.getFlavorOne().toResponse(),
          pizza.getFlavorTwo().toResponse(),
          pizza.getBorder(),
          pizza.getAditionals(),
          pizza.getSize(),
          pizza.getImage());
    }
  }

  // DTO's Requests \/

  public record PizzaCreate(
      @NotBlank String name,
      @NotBlank String description,
      @PositiveOrZero BigDecimal price,
      @NotNull Long flavorOne,
      Long flavorTwo,
      Long border,
      List<Long> aditionals,
      @NotNull @Enumerated(EnumType.STRING) PizzaSizes size,
      String image) { }
}