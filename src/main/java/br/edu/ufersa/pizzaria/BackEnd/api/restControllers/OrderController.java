package br.edu.ufersa.pizzaria.BackEnd.api.restControllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderResponse;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderUpdate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderUpdateItems;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderUpdateStatus;
import br.edu.ufersa.pizzaria.BackEnd.domain.service.OrderService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/order")
public class OrderController {

  private final OrderService service;

  public OrderController(OrderService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> findAll() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderCreate orderCreate) {
    return new ResponseEntity<>(service.save(orderCreate), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderResponse> update(@PathVariable Long id,
      @Valid @RequestBody OrderUpdate orderUpdate) {
    return new ResponseEntity<>(service.update(id, orderUpdate), HttpStatus.OK);
  }

  @PutMapping("/{id}/status")
  public ResponseEntity<OrderResponse> updateStatus(@PathVariable Long id,
      @Valid @RequestBody OrderUpdateStatus orderUpdateStatus) {
    return new ResponseEntity<>(service.updateStatus(id, orderUpdateStatus), HttpStatus.OK);
  }

  @PutMapping("/{id}/items")
  public ResponseEntity<OrderResponse> updateItems(@PathVariable Long id,
      @Valid @RequestBody OrderUpdateItems orderUpdateItems) {
    return new ResponseEntity<>(service.updateItems(id, orderUpdateItems), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }
}