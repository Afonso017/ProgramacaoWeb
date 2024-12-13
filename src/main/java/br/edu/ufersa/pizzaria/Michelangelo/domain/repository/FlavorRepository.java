package br.edu.ufersa.pizzaria.Michelangelo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Flavor;

@Repository
public interface FlavorRepository extends JpaRepository<Flavor, Long> {
  Flavor findByName(String name);
}