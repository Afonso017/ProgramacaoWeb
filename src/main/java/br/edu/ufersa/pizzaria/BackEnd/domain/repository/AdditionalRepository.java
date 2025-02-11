package br.edu.ufersa.pizzaria.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.backend.domain.entity.Additional;

@Repository
public interface AdditionalRepository extends JpaRepository<Additional, Long> {
  Additional findByName(String name);
}