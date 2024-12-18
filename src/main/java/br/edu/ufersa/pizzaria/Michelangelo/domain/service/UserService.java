package br.edu.ufersa.pizzaria.Michelangelo.domain.service;

import br.edu.ufersa.pizzaria.Michelangelo.api.dto.UserDTO;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Employees;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.User;
import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.EmployeesRepository;
import br.edu.ufersa.pizzaria.Michelangelo.security.authentication.JwtService;
import br.edu.ufersa.pizzaria.Michelangelo.security.config.SecurityConfig;
import br.edu.ufersa.pizzaria.Michelangelo.security.userdetails.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import utils.RoleName;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

@Service
public class UserService {

    private final EmployeesRepository employeesRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final SecurityConfig securityConfig;

    public UserService(EmployeesRepository employeesRepository, JwtService jwtService, AuthenticationManager authenticationManager, SecurityConfig securityConfig) {
        this.employeesRepo = employeesRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.securityConfig = securityConfig;
    }

    public UserDTO.TokenDto authenticateUser(UserDTO.AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new UserDTO.TokenDto(jwtService.generateToken(userDetails));
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

        // Find user
        User user = employeesRepo.findByEmail(authRequest.email());
        if (user != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        // Switch user type to create
        return switch (type) {
            case ROLE_ADMIN -> {
                // Create admin
                Employees admin = new Employees(authRequest.email(), securityConfig.passwordEncoder().encode(authRequest.password()));
                admin.setAdmin(true);
                employeesRepo.save(admin);
                yield ResponseEntity.ok().body("Admin created successfully");
            }
            case ROLE_CHEF -> {
                // Create chef
                Employees chef = new Employees(authRequest.email(), securityConfig.passwordEncoder().encode(authRequest.password()));
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

        return new ResponseEntity<>(authenticateUser(authRequest), HttpStatus.OK);
    }

}