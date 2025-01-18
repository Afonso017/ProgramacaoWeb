package br.edu.ufersa.pizzaria.BackEnd.domain.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.FlavorDTO.FlavorCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.FlavorDTO.FlavorUpdate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.PriceDTO.PriceCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.PriceDTO.PriceUpdate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import utils.PizzaSizes;

@Entity
@Table(name = "flavor")
public class Flavor {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotBlank(message = "O nome do sabor é obrigatório")
  @Column(unique = true)
  private String name;

  @NotBlank(message = "A descrição do sabor é obrigatória")
  private String description;

  @OneToMany(mappedBy = "flavor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<PriceEntry> price = new ArrayList<>();

  public Flavor() {
  }

  public Flavor(String name, String description, List<PriceEntry> priceEntries) {
    this.name = name;
    this.description = description;
    setPrice(priceEntries);
  }

  public Flavor(Long id, String name, String description, List<PriceEntry> priceEntries) {
    this.id = id;
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
    // Limpa os itens existentes na lista.
    this.price.clear();

    // Adiciona os novos preços e garante a associação bidirecional.
    for (PriceEntry priceEntry : price) {
      priceEntry.setFlavor(this); // Configura a relação bidirecional.
      this.price.add(priceEntry); // Adiciona à lista existente.
    }
  }

  // Função que recebe um PizzaSizes e retorna o preço associado a ele
  public BigDecimal getPriceEntry(PizzaSizes size) {
    for (PriceEntry priceEntry : price) {
      if (priceEntry.getVariation() == size) {
        return priceEntry.getValue();
      }
    }

    return null;
  }

  public void setFlavor(FlavorCreate flavorCreate) {
    this.setName(flavorCreate.name());
    this.setDescription(flavorCreate.description());
    this.setPrice(
        flavorCreate.price().stream().map(PriceCreate::toEntity).collect(java.util.stream.Collectors.toList()));
  }

  public void setFlavor(FlavorUpdate flavorUpdate) {
    this.setName(flavorUpdate.name());
    this.setDescription(flavorUpdate.description());
    this.setPrice(
        flavorUpdate.price().stream().map(PriceUpdate::toEntity).collect(java.util.stream.Collectors.toList()));
  }
}