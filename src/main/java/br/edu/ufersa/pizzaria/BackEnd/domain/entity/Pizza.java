package br.edu.ufersa.pizzaria.backend.domain.entity;

import br.edu.ufersa.pizzaria.backend.api.dto.PizzaDTO.PizzaCreate;
import br.edu.ufersa.pizzaria.backend.api.dto.PizzaDTO.PizzaUpdate;
import br.edu.ufersa.pizzaria.backend.utils.PizzaSizes;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "product_id")
public class Pizza extends Product {

    @ManyToOne
    @JoinColumn(name = "flavor_one_id", nullable = false)
    private Flavor flavorOne;

    @ManyToOne
    @JoinColumn(name = "flavor_two_id", nullable = true)
    private Flavor flavorTwo;

    @ManyToOne
    @JoinColumn(name = "border_id", nullable = true)
    private Border border;

    @ManyToMany
    @JoinTable(
        name = "pizza_additional",                               // Nome da tabela Gerada
        joinColumns = @JoinColumn(name = "pizza_id"),            // Nome da coluna que referencia a entidade Pizza
        inverseJoinColumns = @JoinColumn(name = "additional_id") // Nome da coluna que referencia a entidade Additional
    )
    private List<Additional> aditionals = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PizzaSizes size;

    public Pizza(PizzaCreate pizzaCreate){
        this.setComunAttributes(
                pizzaCreate.name(), pizzaCreate.description(),
                pizzaCreate.image(), pizzaCreate.flavorOne(),
                pizzaCreate.flavorTwo(), pizzaCreate.border(),
                pizzaCreate.aditionals(), pizzaCreate.size()
        );
    }

    public void setPizza(PizzaUpdate pizzaUpdate) {
        this.setId(pizzaUpdate.id());
        this.setComunAttributes(
                pizzaUpdate.name(), pizzaUpdate.description(),
                pizzaUpdate.image(), pizzaUpdate.flavorOne(),
                pizzaUpdate.flavorTwo(), pizzaUpdate.border(),
                pizzaUpdate.aditionals(), pizzaUpdate.size()
        );
    }

    public void setComunAttributes(String name, String description, String image,
                                   Long flavorOne, Long flavorTwo, Long border, List<Long> aditionals, PizzaSizes size) {
        if (name != null && !name.isBlank()) {
            this.setName(name);
        }
        if (description != null && !description.isBlank()) {
            this.setDescription(description);
        }
        this.setImage(image);
        this.flavorOne = flavorOne != null ? new Flavor(flavorOne) : null;
        this.flavorTwo = flavorTwo != null ? new Flavor(flavorTwo) : null;
        this.border = border != null ? new Border(border) : null;
        this.aditionals = aditionals != null ? aditionals.stream().map(Additional::new).collect(Collectors.toList()) : null;
        this.size = size;
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal calculatedPrice = BigDecimal.ZERO;

        // Adiciona a metade da soma dos preços dos sabores
        if (flavorTwo != null) {
            calculatedPrice = flavorOne.getPriceEntry(size).getValue()
                    .add(flavorTwo.getPriceEntry(size).getValue())
                    .divide(BigDecimal.valueOf(2), new MathContext(10));
        }
        else {
            // Adiciona o preço do primeiro sabor
            calculatedPrice = calculatedPrice.add(flavorOne.getPriceEntry(size).getValue());
        }

        // Adiciona o preço da borda (caso exista)
        if (border != null) {
            calculatedPrice = calculatedPrice.add(border.getPrice());
        }

        // Adiciona o preço dos adicionais
        if (aditionals != null && !aditionals.isEmpty()) {
            for (Additional additional : aditionals) {
                calculatedPrice = calculatedPrice.add(additional.getPrice());
            }
        }

        // Retorna o preço total calculado somado ao preço base
        return calculatedPrice;
    }
}