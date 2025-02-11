package br.edu.ufersa.pizzaria.backend.domain.entity;

import br.edu.ufersa.pizzaria.backend.api.dto.ProductDTO.ProductCreate;
import br.edu.ufersa.pizzaria.backend.api.dto.ProductDTO.ProductResponse;
import br.edu.ufersa.pizzaria.backend.api.dto.ProductDTO.ProductUpdate;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "productType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Pizza.class, name = "pizza")
})
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

  public Product(Long id) {
    this.id = id;
  }

  public Product(String name, String description, BigDecimal price, String image) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.image = image;
  }

  public Product(ProductResponse productResponse) {
    this.id = productResponse.id();
    this.name = productResponse.name();
    this.price = productResponse.price();
  }

  public Product(ProductCreate productCreate) {
    this.name = productCreate.name();
    this.description = productCreate.description();
    this.price = productCreate.price();
    this.image = productCreate.image();
  }

  public Product(ProductUpdate productUpdate) {
    this.id = productUpdate.id();
    this.name = productUpdate.name();
    this.description = productUpdate.description();
    this.price = productUpdate.price();
    this.image = productUpdate.image();
  }
}