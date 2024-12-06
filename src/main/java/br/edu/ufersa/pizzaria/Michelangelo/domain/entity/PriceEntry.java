
package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class PriceEntry {
  @Id
  @GeneratedValue
  private Long id;

  @Column(precision = 5, scale = 2)
  private Character key;

  private Float value;

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
   * @return Character return the key
   */
  public Character getKey() {
    return key;
  }

  /**
   * @param key the key to set
   */
  public void setKey(Character key) {
    this.key = key;
  }

  /**
   * @return Float return the value
   */
  public Float getValue() {
    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(Float value) {
    this.value = value;
  }
}