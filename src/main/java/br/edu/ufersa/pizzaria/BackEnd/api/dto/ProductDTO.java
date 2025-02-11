package br.edu.ufersa.pizzaria.backend.api.dto;

import br.edu.ufersa.pizzaria.backend.domain.entity.Product;
import java.math.BigDecimal;

public class ProductDTO {

    public record ProductResponse(
            Long id,
            String name,
            String description,
            BigDecimal price,
            String image
    ) {
        public ProductResponse(Product product) {
            this(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getImage()
            );
        }
    }

    public record ProductCreate(
            String name,
            String description,
            BigDecimal price,
            String image
    ) {
        public Product toEntity() {
            return new Product(name, description, price, image);
        }
    }

    public record ProductUpdate(
            Long id,
            String name,
            String description,
            BigDecimal price,
            String image
    ) {
        public Product toEntity() {
            return new Product(id, name, description, price, image);
        }
    }
}