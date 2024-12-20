package br.edu.ufersa.pizzaria.Michelangelo.api.restControllers;

import br.edu.ufersa.pizzaria.Michelangelo.api.dto.PizzaDTO.PizzaCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.PizzaDTO.PizzaResponse;
import br.edu.ufersa.pizzaria.Michelangelo.domain.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import java.util.List;

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
