package br.edu.ufersa.pizzaria.BackEnd.domain.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderItemUpdate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderResponse;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderUpdate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderUpdateItems;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderUpdateStatus;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.StatusOrderResponse;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Order;
import br.edu.ufersa.pizzaria.BackEnd.domain.repository.OrderRepository;
import utils.OrderStatus;

@Service
public class OrderService {

  private final OrderRepository repository;

  public OrderService(OrderRepository repository) {
    this.repository = repository;
  }

  public List<OrderResponse> findAll() {
    List<OrderResponse> orders = repository.findAllWithItems()
        .stream().map(order -> new OrderResponse(order))
        .collect(Collectors.toList());

    return orders;
  }

  public OrderResponse save(OrderCreate orderCreate) {
    Order newOrder = repository.save(orderCreate.toEntity());

    return new OrderResponse(newOrder);
  }

  public OrderResponse updateStatus(Long id, OrderUpdateStatus orderUpdate) {
    Order order = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

    // Atualiza o pedido, caso o status não seja de pedido finalizado ou entregue
    if (order.getStatus() == OrderStatus.FINALIZED || order.getStatus() == OrderStatus.DELIVERED) {
      throw new IllegalArgumentException("Não é possível alterar o status de um pedido finalizado ou entregue");
    }

    order.setStatus(orderUpdate.status());
    repository.save(order);

    return new OrderResponse(order);
  }

  public OrderResponse updateItems(Long id, OrderUpdateItems items) {
    Order order = repository.findByIdWithItems(id)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

    // Atualiza o pedido, caso o status não seja de pedido finalizado ou entregue
    if (order.getStatus() == OrderStatus.FINALIZED || order.getStatus() == OrderStatus.DELIVERED) {
      throw new IllegalArgumentException("Não é possível alterar o status de um pedido finalizado ou entregue");
    }

    order.setItems(items.items().stream().map(OrderItemUpdate::toEntity).collect(Collectors.toList()));
    order.setTotalAmount(items.totalAmount());
    repository.save(order);

    return new OrderResponse(order);
  }

  public StatusOrderResponse findStatus(Long id) {
    return new StatusOrderResponse(repository.findOrderStatusById(id));
  }

  public OrderResponse update(Long id, OrderUpdate orderUpdate) {
    Order order = repository.findByIdWithItems(id)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

    // Atualiza o pedido, caso o status não seja de pedido finalizado ou entregue
    if (order.getStatus() == OrderStatus.FINALIZED || order.getStatus() == OrderStatus.DELIVERED) {
      throw new IllegalArgumentException("Não é possível alterar o status de um pedido finalizado ou entregue");
    }

    order.setOrder(orderUpdate);

    repository.save(order);

    return new OrderResponse(order);
  }

  public void delete(Long id) {
    Order order = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

    repository.delete(order);
  }

  public OrderResponse findById(Long id) {
    Order order = repository.findByIdWithItems(id)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

    return new OrderResponse(order);
  }
}