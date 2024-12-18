package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "client_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClientDelivery.class, name = "delivery"),
        @JsonSubTypes.Type(value = ClientLocal.class, name = "local"),
        @JsonSubTypes.Type(value = ClientRetirement.class, name = "retirement")
})
@PrimaryKeyJoinColumn(name = "client_id")
public abstract class Client extends User {
    private String phone;

    public Client() {
    }

    public Client(String email, String password, String phone) {
        super(email, password);
        this.phone = phone;
    }

    /**
     * @return String return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

}