package br.edu.ufersa.pizzaria.BackEnd.domain.entity;

import jakarta.persistence.Entity;

@Entity
public class Employees extends User {
    private boolean isAdmin;
    private boolean isChef;

    public Employees() {
    }

    public Employees(String email, String password) {
        super(email, password);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isChef() {
        return isChef;
    }

    public void setChef(boolean chef) {
        isChef = chef;
    }
}
