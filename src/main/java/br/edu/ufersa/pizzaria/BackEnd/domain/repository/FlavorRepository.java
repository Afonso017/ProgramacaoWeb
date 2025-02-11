package br.edu.ufersa.pizzaria.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.backend.domain.entity.Flavor;

@Repository
public interface FlavorRepository extends JpaRepository<Flavor, Long> {
  Flavor findByName(String name);
}