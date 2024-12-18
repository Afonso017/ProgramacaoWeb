package br.edu.ufersa.pizzaria.Michelangelo.domain.repository;

import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
  // Exemplo de uso: List<Client> deliveryClients =
  // clientRepository.findAllByType(ClientDelivery.class);
  @Query("SELECT c FROM Client c WHERE TYPE(c) = ?1")
  List<Client> findAllByType(Class<?> type);
}