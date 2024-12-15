package br.edu.ufersa.pizzaria.Michelangelo.api.restControllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.BorderDTO.BorderCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.BorderDTO.BorderResponse;
import br.edu.ufersa.pizzaria.Michelangelo.domain.service.BorderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/api/v1/border")
public class BorderController {

  private final BorderService service;

  public BorderController(BorderService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<BorderResponse>> listAll() {
    return new ResponseEntity<List<BorderResponse>>(service.listAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<BorderResponse> create(@Valid @RequestBody BorderCreate border) {
    return new ResponseEntity<BorderResponse>(service.create(border), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BorderResponse> update(@PathVariable Long id, @Valid @RequestBody BorderCreate border) {
    return new ResponseEntity<>(service.update(id, border), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<BorderResponse> findById(@PathVariable Long id) {
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }
}