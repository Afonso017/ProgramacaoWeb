package br.edu.ufersa.pizzaria.Michelangelo.api.dto;

import java.math.BigDecimal;

import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Product;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {
  public record ProductResponse(
      Long id,
      String name,
      String description,
      BigDecimal price,
      String image) {

    public ProductResponse(Product product) {
      this(
          product.getId(),
          product.getName(),
          product.getDescription(),
          product.getPrice(),
          product.getImage());
    }

    public Product toEntity() {
      return new Product(id, name, description, price, image);
    }
  }

  public record ProductCreate(
      @NotNull Long id) {

    public ProductCreate(Product product) {
      this(product.getId());
    }

    public Product toEntity() {
      return new Product(id);
    }
  }
}