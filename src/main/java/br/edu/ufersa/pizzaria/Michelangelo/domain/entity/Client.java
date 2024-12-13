package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import jakarta.persistence.Entity;

@Entity
public class Client extends User {
    private String address;
    private String clientTable;
    private String telefone;

    public Client() {
    }

    public Client(String email, String password, String address, String clientTable, String telefone) {
        super(email, password);
        this.address = address;
        this.clientTable = clientTable;
        this.telefone = telefone;
    }

    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return String return the clientTable
     */
    public String getClientTable() {
        return clientTable;
    }

    /**
     * @param clientTable the clientTable to set
     */
    public void setClientTable(String clientTable) {
        this.clientTable = clientTable;
    }

    /**
     * @return String return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}