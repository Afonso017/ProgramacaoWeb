package br.edu.ufersa.pizzaria.BackEnd.domain.service;

import br.edu.ufersa.pizzaria.BackEnd.api.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChefService {

    public ResponseEntity<?> createChef(UserDTO.@Valid AuthRequest authRequest) {
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

        // Create chefEmployees chef = new Employees(email, password);
        ////        chef.setChef(true);
        ////        employeesRepo.save(chef);
//

        return ResponseEntity.ok().body("Chef created successfully");
    }

    public ResponseEntity<?> loginChef(UserDTO.AuthRequest authRequest) {
        // Validate email
        String email = authRequest.email();
        if (email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        // Find chef
//        User chef = employeesRepo.findByEmail(email);
//        if (chef == null) {
//            return ResponseEntity.badRequest().body("Chef not found");
//        }

        return ResponseEntity.ok().body("Chef logged in successfully");
    }

}
