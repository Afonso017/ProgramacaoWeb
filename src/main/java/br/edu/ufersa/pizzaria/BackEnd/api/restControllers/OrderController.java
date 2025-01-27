package br.edu.ufersa.pizzaria.BackEnd.api.restControllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.StatusOrderResponse;
import br.edu.ufersa.pizzaria.BackEnd.domain.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/api/v1/order")
public class OrderController {

  private final OrderService service;
  private final Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

  @GetMapping("/status/stream/{id}")
  public SseEmitter streamOrderStatus(@PathVariable Long id) {
    SseEmitter emitter = new SseEmitter(0L); // 0L indica sem timeout
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

  @GetMapping("/status/{id}")
  public ResponseEntity<StatusOrderResponse> findStatus(@RequestParam Long id) {
    return new ResponseEntity<>(service.findStatus(id), HttpStatus.OK);
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
            .data(orderUpdateStatus.status().getStatus()));
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