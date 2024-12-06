package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import jakarta.persistence.Entity;

@Entity
public class Employees extends User {

    private boolean isAdmin;
    private boolean isChef;

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
