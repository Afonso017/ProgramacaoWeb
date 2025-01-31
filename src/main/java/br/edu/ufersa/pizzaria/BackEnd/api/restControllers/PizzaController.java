package br.edu.ufersa.pizzaria.BackEnd.api.restControllers;

import br.edu.ufersa.pizzaria.BackEnd.api.dto.PizzaDTO.PizzaCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.PizzaDTO.PizzaResponse;
import br.edu.ufersa.pizzaria.BackEnd.domain.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/api/v1/pizza")
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
  public ResponseEntity<PizzaResponse> update(@PathVariable Long id, @Valid @RequestBody PizzaCreate pizzaCreate) {
    return new ResponseEntity<>(service.update(id, pizzaCreate), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build(); // HTTP 204 No Content
  }

  @GetMapping("/{id}")
  public ResponseEntity<PizzaResponse> findById(@PathVariable Long id) {
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }
}
