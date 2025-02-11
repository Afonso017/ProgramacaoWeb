package br.edu.ufersa.pizzaria.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.backend.domain.entity.Border;

@Repository
public interface BorderRepository extends JpaRepository<Border, Long> {
  Border findByName(String name);
}