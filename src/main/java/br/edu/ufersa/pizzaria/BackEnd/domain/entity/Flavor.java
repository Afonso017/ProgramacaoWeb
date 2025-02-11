package br.edu.ufersa.pizzaria.backend.domain.entity;

import br.edu.ufersa.pizzaria.backend.api.dto.FlavorDTO.FlavorResponse;
import br.edu.ufersa.pizzaria.backend.api.dto.FlavorDTO.FlavorUpdate;
import br.edu.ufersa.pizzaria.backend.utils.PizzaSizes;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flavor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "O nome do sabor é obrigatório")
  @Column(unique = true)
  private String name;

  @NotBlank(message = "A descrição do sabor é obrigatória")
  private String description;

  @OneToMany(mappedBy = "flavor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JsonManagedReference
  @Size(min = 3, max = 3, message = "Lista de preço deve conter 3 elementos")
  private List<PriceEntry> price = new ArrayList<>(3);

  public Flavor(Long id){
    this.id = id;
  }

  public Flavor(String name, String description, List<PriceEntry> priceEntries) {
    this.name = name;
    this.description = description;
    setPrice(priceEntries);
  }

  public void setFlavor(FlavorUpdate flavorUpdate) {
    this.id = flavorUpdate.id();
    this.name = flavorUpdate.name();
    this.description = flavorUpdate.description();
    setPrice(flavorUpdate.toEntity().getPrice());
  }

  public void setPrice(List<PriceEntry> price) {
    if (this.price == null) {return;}
    this.price.clear();
    for (PriceEntry priceEntry : price) {
      priceEntry.setFlavor(this);
      this.price.add(priceEntry);
    }
  }

  public PriceEntry getPriceEntry(PizzaSizes size) {
    for (PriceEntry priceEntry : price) {
      if (priceEntry.getVariation() == size) {
        return priceEntry;
      }
    }

    return new PriceEntry(PizzaSizes.LARGE, BigDecimal.ZERO);
  }

  public FlavorResponse toResponse() {
    return new FlavorResponse(this);
  }
}