package br.edu.ufersa.pizzaria.backend.api.dto;

import br.edu.ufersa.pizzaria.backend.api.dto.PriceDTO.PriceResponse;
import br.edu.ufersa.pizzaria.backend.domain.entity.Additional;
import br.edu.ufersa.pizzaria.backend.domain.entity.Border;
import br.edu.ufersa.pizzaria.backend.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.backend.domain.entity.Pizza;
import br.edu.ufersa.pizzaria.backend.utils.PizzaSizes;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class PizzaDTO {

    public record FlavorResponsePizza(
            Long id,
            String name,
            String description,
            PriceResponse price
    ) {
        public FlavorResponsePizza(Flavor flavor, PriceResponse price) {
            this(
                    flavor.getId(),
                    flavor.getName(),
                    flavor.getDescription(),
                    price
            );
        }
    }

    public record PizzaResponse(
            Long id,
            String name,
            String description,
            BigDecimal price,
            FlavorResponsePizza flavorOne,
            FlavorResponsePizza flavorTwo,
            Border border,
            List<Additional> aditionals,
            PizzaSizes size,
            String image
    ) {
        public PizzaResponse(Pizza pizza) {
            this(
                    pizza.getId(),
                    pizza.getName(),
                    pizza.getDescription(),
                    pizza.getPrice(),
                    new FlavorResponsePizza(pizza.getFlavorOne(), new PriceResponse(pizza.getFlavorOne().getPriceEntry(pizza.getSize()))),
                    pizza.getFlavorTwo() != null ? new FlavorResponsePizza(pizza.getFlavorTwo(), new PriceResponse(pizza.getFlavorTwo().getPriceEntry(pizza.getSize()))) : null,
                    pizza.getBorder(),
                    pizza.getAditionals(),
                    pizza.getSize(),
                    pizza.getImage()
            );
        }
    }

    public record PizzaCreate(
            String name,
            String description,
            @NotNull Long flavorOne,
            Long flavorTwo,
            Long border,
            List<Long> aditionals,
            @NotNull @Enumerated(EnumType.STRING) PizzaSizes size,
            String image
    ) {
        public Pizza toEntity() {
            return new Pizza(this);
        }
    }

    public record PizzaUpdate(
            @NotBlank Long id,
            @NotBlank String name,
            @NotBlank String description,
            @NotNull Long flavorOne,
            Long flavorTwo,
            Long border,
            List<Long> aditionals,
            @NotNull @Enumerated(EnumType.STRING) PizzaSizes size,
            String image
    ) {
        public Pizza toEntity() {
            Pizza pizza = new Pizza();
            pizza.setPizza(this);
            return pizza;
        }
    }
}