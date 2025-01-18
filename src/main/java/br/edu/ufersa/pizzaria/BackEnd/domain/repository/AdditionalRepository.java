package br.edu.ufersa.pizzaria.BackEnd.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Additional;

@Repository
public interface AdditionalRepository extends JpaRepository<Additional, Long> {
  public Additional findByName(String name);
}