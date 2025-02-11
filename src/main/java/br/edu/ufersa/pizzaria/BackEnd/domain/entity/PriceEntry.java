package br.edu.ufersa.pizzaria.backend.domain.entity;

import br.edu.ufersa.pizzaria.backend.utils.PizzaSizes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PriceEntry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private PizzaSizes variation;

  @Column(precision = 10, scale = 2)
  private BigDecimal value;

  @ManyToOne()
  @JoinColumn(name = "flavor_id", nullable = false)
  @JsonBackReference
  private Flavor flavor;

  public PriceEntry(PizzaSizes variation, BigDecimal value) {
    this.variation = variation;
    this.value = value;
  }

  public PriceEntry(PizzaSizes variation, BigDecimal value, Flavor flavor) {
    this.variation = variation;
    this.value = value;
    this.flavor = flavor;
  }
}