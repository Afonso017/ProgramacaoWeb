package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Inheritance;

@Entity
@Table(name = "Product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  @NotNull
  @Column(precision = 10, scale = 2)
  private BigDecimal price;

  private String image;

  public Product() {
  }

  public Product(String name, String description, BigDecimal price, String image) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.image = image;
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
   * @return String return the descrition
   */
  public String getDescrition() {
    return description;
  }

  /**
   * @param description the descrition to set
   */
  public void setDescrition(String description) {
    this.description = description;
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

  /**
   * @return String return the image
   */
  public String getImage() {
    return image;
  }

  /**
   * @param image the image to set
   */
  public void setImage(String image) {
    this.image = image;
  }

}