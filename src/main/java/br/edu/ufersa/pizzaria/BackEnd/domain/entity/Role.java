package br.edu.ufersa.pizzaria.backend.domain.entity;

import jakarta.persistence.*;
import br.edu.ufersa.pizzaria.backend.utils.RoleName;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="role_tb")
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