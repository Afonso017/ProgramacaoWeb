package br.edu.ufersa.pizzaria.backend.domain.entity;

import br.edu.ufersa.pizzaria.backend.api.dto.OrderDTO.OrderItemUpdate;
import br.edu.ufersa.pizzaria.backend.api.dto.OrderDTO.OrderUpdate;
import br.edu.ufersa.pizzaria.backend.utils.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private @NotNull Client client;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime orderDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    public Order(Long id) {
        this.id = id;
    }

    public Order(Client client, OrderStatus status, List<OrderItem> items) {
        this.client = client;
        this.status = status;
        this.setItems(items);
        this.totalAmount = Calculator(items);
    }

    public void setOrder(OrderUpdate orderUpdate) {
        this.id = orderUpdate.id();
        this.client = new Client(orderUpdate.clientId());
        this.orderDate = orderUpdate.orderDate();
        this.status = orderUpdate.status();
        this.setItems(orderUpdate.items().stream().map(OrderItemUpdate::toEntity).collect(Collectors.toList()));
        this.totalAmount = Calculator(orderUpdate.items().stream().map(OrderItemUpdate::toEntity).collect(Collectors.toList()));
    }

    public static BigDecimal Calculator(List<OrderItem> items) {
        return items.stream().map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setItems(List<OrderItem> items) {
        this.items.clear();
        for (OrderItem item : items) {
            item.setOrder(this);
            this.items.add(item);
        }
        this.totalAmount = Calculator(items);
    }
}