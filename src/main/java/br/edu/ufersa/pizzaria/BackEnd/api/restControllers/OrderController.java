package br.edu.ufersa.pizzaria.backend.api.restControllers;

import br.edu.ufersa.pizzaria.backend.api.dto.OrderDTO.*;
import br.edu.ufersa.pizzaria.backend.domain.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

  private final OrderService service;
  private final Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

  @GetMapping("/status/stream/{id}")
  public SseEmitter streamOrderStatus(@PathVariable Long id) {
    SseEmitter emitter = new SseEmitter(0L);
    sseEmitters.put(id, emitter);

    emitter.onCompletion(() -> sseEmitters.remove(id));
    emitter.onTimeout(() -> sseEmitters.remove(id));

    return emitter;
  }

  public OrderController(OrderService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> findAll() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<OrderResponse> save(@Valid @RequestBody OrderCreate orderCreate) {
    return new ResponseEntity<>(service.save(orderCreate), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderResponse> update(@PathVariable Long id,
      @Valid @RequestBody OrderUpdate orderUpdate) {
    return new ResponseEntity<>(service.update(id, orderUpdate), HttpStatus.OK);
  }

  @GetMapping("/status/{id}")
  public ResponseEntity<StatusOrderResponse> findStatus(@PathVariable Long id) {
    return new ResponseEntity<>(service.findStatus(id), HttpStatus.OK);
  }

  @PutMapping("/status/{id}")
  public ResponseEntity<OrderResponse> updateStatus(@PathVariable Long id,
      @Valid @RequestBody OrderUpdateStatus orderUpdateStatus) {

    // Atualize o status no banco de dados
    OrderResponse updatedOrder = service.updateStatus(id, orderUpdateStatus);

    // Envie o evento SSE para os clientes conectados
    SseEmitter emitter = sseEmitters.get(id);
    if (emitter != null) {
      try {
        emitter.send(SseEmitter.event()
            .name("statusUpdate")
            .data(orderUpdateStatus.status()));
      } catch (IOException e) {
        emitter.completeWithError(e);
      }
    }

    return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
  }

  @PutMapping("/items/{id}")
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