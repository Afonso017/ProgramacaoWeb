package br.edu.ufersa.pizzaria.backend.domain.service;

import br.edu.ufersa.pizzaria.backend.api.dto.OrderDTO.*;
import br.edu.ufersa.pizzaria.backend.domain.entity.Order;
import br.edu.ufersa.pizzaria.backend.domain.entity.OrderItem;
import br.edu.ufersa.pizzaria.backend.domain.entity.Pizza;
import br.edu.ufersa.pizzaria.backend.domain.repository.ClientRepository;
import br.edu.ufersa.pizzaria.backend.domain.repository.OrderRepository;
import br.edu.ufersa.pizzaria.backend.utils.OrderStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

  private final OrderRepository repository;
  private final ClientRepository clientRepository;
  private final PizzaService pizzaService;

  public OrderService(OrderRepository repository, ClientRepository clientRepository, PizzaService pizzaService) {
    this.repository = repository;
    this.clientRepository = clientRepository;
    this.pizzaService = pizzaService;
  }

  public List<OrderResponse> findAll() {
      return repository.findAll().stream().map(OrderResponse::new).collect(Collectors.toList());
  }

  public OrderResponse save(OrderCreate orderCreate) {
    for (OrderItemCreate item : orderCreate.items()) {
      if (item.product() instanceof Pizza pizza) {
        pizza = pizzaService.save(pizza);
        orderCreate.items().set(orderCreate.items().indexOf(item), new OrderItemCreate(pizza, item.quantity()));
      }
    }

    Order newOrder = toEntity(orderCreate);
    newOrder.setOrderDate(LocalDateTime.now().withNano(0));

    repository.save(newOrder);

    return new OrderResponse(newOrder);
  }

  public OrderResponse updateStatus(Long id, OrderUpdateStatus orderUpdate) {
    Order order = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

    validateOrderStatus(orderUpdate.status());

    order.setStatus(orderUpdate.status());
    repository.save(order);

    return new OrderResponse(order);
  }

  public OrderResponse updateItems(Long id, OrderUpdateItems items) {
    Order order = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

    validateOrderStatus(order.getStatus());

    for (OrderItemUpdate item : items.items()) {
      if (item.product() instanceof Pizza pizza) {
        System.out.println(pizza.getId());
        pizza = pizzaService.save(pizza);
        items.items().set(items.items().indexOf(item), new OrderItemUpdate(item.id(), pizza, item.quantity()));
      }
    }

    order.setItems(items.items().stream().map(OrderItemUpdate::toEntity).toList());

    repository.save(order);

    return new OrderResponse(order);
  }

  public StatusOrderResponse findStatus(Long id) {
    Order order = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

    return new StatusOrderResponse(order.getStatus());
  }

  public OrderResponse update(Long id, OrderUpdate orderUpdate) {
    Order order = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

    validateOrderStatus(order.getStatus());

    for (OrderItemUpdate item : orderUpdate.items()) {
      if (item.product() instanceof Pizza pizza) {
        pizza = pizzaService.save(pizza);
        orderUpdate.items().set(orderUpdate.items().indexOf(item), new OrderItemUpdate(item.id(), pizza, item.quantity()));
      }
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

  public Order toEntity(OrderCreate orderCreate) {
    var client = clientRepository.findById(orderCreate.clientId())
        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

    var orderItems = orderCreate.items().stream().map(orderItemCreate -> {
        return new OrderItem(orderItemCreate.product(), orderItemCreate.quantity());
    }).toList();

    return new Order(client, OrderStatus.PENDING, orderItems);
  }

  public void validateOrderStatus(OrderStatus order) {
    if (order == OrderStatus.FINALIZED || order == OrderStatus.DELIVERED) {
      throw new IllegalArgumentException("Não é possível alterar o status de um pedido finalizado ou entregue");
    }
  }
}