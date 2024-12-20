package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import utils.PizzaSizes;

@Entity
@Table(name = "price_entry")
public class PriceEntry {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Enumerated(EnumType.STRING)
  private PizzaSizes variation;

  @Column(precision = 10, scale = 2)
  private BigDecimal value;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flavor_id", nullable = false)
  private Flavor flavor;

  public PriceEntry() {
  }

  public PriceEntry(Long id, PizzaSizes variation, BigDecimal value, Flavor flavor) {
    this.id = id;
    this.variation = variation;
    this.value = value;
    this.flavor = flavor;
  }

  public PriceEntry(PizzaSizes variation, BigDecimal value, Flavor flavor) {
    this.variation = variation;
    this.value = value;
    this.flavor = flavor;
  }

  /**
   * @return Long return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return PizzaSizes return the variation
   */
  public PizzaSizes getVariation() {
    return variation;
  }

  /**
   * @param variation the variation to set
   */
  public void setVariation(PizzaSizes variation) {
    this.variation = variation;
  }

  /**
   * @return BigDecimal return the value
   */
  public BigDecimal getValue() {
    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(BigDecimal value) {
    this.value = value;
  }

  /**
   * @return Flavor return the flavor
   */
  public Flavor getFlavor() {
    return flavor;
  }

  /**
   * @param flavor the flavor to set
   */
  public void setFlavor(Flavor flavor) {
    if (flavor == null) {
      throw new IllegalArgumentException("Flavor não pode ser nulo em PriceEntry");
    }
    this.flavor = flavor;
  }
}