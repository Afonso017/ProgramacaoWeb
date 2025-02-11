package br.edu.ufersa.pizzaria.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  @JsonIgnore
  private Order order;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  @JsonIgnore
  private Product product;

  @Positive(message = "A quantidade deve ser maior que zero")
  @Column(nullable = false)
  private Integer quantity;

  @Positive(message = "O preço deve ser maior que zero")
  @Column(precision = 10, scale = 2, nullable = false)
  private BigDecimal price;

  public OrderItem(Product product, Integer quantity) {
    this.product = product;
    this.quantity = quantity;
    this.setPrice();
  }

  public OrderItem(Long id,Product product, Integer quantity) {
    this.id = id;
    this.product = product;
    this.quantity = quantity;
    this.setPrice();
  }

  public OrderItem(Long id, Product product, Order order, Integer quantity) {
    this.id = id;
    this.product = product;
    this.order = order;
    this.quantity = quantity;
    this.setPrice();
  }

  public void setPrice() {
    if (this.product != null) {
      this.price = product.getPrice().multiply(BigDecimal.valueOf(quantity.longValue()));
    }
  }
}