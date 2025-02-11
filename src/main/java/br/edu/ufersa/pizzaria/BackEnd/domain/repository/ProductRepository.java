package br.edu.ufersa.pizzaria.backend.domain.repository;

import br.edu.ufersa.pizzaria.backend.domain.entity.Pizza;
import br.edu.ufersa.pizzaria.backend.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    // Buscar todos os produtos do tipo Pizza
    @Query("SELECT p FROM Product p WHERE TYPE(p) = Pizza")
    List<Pizza> findAllPizzas();
}