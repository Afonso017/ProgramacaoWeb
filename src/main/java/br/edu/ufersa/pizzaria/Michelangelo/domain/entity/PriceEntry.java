
package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import utils.PizzaSizes;

@Entity
public class PriceEntry {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private PizzaSizes variation;

  @Column(precision = 10, scale = 2)
  private BigDecimal value;

  public PriceEntry() {
  }

  public PriceEntry(PizzaSizes variation, BigDecimal value) {
    this.variation = variation;
    this.value = value;
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

}