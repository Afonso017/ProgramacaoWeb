package br.edu.ufersa.pizzaria.Michelangelo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Order;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.OrderItem;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id = :id")
  Optional<Order> findByIdWithItems(@Param("id") Long id);

  @Query("SELECT o FROM Order o JOIN FETCH o.items")
  Optional<Order> findAllWithItems();

  // Método que buscas e retorna uma lista de itens de um pedido pelo id do pedido
  @Query("SELECT i FROM Order o JOIN o.items i WHERE o.id = :orderId")
  List<OrderItem> findItemsByOrderId(@Param("orderId") Long orderId);
}