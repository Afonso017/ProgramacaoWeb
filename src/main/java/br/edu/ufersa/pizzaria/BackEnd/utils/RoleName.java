package br.edu.ufersa.pizzaria.backend.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleName {
    ROLE_ADMIN("Administrador"),
    ROLE_CHEF("Chefe"),
    ROLE_CLIENT("Cliente");

    private final String role;

    RoleName(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }

    @JsonCreator
    public static RoleName fromString(String value) {
        for (RoleName role : RoleName.values()) {
            if (role.getRole().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown size: " + value);
    }
}