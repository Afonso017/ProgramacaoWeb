package br.edu.ufersa.pizzaria.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.backend.domain.entity.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}