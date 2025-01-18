package br.edu.ufersa.pizzaria.BackEnd.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Border;

@Repository
public interface BorderRepository extends JpaRepository<Border, Long> {
  public Border findByName(String name);
}