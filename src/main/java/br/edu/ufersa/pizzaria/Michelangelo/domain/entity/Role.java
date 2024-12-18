package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import jakarta.persistence.*;
import utils.RoleName;

@Entity
@Table(name="tb_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    public String getName() {
        return name.name();
    }

}