package br.edu.ufersa.pizzaria.Michelangelo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Additional;

@Repository
public interface AdditionalRepository extends JpaRepository<Additional, Long> {

}