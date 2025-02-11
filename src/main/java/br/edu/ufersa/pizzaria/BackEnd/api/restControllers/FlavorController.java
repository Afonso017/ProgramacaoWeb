package br.edu.ufersa.pizzaria.backend.api.restControllers;

import br.edu.ufersa.pizzaria.backend.api.dto.FlavorDTO.FlavorCreate;
import br.edu.ufersa.pizzaria.backend.api.dto.FlavorDTO.FlavorResponse;
import br.edu.ufersa.pizzaria.backend.api.dto.FlavorDTO.FlavorUpdate;
import br.edu.ufersa.pizzaria.backend.domain.service.FlavorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flavor")
public class FlavorController {

  private final FlavorService service;

  public FlavorController(FlavorService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<FlavorResponse>> findAll() {
    return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<FlavorResponse> create(@Valid @RequestBody FlavorCreate flavor) {
    return new ResponseEntity<>(service.save(flavor), HttpStatus.CREATED);
  }

  @PutMapping()
  public ResponseEntity<FlavorResponse> update(@Valid @RequestBody FlavorUpdate flavor) {
    return new ResponseEntity<>(service.update(flavor), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<FlavorResponse> findById(@PathVariable Long id) {
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }
}