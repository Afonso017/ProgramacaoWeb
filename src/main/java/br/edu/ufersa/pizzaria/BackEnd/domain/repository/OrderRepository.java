package br.edu.ufersa.pizzaria.BackEnd.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id = :id")
  Optional<Order> findByIdWithItems(@Param("id") Long id);

  @Query("SELECT o FROM Order o JOIN FETCH o.items")
  Optional<Order> findAllWithItems();
}