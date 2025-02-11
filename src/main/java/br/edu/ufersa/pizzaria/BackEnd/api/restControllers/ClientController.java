package br.edu.ufersa.pizzaria.backend.api.restControllers;

import br.edu.ufersa.pizzaria.backend.api.dto.ClientDTO.*;
import br.edu.ufersa.pizzaria.backend.domain.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

  private final ClientService service;

  public ClientController(ClientService service) {
    this.service = service;
  }

  @GetMapping("/delivery")
  public List<ClientDeliveryResponse> findDeliveryClients() {
    return service.findAllDeliveryClients();
  }

  @GetMapping("/local")
  public List<ClientLocalResponse> findLocalClients() {
    return service.findAllLocalClients();
  }

  @GetMapping("/retirement")
  public List<ClientRetirementResponse> findRetirementClients() {
    return service.findAllRetirementClients();
  }

  // Create Client Delivery
  @PostMapping("/delivery")
  public ResponseEntity<ClientDeliveryResponse> createClientDelivery(
          @Valid @RequestBody ClientDeliveryCreate client) {
    return new ResponseEntity<>(service.saveClientDelivery(client), HttpStatus.CREATED);
  }

  // Create Client Local
  @PostMapping("/local")
  public ResponseEntity<ClientLocalResponse> createClientLocal(
          @Valid @RequestBody ClientLocalCreate client) {
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
  public ResponseEntity<?> updateClientLocal(@PathVariable Long id,
                                             @Valid @RequestBody ClientLocalCreate client) {
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