package br.edu.ufersa.pizzaria.backend.domain.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employees extends User {
    private boolean isAdmin;
    private boolean isChef;

    public Employees(String email, String password) {
        super(email, password);
    }

}