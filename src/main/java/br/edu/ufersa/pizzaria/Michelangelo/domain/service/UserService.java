package br.edu.ufersa.pizzaria.Michelangelo.domain.service;

import br.edu.ufersa.pizzaria.Michelangelo.api.dto.UserDTO;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Employees;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.User;
import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.EmployeesRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

@Service
public class UserService {

    private final EmployeesRepository employeesRepo;

    public UserService(EmployeesRepository employeesRepository) {
        this.employeesRepo = employeesRepository;
    }

    public ResponseEntity<?> createAdmin(UserDTO.AuthRequest authRequest) {
        // Validate email
        String email = authRequest.email();
        if (email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        // Validate password
        String password = authRequest.password();
        if (password.isEmpty()) {
            return ResponseEntity.badRequest().body("Password is required");
        }

        // Create admin
        Employees admin = new Employees(email, password);
        admin.setAdmin(true);
        employeesRepo.save(admin);

        return ResponseEntity.ok().body("Admin created successfully");
    }

    public ResponseEntity<?> getAdmin(UserDTO.EmailRequest emailRequest) {
        // Validate email
        String email = emailRequest.email();
        if (email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        // Find admin
        User admin = employeesRepo.findByEmail(email);
        if (admin == null) {
            return ResponseEntity.badRequest().body("Admin not found");
        }

        return ResponseEntity.ok().body(admin);
    }
}
