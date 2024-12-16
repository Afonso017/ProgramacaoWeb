package br.edu.ufersa.pizzaria.Michelangelo.api.restControllers;

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
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.AdditionalDTO.AdditionalCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.AdditionalDTO.AdditionalResponse;
import br.edu.ufersa.pizzaria.Michelangelo.domain.service.AdditionalService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/additional")
public class AdditionalController {

  private final AdditionalService service;

  public AdditionalController(AdditionalService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<AdditionalResponse>> findAll() {
    return new ResponseEntity<List<AdditionalResponse>>(service.listAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<AdditionalResponse> create(@Valid @RequestBody AdditionalCreate additional) {
    return new ResponseEntity<AdditionalResponse>(service.save(additional), HttpStatus.CREATED);
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