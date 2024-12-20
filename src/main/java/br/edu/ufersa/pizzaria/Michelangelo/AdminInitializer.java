package br.edu.ufersa.pizzaria.Michelangelo;

import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Employees;
import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.EmployeesRepository;
import br.edu.ufersa.pizzaria.Michelangelo.security.config.SecurityConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {

    private final EmployeesRepository employeesRepository;
    private final SecurityConfig securityConfig;

    @Autowired
    public AdminInitializer(EmployeesRepository employeesRepository, SecurityConfig securityConfig) {
        this.employeesRepository = employeesRepository;
        this.securityConfig = securityConfig;
    }

    @PostConstruct
    public void initializeAdmin() {
        if (employeesRepository.findByEmail("admin@email.com") == null) {
            Employees admin = new Employees(
                "admin@email.com",
                securityConfig.passwordEncoder().encode("admin123")
            );
            admin.setAdmin(true);
            employeesRepository.save(admin);
            System.out.println("Admin created with email: admin@email.com and password: admin123");
        } else {
            System.out.println("Admin already exists");
        }
    }
}