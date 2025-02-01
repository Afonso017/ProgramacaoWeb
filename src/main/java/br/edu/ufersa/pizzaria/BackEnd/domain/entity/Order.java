package br.edu.ufersa.pizzaria.BackEnd.domain.entity;

import utils.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonFormat;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderItemUpdate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.OrderDTO.OrderUpdate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime orderDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    public Order() {
    }

    public Order(Long id, Client client, OrderStatus status, List<OrderItem> items,
            BigDecimal totalAmount) {
        this.client = client;
        this.status = status;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public Order(Client client, OrderStatus status, List<OrderItem> items,
            BigDecimal totalAmount) {
        this.client = client;
        this.status = status;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public Order(List<OrderItem> items, BigDecimal totalAmount) {
        this.items = items;
        this.totalAmount = totalAmount;
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Client return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return LocalDateTime return the orderDate
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return OrderStatus return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * @return List<OrderItem> return the items
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<OrderItem> items) {
        this.items.clear();
        for (OrderItem item : items) {
            this.addItem(item);
        }
    }

    /**
     * @return BigDecimal return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrder(OrderUpdate orderUpdate) {
        this.client = orderUpdate.clientId();
        this.orderDate = orderUpdate.orderDate();
        this.status = orderUpdate.status();
        this.totalAmount = orderUpdate.totalAmount();
        this.setItems(orderUpdate.items().stream().map(OrderItemUpdate::toEntity).collect(Collectors.toList()));
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", client=" + client + ", orderDate=" + orderDate + ", status=" + status + ", items=" + items + ", totalAmount=" + totalAmount + "]";
    }

}