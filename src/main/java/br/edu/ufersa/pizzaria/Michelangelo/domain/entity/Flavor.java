package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_flavor")
public class Flavor {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  @NotNull
  @OneToMany
  private List<PriceEntry> price;

  public Flavor(String name, String description, List<PriceEntry> price) {
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public Flavor() {
  }

  /**
   * @return String return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return String return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return List<Map<Character, Float>> return the price
   */
  public List<PriceEntry> getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(List<PriceEntry> price) {
    this.price = price;
  }
}