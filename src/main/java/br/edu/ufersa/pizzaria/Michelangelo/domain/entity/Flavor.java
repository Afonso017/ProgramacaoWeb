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
@Table(name = "flavor")
public class Flavor {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "O nome do sabor é obrigatório")
  private String name;

  @NotBlank(message = "A descrição do sabor é obrigatória")
  private String description;

  @NotNull
  @OneToMany
  private List<PriceEntry> price;

  public Flavor() {
  }

  public Flavor(String name, String description, List<PriceEntry> price) {
    this.name = name;
    this.description = description;
    this.price = price;
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