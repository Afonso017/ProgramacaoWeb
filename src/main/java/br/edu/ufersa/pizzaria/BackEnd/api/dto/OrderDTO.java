package br.edu.ufersa.pizzaria.backend.api.dto;

import br.edu.ufersa.pizzaria.backend.api.dto.ProductDTO.ProductResponse;
import br.edu.ufersa.pizzaria.backend.domain.entity.Order;
import br.edu.ufersa.pizzaria.backend.domain.entity.OrderItem;
import br.edu.ufersa.pizzaria.backend.domain.entity.Product;
import br.edu.ufersa.pizzaria.backend.utils.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDTO {

  // DTO's responses
  public record OrderItemResponse(
      Long id,
      ProductResponse product,
      Integer quantity,
      BigDecimal itemPrice
  ) {

    public OrderItemResponse(OrderItem orderItem) {
      this(
          orderItem.getId(),
          new ProductResponse(orderItem.getProduct()),
          orderItem.getQuantity(),
          orderItem.getProduct().getPrice()
      );
    }

    public OrderItem toEntity() {
      return new OrderItem(id,new Product(product), quantity);
    }
  }

  public record OrderResponse(
      Long id,
      Long clientId,
      LocalDateTime orderDate,
      OrderStatus status,
      List<OrderItemResponse> items,
      BigDecimal totalAmount
  ) {
    public OrderResponse(Order order) {
      this(
          order.getId(),
          order.getClient().getId(),
          order.getOrderDate(),
          order.getStatus(),
          order.getItems().stream().map(OrderItemResponse::new).collect(Collectors.toList()),
          order.getTotalAmount()
      );
    }
  }

  public record StatusOrderResponse(
      OrderStatus status
  ) {
  }

  // DTO's requests

  public record OrderItemCreate(
      @NotNull Product product,
      @Positive @NotNull Integer quantity
  ) {
    public OrderItem toEntity() {
      return new OrderItem(product, quantity);
    }
  }

  public record OrderCreate(
      @NotNull Long clientId,
      @NotNull @Size(min = 1) List<OrderItemCreate> items
  ) {
  }

  public record OrderUpdate(
          @NotNull Long id,
          @NotNull Long clientId,
          @NotNull @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime orderDate,
          @NotNull @Enumerated(EnumType.STRING) OrderStatus status,
          @NotNull @Size(min = 1) List<OrderItemUpdate> items
  ) {
  }

  public record OrderItemUpdate(
      @NotNull Long id,
      @NotNull Product product,
      @Positive Integer quantity
  ) {
    public OrderItem toEntity() {
      return new OrderItem(id, product, quantity);
    }
  }

  public record OrderUpdateItems(
      @NotNull @Size(min = 1) List<OrderItemUpdate> items
  ) {
  }

  public record OrderUpdateStatus(
      @NotNull @Enumerated(EnumType.STRING) OrderStatus status
  ) {
  }
}