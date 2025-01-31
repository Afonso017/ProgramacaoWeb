package br.edu.ufersa.pizzaria.BackEnd.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonFormat;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.ProductDTO.ProductCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.ProductDTO.ProductResponse;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Client;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Order;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.OrderItem;
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
      Integer quantity) {

    public OrderItemResponse(OrderItem orderItem) {
      this(
          new ProductResponse(orderItem.getProduct()),
          orderItem.getQuantity());
    }

    public OrderItem toEntity() {
      return new OrderItem(productId.toEntity(), quantity);
    }
  }

  public record OrderResponse(
      Long id,
      Client clientId,
      LocalDateTime orderDate,
      OrderStatus status,
      List<OrderItemResponse> items,
      BigDecimal totalAmount) {

    public OrderResponse(Order order) {
      this(
          order.getId(),
          order.getClient(),
          order.getOrderDate(),
          order.getStatus(),
          order.getItems().stream().map(OrderItemResponse::new).collect(Collectors.toList()),
          order.getTotalAmount());
    }
  }

  public record StatusOrderResponse(
      OrderStatus status) {

    public StatusOrderResponse(OrderStatus status) {
      this.status = status;
    }
  }

  // -----------------------------------------------------------------------------------
  // DTO's requests

  // DTO para criação de um item do pedido, esperando os objetos completos
  public record OrderItemCreate(
      @NotNull Long productId,
      @Positive Integer quantity) {
  }

  public record OrderCreate(
      @NotNull Long clientId,
      @NotNull @Size(min = 1) List<OrderItemCreate> items,
      @NotNull @Positive BigDecimal totalAmount) {
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
      return new Order(clientId, status,
          items.stream().map(OrderItemUpdate::toEntity).collect(Collectors.toList()), totalAmount);
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