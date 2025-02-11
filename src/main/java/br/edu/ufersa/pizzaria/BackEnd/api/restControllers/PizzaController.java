package br.edu.ufersa.pizzaria.backend.api.restControllers;

import br.edu.ufersa.pizzaria.backend.api.dto.PizzaDTO.PizzaCreate;
import br.edu.ufersa.pizzaria.backend.api.dto.PizzaDTO.PizzaResponse;
import br.edu.ufersa.pizzaria.backend.api.dto.PizzaDTO.PizzaUpdate;
import br.edu.ufersa.pizzaria.backend.domain.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

// Apenas para o caso de precisamos, por enquanto irá ficar!

@RestController
@RequestMapping("/api/v1/vendas/pizzas")
public class PizzaController {

  private final PizzaService service;

  public PizzaController(PizzaService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<PizzaResponse>> listAll() {
    return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<PizzaResponse> create(@Valid @RequestBody PizzaCreate pizzaCreate) {
    return new ResponseEntity<>(service.save(pizzaCreate), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PizzaResponse> update(@PathVariable Long id, @Valid @RequestBody PizzaUpdate pizzaUpdate) {
    return new ResponseEntity<>(service.update(id, pizzaUpdate), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PizzaResponse> findById(@PathVariable Long id) {
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }

  @GetMapping("/prices")
  public ResponseEntity<BigDecimal> findPricePizza(Long id) {
      return new ResponseEntity<>(service.findPricePizza(id), HttpStatus.OK);
  }
}
