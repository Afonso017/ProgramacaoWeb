package br.edu.ufersa.pizzaria.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "client_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClientDelivery.class, name = "delivery"),
        @JsonSubTypes.Type(value = ClientLocal.class, name = "local"),
        @JsonSubTypes.Type(value = ClientRetirement.class, name = "retirement")
})
@PrimaryKeyJoinColumn(name = "client_id")
@Table(name = "client_tb")
public class Client extends User {
    private String phone;

    public Client(Long id) {
        super(id);
    }

    public Client(String phone, String email, String password) {
        super(email, password);
        this.phone = phone;
    }

    public Client(Long id, String phone, String email, String password) {
        super(id, email, password);
        this.phone = phone;
    }

    public Client(String email, String password){
        super(email, password);
        this.phone = phone;
    }
}