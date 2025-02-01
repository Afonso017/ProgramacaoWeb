package br.edu.ufersa.pizzaria.BackEnd.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.OrderItem;
import br.edu.ufersa.pizzaria.BackEnd.domain.repository.ClientRepository;
import br.edu.ufersa.pizzaria.BackEnd.domain.repository.ProductRepository;
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
  private final ClientRepository clientRepository;
  private final ProductRepository productRepository;

  public OrderService(OrderRepository repository, ClientRepository clientRepository, ProductRepository productRepository) {
    this.repository = repository;
    this.clientRepository = clientRepository;
    this.productRepository = productRepository;
  }

  public List<OrderResponse> findAll() {
      return repository.findAll().stream().map(OrderResponse::new).collect(Collectors.toList());
  }

  public OrderResponse save(OrderCreate orderCreate) {
    LocalDateTime orderDate = LocalDateTime.now();
    Order newOrder = toEntity(orderCreate);
    newOrder.setOrderDate(orderDate);

    System.out.println("Ordem: " + newOrder.toString());

    repository.save(newOrder);

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

  public Order toEntity(OrderCreate orderCreate) {
    var client = clientRepository.findById(orderCreate.clientId())
        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    var orderItems = orderCreate.items().stream().map(orderItemCreate -> {
        var product = productRepository.findById(orderItemCreate.productId())
            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        return new OrderItem(product, orderItemCreate.quantity(), product.getPrice());
    }).toList();
    return new Order(client, OrderStatus.PENDING, orderItems, orderCreate.totalAmount());
  }
}