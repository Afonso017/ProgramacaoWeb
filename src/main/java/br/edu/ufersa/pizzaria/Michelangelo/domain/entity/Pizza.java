package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import utils.PizzaSizes;
import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private PizzaSizes size;

    public Pizza() {
    }

    public Pizza(String name, String description, BigDecimal price, String image, Flavor flavorOne, Flavor flavorTwo,
            Border border, List<Additional> aditionals, PizzaSizes size) {
        super(name, description, price, image);
        this.flavorOne = flavorOne;
        this.flavorTwo = flavorTwo;
        this.border = border;
        this.aditionals = aditionals;
        this.size = size;
    }

    /**
     * @return Flavor return the flavorOne
     */
    public Flavor getFlavorOne() {
        return flavorOne;
    }

    /**
     * @param flavorOne the flavorOne to set
     */
    public void setFlavorOne(Flavor flavorOne) {
        this.flavorOne = flavorOne;
    }

    /**
     * @return Flavor return the flavorTwo
     */
    public Flavor getFlavorTwo() {
        return flavorTwo;
    }

    /**
     * @param flavorTwo the flavorTwo to set
     */
    public void setFlavorTwo(Flavor flavorTwo) {
        this.flavorTwo = flavorTwo;
    }

    /**
     * @return Border return the border
     */
    public Border getBorder() {
        return border;
    }

    /**
     * @param border the border to set
     */
    public void setBorder(Border border) {
        this.border = border;
    }

    /**
     * @return List<Additional> return the aditionals
     */
    public List<Additional> getAditionals() {
        return aditionals;
    }

    /**
     * @param aditionals the aditionals to set
     */
    public void setAditionals(List<Additional> aditionals) {
        this.aditionals = aditionals;
    }

    /**
     * @return PizzaSizes return the size
     */
    public PizzaSizes getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(PizzaSizes size) {
        this.size = size;
    }

}