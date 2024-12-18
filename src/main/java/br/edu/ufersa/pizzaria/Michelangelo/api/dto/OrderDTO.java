package br.edu.ufersa.pizzaria.Michelangelo.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.ufersa.pizzaria.Michelangelo.api.dto.ProductDTO.ProductCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.ProductDTO.ProductResponse;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Client;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Order;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.OrderItem;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Product;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import utils.OrderStatus;

public class OrderDTO {

  // DTO's responses

  public record OrderItemResponse(
      ProductResponse productId,
      Long orderId,
      Integer quantity,
      BigDecimal price) {

    public OrderItemResponse(OrderItem orderItem) {
      this(
          new ProductResponse(orderItem.getProduct()),
          orderItem.getOrder().getId(),
          orderItem.getQuantity(),
          orderItem.getPrice());
    }

    public OrderItem toEntity() {
      Order order = new Order();
      order.setId(orderId);

      return new OrderItem(productId.toEntity(), order, quantity, price);
    }
  }

  public record OrderResponse(
      Client clientId,
      LocalDateTime orderDate,
      OrderStatus status,
      List<OrderItemResponse> items,
      BigDecimal totalAmount) {

    public OrderResponse(Order order) {
      this(
          order.getClient(),
          order.getOrderDate(),
          order.getStatus(),
          order.getItems().stream().map(OrderItemResponse::new).collect(Collectors.toList()),
          order.getTotalAmount());
    }
  }

  // -----------------------------------------------------------------------------------
  // DTO's requests

  // DTO para criação de um item do pedido, esperando os objetos completos
  public record OrderItemCreate(
      @NotNull ProductCreate productId,
      @Positive Integer quantity,
      @Positive BigDecimal price) {

    public OrderItem toEntity() {
      return new OrderItem(productId.toEntity(), quantity, price);
    }
  }

  public record OrderCreate(
      @NotNull Client clientId,
      @NotNull @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime orderDate,
      @NotNull @Size(min = 1) List<OrderItemCreate> items,
      @NotNull @Positive BigDecimal totalAmount) {

    public Order toEntity() {
      Order order = new Order(clientId, orderDate, OrderStatus.PENDING,
          items.stream().map(OrderItemCreate::toEntity).collect(Collectors.toList()), totalAmount);

      for (OrderItem item : order.getItems()) {
        item.setOrder(order);
      }

      return order;
    }
  }

  public record OrderItemUpdate(
      @NotNull Long id,
      @NotNull ProductCreate productId,
      @NotNull Order orderId,
      @Positive Integer quantity,
      @Positive BigDecimal price) {

    public OrderItem toEntity() {
      return new OrderItem(id, productId.toEntity(), orderId, quantity, price);
    }
  }

  public record OrderUpdate(
      @NotNull Long id,
      @NotNull Client clientId,
      @NotNull @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime orderDate,
      @NotNull @Enumerated(EnumType.STRING) OrderStatus status,
      @NotNull @Size(min = 1) List<OrderItemUpdate> items,
      @NotNull @Positive BigDecimal totalAmount) {

    public Order toEntity() {
      Order order = new Order(id, clientId, orderDate, status,
          items.stream().map(OrderItemUpdate::toEntity).collect(Collectors.toList()), totalAmount);

      for (OrderItem item : order.getItems()) {
        item.setOrder(order);
      }

      return order;
    }
  }

  public record OrderUpdateItems(
      @NotNull @Size(min = 1) List<OrderItemUpdate> items,
      @NotNull @Positive BigDecimal totalAmount) {
  }

  public record OrderUpdateStatus(
      @NotNull @Enumerated(EnumType.STRING) OrderStatus status) {
  }
}