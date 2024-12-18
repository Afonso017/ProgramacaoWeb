package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "OrderItem")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  @JsonIgnore
  private Product product;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  @JsonIgnore
  private Order order;

  @Positive(message = "A quantidade deve ser maior que zero")
  private int quantity;

  @Positive(message = "O preço deve ser maior que zero")
  @Column(precision = 10, scale = 2, nullable = false)
  private BigDecimal price;

  public OrderItem() {
  }

  public OrderItem(Long id, Product product, Order order, int quantity, BigDecimal price) {
    this.id = id;
    this.product = product;
    this.order = order;
    this.quantity = quantity;
    this.price = price;
  }

  public OrderItem(Product product, int quantity, BigDecimal price) {
    this.product = product;
    this.quantity = quantity;
    this.price = price;
  }

  public OrderItem(Product product, Order order, int quantity, BigDecimal price) {
    this.product = product;
    this.order = order;
    this.quantity = quantity;
    this.price = price;
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
   * @return Product return the product
   */
  public Product getProduct() {
    return product;
  }

  /**
   * @param Long the product to set
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * @return Order return the order
   */
  public Order getOrder() {
    return order;
  }

  /**
   * @param order the order to set
   */
  public void setOrder(Order order) {
    this.order = order;
  }

  /**
   * @return int return the quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * @param quantity the quantity to set
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * @return BigDecimal return the price
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}