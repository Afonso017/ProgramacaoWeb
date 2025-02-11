package br.edu.ufersa.pizzaria.backend.api.restControllers;

import br.edu.ufersa.pizzaria.backend.api.dto.AdditionalDTO.AdditionalCreate;
import br.edu.ufersa.pizzaria.backend.api.dto.AdditionalDTO.AdditionalResponse;
import br.edu.ufersa.pizzaria.backend.domain.service.AdditionalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/additional")
public class AdditionalController {

  private final AdditionalService service;

  public AdditionalController(AdditionalService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<AdditionalResponse>> findAll() {
    return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<AdditionalResponse> create(@Valid @RequestBody AdditionalCreate additional) {
    return new ResponseEntity<>(service.save(additional), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AdditionalResponse> update(@PathVariable Long id, @Valid @RequestBody AdditionalCreate additional) {
    return new ResponseEntity<>(service.update(id, additional), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<AdditionalResponse> findById(@PathVariable Long id) {
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }
}