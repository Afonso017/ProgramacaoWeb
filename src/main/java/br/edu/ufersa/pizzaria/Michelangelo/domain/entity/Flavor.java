package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "flavor")
public class Flavor {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotBlank(message = "O nome do sabor é obrigatório")
  private String name;

  @NotBlank(message = "A descrição do sabor é obrigatória")
  private String description;

  @OneToMany(mappedBy = "flavor", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PriceEntry> price = new ArrayList<>();

  public Flavor() {
  }

  public Flavor(String name, String description, List<PriceEntry> priceEntries) {
    this.name = name;
    this.description = description;
    setPrice(priceEntries);
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
   * @return List<PriceEntry> return the price
   */
  public List<PriceEntry> getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(List<PriceEntry> price) {
    // Remove todos os preços antigos
    if (this.price != null) {
      this.price.clear();
    }

    // Adiciona os novos preços e garante a associação bidirecional
    for (PriceEntry priceEntry : price) {
      priceEntry.setFlavor(this);
    }

    this.price = price;
  }
}