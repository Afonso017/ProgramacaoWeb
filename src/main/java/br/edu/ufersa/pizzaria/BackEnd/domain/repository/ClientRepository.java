package br.edu.ufersa.pizzaria.backend.domain.repository;

import br.edu.ufersa.pizzaria.backend.domain.entity.Client;
import br.edu.ufersa.pizzaria.backend.domain.entity.ClientDelivery;
import br.edu.ufersa.pizzaria.backend.domain.entity.ClientLocal;
import br.edu.ufersa.pizzaria.backend.domain.entity.ClientRetirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
  @Query("SELECT c FROM ClientDelivery c")
  List<ClientDelivery> findAllDeliveries();

  @Query("SELECT c FROM ClientLocal c")
  List<ClientLocal> findAllLocals();

  @Query("SELECT c FROM ClientRetirement c")
  List<ClientRetirement> findAllRetirements();

  Optional<Client> findByEmail(String email);

  boolean existsByEmail(String email);
}