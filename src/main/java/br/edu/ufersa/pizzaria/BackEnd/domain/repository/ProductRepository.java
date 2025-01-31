package br.edu.ufersa.pizzaria.BackEnd.domain.repository;

import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findByName(String name);
}
