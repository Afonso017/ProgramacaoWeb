package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pizzas")

public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flavor_one_id", nullable = false)
    private Flavor flavorOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flavor_two_id", nullable = true) // O segundo sabor é opcional
    private Flavor flavorTwo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "border_id", nullable = true)
    private Border border;

    @ManyToMany
    @JoinTable(
            name = "pizza_aditionals",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "aditional_id")
    )
    private List<Aditional> aditionals;

    @Column(nullable = false)
    private String size; // Exemplo: Pequena, Média, Grande

    @Column(nullable = false)
    private Double totalPrice; // Preço total da pizza (incluindo adicionais e borda)

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flavor getFlavorOne() {
        return flavorOne;
    }

    public void setFlavorOne(Flavor flavorOne) {
        this.flavorOne = flavorOne;
    }

    public Flavor getFlavorTwo() {
        return flavorTwo;
    }

    public void setFlavorTwo(Flavor flavorTwo) {
        this.flavorTwo = flavorTwo;
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public List<Aditional> getAditionals() {
        return aditionals;
    }

    public void setAditionals(List<Aditional> aditionals) {
        this.aditionals = aditionals;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
