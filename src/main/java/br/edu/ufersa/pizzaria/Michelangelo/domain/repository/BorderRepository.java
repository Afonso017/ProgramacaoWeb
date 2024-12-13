package br.edu.ufersa.pizzaria.Michelangelo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Border;

@Repository
public interface BorderRepository extends JpaRepository<Border, Long> {
  public Border findByName(String name);
}