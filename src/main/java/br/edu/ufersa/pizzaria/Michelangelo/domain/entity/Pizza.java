package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import utils.PizzaSizes;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Pizza")
public class Pizza extends Product {
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
    @JoinTable(name = "pizza_additional", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "additional_id"))
    private List<Additional> aditionals;

    @Column(nullable = false)
    private PizzaSizes size;
}