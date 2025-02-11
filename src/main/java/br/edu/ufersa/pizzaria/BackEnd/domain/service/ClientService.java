package br.edu.ufersa.pizzaria.backend.domain.service;

import br.edu.ufersa.pizzaria.backend.api.dto.ClientDTO.*;
import br.edu.ufersa.pizzaria.backend.domain.entity.Client;
import br.edu.ufersa.pizzaria.backend.domain.entity.ClientDelivery;
import br.edu.ufersa.pizzaria.backend.domain.entity.ClientLocal;
import br.edu.ufersa.pizzaria.backend.domain.entity.ClientRetirement;
import br.edu.ufersa.pizzaria.backend.domain.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

  private final ClientRepository repository;

  public ClientService(ClientRepository repository) {
    this.repository = repository;
  }

  public List<ClientDeliveryResponse> findAllDeliveryClients() {
    return repository.findAllDeliveries().stream().map(ClientDeliveryResponse::new).toList();
  }

  public List<ClientLocalResponse> findAllLocalClients() {
    return repository.findAllLocals().stream().map(ClientLocalResponse::new).toList();
  }

  public List<ClientRetirementResponse> findAllRetirementClients() {
    return repository.findAllRetirements().stream().map(ClientRetirementResponse::new).toList();
  }

  public ClientDeliveryResponse saveClientDelivery(ClientDeliveryCreate client) {
    if (repository.existsByEmail(client.email())) {
      throw new IllegalArgumentException("Email já cadastrado");
    }

    ClientDelivery savedClient = repository.save(client.toEntity());
    return new ClientDeliveryResponse(savedClient);
  }

  public ClientLocalResponse saveClientLocal(ClientLocalCreate client) {
    if (repository.existsByEmail(client.email())) {
      throw new IllegalArgumentException("Email já cadastrado");
    }

    ClientLocal savedClient = repository.save(client.toEntity());
    return new ClientLocalResponse(savedClient);
  }

  public ClientRetirementResponse saveClientRetirement(ClientRetirementCreate client) {
    if (repository.existsByEmail(client.email())) {
      throw new IllegalArgumentException("Email já cadastrado");
    }

    ClientRetirement savedClient = repository.save(client.toEntity());
    return new ClientRetirementResponse(savedClient);
  }

  public ResponseEntity<?> findClientById(Long id) {
    var client = repository.findById(id);

    if (client.isPresent()) {
      if (client.get() instanceof ClientDelivery) {
        return ResponseEntity.ok(new ClientDeliveryResponse((ClientDelivery) client.get()));
      } else if (client.get() instanceof ClientLocal) {
        return ResponseEntity.ok(new ClientLocalResponse((ClientLocal) client.get()));
      } else {
        return ResponseEntity.ok(new ClientRetirementResponse((ClientRetirement) client.get()));
      }
    }

    throw new IllegalArgumentException("Cliente não encontrado");
  }

  public void deleteClient(Long id) {
    Optional<Client> client = repository.findById(id);

    if (client.isPresent()) {
      Client clientEntity = client.get();
      repository.delete(clientEntity);
      return;
    }

    throw new IllegalArgumentException("Cliente não encontrado");
  }

  public ResponseEntity<?> updateClientDelivery(Long id, ClientDeliveryCreate client) {
    Optional<Client> clientEntity = repository.findById(id);

    if (clientEntity.isPresent()) {
      ClientDelivery clientToUpdate = (ClientDelivery) client.toEntity();
      clientToUpdate.setId(id);
      repository.save(clientToUpdate);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  public ResponseEntity<?> updateClientLocal(Long id, ClientLocalCreate client) {
    Optional<Client> clientEntity = repository.findById(id);

    if (clientEntity.isPresent()) {
      ClientLocal clientToUpdate = (ClientLocal) client.toEntity();
      clientToUpdate.setId(id);
      repository.save(clientToUpdate);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  public ResponseEntity<?> updateClientRetirement(Long id, ClientRetirementCreate client) {
    Optional<Client> clientEntity = repository.findById(id);

    if (clientEntity.isPresent()) {
      ClientRetirement clientToUpdate = (ClientRetirement) client.toEntity();
      clientToUpdate.setId(id);
      repository.save(clientToUpdate);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}