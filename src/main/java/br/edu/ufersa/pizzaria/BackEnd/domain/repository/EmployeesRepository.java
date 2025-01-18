package br.edu.ufersa.pizzaria.BackEnd.domain.repository;

import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Employees;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {

    User findByEmail(String email);
}
