package br.edu.ufersa.pizzaria.Michelangelo.domain.repository;

import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Employees;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {

    User findByEmail(String email);
}
