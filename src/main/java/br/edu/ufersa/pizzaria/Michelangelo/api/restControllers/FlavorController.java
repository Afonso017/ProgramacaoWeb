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
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.FlavorDTO.FlavorCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.FlavorDTO.FlavorResponse;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.FlavorDTO.FlavorUpdate;
import br.edu.ufersa.pizzaria.Michelangelo.domain.service.FlavorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/flavor")
public class FlavorController {

  private final FlavorService service;

  public FlavorController(FlavorService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<FlavorResponse>> findAll() {
    return new ResponseEntity<List<FlavorResponse>>(service.listAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<FlavorResponse> create(@Valid @RequestBody FlavorCreate flavor) {
    return new ResponseEntity<FlavorResponse>(service.save(flavor), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FlavorResponse> update(@PathVariable Long id,
      @Valid @RequestBody FlavorUpdate flavor) {
    return new ResponseEntity<>(service.update(id, flavor), HttpStatus.OK);
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