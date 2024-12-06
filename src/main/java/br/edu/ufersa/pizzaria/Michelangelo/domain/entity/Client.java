package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import jakarta.persistence.Entity;

@Entity
public class Client extends User {

    private String address;
    private String table;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
