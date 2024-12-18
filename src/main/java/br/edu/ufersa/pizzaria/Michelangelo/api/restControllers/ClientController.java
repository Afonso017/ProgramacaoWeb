package br.edu.ufersa.pizzaria.Michelangelo.api.restControllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.ClientDTO.ClientDeliveryCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.ClientDTO.ClientDeliveryResponse;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.ClientDTO.ClientLocalCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.ClientDTO.ClientLocalResponse;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.ClientDTO.ClientResponse;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.ClientDTO.ClientRetirementCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.ClientDTO.ClientRetirementResponse;
import br.edu.ufersa.pizzaria.Michelangelo.domain.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/api/v1/client")
public class ClientController {

  private final ClientService service;

  public ClientController(ClientService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<ClientResponse>> listAllClients() {
    return new ResponseEntity<>(service.findAllClients(), HttpStatus.OK);
  }

  // Create Client Delivery
  @PostMapping("/delivery")
  public ResponseEntity<ClientDeliveryResponse> createClientDelivery(@Valid @RequestBody ClientDeliveryCreate client) {
    return new ResponseEntity<>(service.saveClientDelivery(client), HttpStatus.CREATED);
  }

  // Create Client Local
  @PostMapping("/local")
  public ResponseEntity<ClientLocalResponse> createClientLocal(@Valid @RequestBody ClientLocalCreate client) {
    return new ResponseEntity<>(service.saveClientLocal(client), HttpStatus.CREATED);
  }

  // Create Client Retirement
  @PostMapping("/retirement")
  public ResponseEntity<ClientRetirementResponse> createClientRetirement(
      @Valid @RequestBody ClientRetirementCreate client) {
    return new ResponseEntity<>(service.saveClientRetirement(client), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findClientById(@PathVariable Long id) {
    return service.findClientById(id);
  }

  @PutMapping("/delivery/{id}")
  public ResponseEntity<?> updateClientDelivery(@PathVariable Long id,
      @Valid @RequestBody ClientDeliveryCreate client) {
    return service.updateClientDelivery(id, client);
  }

  @PutMapping("/local/{id}")
  public ResponseEntity<?> updateClientLocal(@PathVariable Long id, @Valid @RequestBody ClientLocalCreate client) {
    return service.updateClientLocal(id, client);
  }

  @PutMapping("/retirement/{id}")
  public ResponseEntity<?> updateClientRetirement(@PathVariable Long id,
      @Valid @RequestBody ClientRetirementCreate client) {
    return service.updateClientRetirement(id, client);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
    service.deleteClient(id);
    return ResponseEntity.noContent().build();
  }
}