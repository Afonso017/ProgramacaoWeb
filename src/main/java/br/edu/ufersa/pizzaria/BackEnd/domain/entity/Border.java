package br.edu.ufersa.pizzaria.backend.domain.entity;

import br.edu.ufersa.pizzaria.backend.api.dto.BorderDTO.*;
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
public class Border {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "O nome da borda é obrigatório")
  private String name;

  @PositiveOrZero(message = "O preço da borda deve ser maior ou igual a zero")
  @Column(precision = 10, scale = 2, nullable = false)
  private BigDecimal price;

  public Border(Long id) {
    this.id = id;
  }

  public Border(String name, BigDecimal price) {
    this.name = name;
    this.price = price;
  }

  public void setBorder(BorderCreate border) {
    this.name = border.name();
    this.price = border.price();
  }
}