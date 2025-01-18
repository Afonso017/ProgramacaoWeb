package br.edu.ufersa.pizzaria.BackEnd.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.edu.ufersa.pizzaria.BackEnd.domain.repository.ClientRepository;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.ClientDTO.ClientDeliveryCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.ClientDTO.ClientDeliveryResponse;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.ClientDTO.ClientLocalCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.ClientDTO.ClientLocalResponse;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.ClientDTO.ClientResponse;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.ClientDTO.ClientRetirementCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.ClientDTO.ClientRetirementResponse;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Client;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.ClientDelivery;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.ClientLocal;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.ClientRetirement;

@Service
public class ClientService {

  private final ClientRepository repository;

  public ClientService(ClientRepository repository) {
    this.repository = repository;
  }

  public ClientDeliveryResponse saveClientDelivery(ClientDeliveryCreate client) {
    ClientDelivery savedClient = repository.save((ClientDelivery) client.toEntity());
    return new ClientDeliveryResponse(savedClient);
  }

  public ClientLocalResponse saveClientLocal(ClientLocalCreate client) {
    ClientLocal savedClient = repository.save((ClientLocal) client.toEntity());
    return new ClientLocalResponse(savedClient);
  }

  public ClientRetirementResponse saveClientRetirement(ClientRetirementCreate client) {
    ClientRetirement savedClient = repository.save((ClientRetirement) client.toEntity());
    return new ClientRetirementResponse(savedClient);
  }

  public List<ClientResponse> findAllClients() {
    return repository.findAll()
        .stream()
        .map(ClientResponse::new)
        .collect(Collectors.toList());
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
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  public ResponseEntity<?> deleteClient(Long id) {
    Optional<Client> client = repository.findById(id);

    if (client.isPresent()) {
      Client clientEntity = client.get();

      if (clientEntity instanceof ClientDelivery) {
        repository.delete((ClientDelivery) clientEntity); // Deleta o cliente do tipo ClientDelivery
      } else if (clientEntity instanceof ClientLocal) {
        repository.delete((ClientLocal) clientEntity); // Deleta o cliente do tipo ClientLocal
      } else if (clientEntity instanceof ClientRetirement) {
        repository.delete((ClientRetirement) clientEntity); // Deleta o cliente do tipo ClientRetirement
      }

      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build(); // Cliente não encontrado
    }
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