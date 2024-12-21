package br.edu.ufersa.pizzaria.Michelangelo.api.dto;

import java.math.BigDecimal;
import java.util.List;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Additional;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Border;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Pizza;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import utils.PizzaSizes;

public class PizzaDTO {
  public record PizzaResponse(
      Long id,
      String name,
      String description,
      double price,
      Flavor flavorOne,
      Flavor flavorTwo,
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
          pizza.getFlavorOne(),
          pizza.getFlavorTwo(),
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
      @NotNull Flavor flavorOne,
      Flavor flavorTwo,
      Border border,
      List<Additional> aditionals,
      @NotNull @Enumerated(EnumType.STRING) PizzaSizes size,
      String image) {

    public Pizza toEntity() {
      return new Pizza(name, description, price, image, flavorOne, flavorTwo, border, aditionals, size);
    }
  }
}