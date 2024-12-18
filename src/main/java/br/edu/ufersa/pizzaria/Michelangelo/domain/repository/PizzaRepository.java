package br.edu.ufersa.pizzaria.Michelangelo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
  public Pizza findByName(String name);
}