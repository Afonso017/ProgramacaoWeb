package br.edu.ufersa.pizzaria.Michelangelo.domain.service;

import br.edu.ufersa.pizzaria.Michelangelo.api.dto.UserDTO;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Employees;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.User;
import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.EmployeesRepository;
import utils.RoleName;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

@Service
public class UserService {

    private final EmployeesRepository employeesRepo;

    public UserService(EmployeesRepository employeesRepository) {
        this.employeesRepo = employeesRepository;
    }

    public void validateUser(UserDTO.AuthRequest authRequest) {
        // Validate email
        String email = authRequest.email();
        if (email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        // Validate password
        String password = authRequest.password();
        if (password.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
    }

    public ResponseEntity<?> create(UserDTO.AuthRequest authRequest, RoleName type) {
        // Validate user
        validateUser(authRequest);

        // Switch user type to create
        return switch (type) {
            case ROLE_ADMIN -> {
                // Create admin
                Employees admin = new Employees(authRequest.email(), authRequest.password());
                admin.setAdmin(true);
                employeesRepo.save(admin);
                yield ResponseEntity.ok().body("Admin created successfully");
            }
            case ROLE_CHEF -> {
                // Create chef
                Employees chef = new Employees(authRequest.email(), authRequest.password());
                chef.setChef(true);
                employeesRepo.save(chef);
                yield ResponseEntity.ok().body("Chef created successfully");
            }
            default -> ResponseEntity.badRequest().body("Invalid user type");
        };
    }

    public ResponseEntity<?> login(UserDTO.AuthRequest authRequest) {
        // Validate user
        validateUser(authRequest);

        // Find admin
        User admin = employeesRepo.findByEmail(authRequest.email());
        if (admin == null) {
            return ResponseEntity.badRequest().body("Admin not found");
        }

        return ResponseEntity.ok().body("Admin logged in successfully");
    }

}