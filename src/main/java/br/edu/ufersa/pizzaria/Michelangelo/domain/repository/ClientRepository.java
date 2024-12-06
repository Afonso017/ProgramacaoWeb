package br.edu.ufersa.pizzaria.Michelangelo.domain.repository;

import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
