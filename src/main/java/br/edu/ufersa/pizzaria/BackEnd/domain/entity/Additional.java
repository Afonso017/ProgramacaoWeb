package br.edu.ufersa.pizzaria.backend.domain.entity;

import br.edu.ufersa.pizzaria.backend.api.dto.AdditionalDTO.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Additional {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "O nome do adicional é obrigatório")
  @Column(unique = true)
  private String name;

  @NotBlank(message = "A descrição do adicional é obrigatória")
  private String description;

  @PositiveOrZero(message = "O preço do adicional deve ser maior ou igual a zero")
  @Column(precision = 10, scale = 2, nullable = false)
  private BigDecimal price;

  private String image;

  public Additional(Long id) {
    this.id = id;
  }

  public Additional(String name, String description, BigDecimal price, String image) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.image = image;
  }

  public void setAdditional(AdditionalCreate additional) {
    this.name = additional.name();
    this.description = additional.description();
    this.price = additional.price();
    this.image = additional.image();
  }
}