package br.edu.ufersa.pizzaria.backend.domain.service;

import br.edu.ufersa.pizzaria.backend.domain.repository.EmployeesRepository;
import br.edu.ufersa.pizzaria.backend.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final EmployeesRepository employeesRepo;
    private final UserRepository userRepository;

    public UserService(EmployeesRepository employeesRepository, UserRepository userRepository) {
        this.employeesRepo = employeesRepository;
        this.userRepository = userRepository;
    }

//    public UserDTO.TokenDto authenticateUser(UserDTO.AuthRequest authRequest) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password());
//
//        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//        return new UserDTO.TokenDto(jwtService.generateToken(userDetails));
//    }

//    public void validateUser(UserDTO.AuthRequest authRequest) {
//        // Validate email
//        String email = authRequest.email();
//        if (email.isBlank()) {
//            throw new IllegalArgumentException("Email is required");
//        }
//
//        // Validate password
//        String password = authRequest.password();
//        if (password.isBlank()) {
//            throw new IllegalArgumentException("Password is required");
//        }
//    }
//
//    public ResponseEntity<?> create(UserDTO.AuthRequest authRequest, RoleName type) {
//        // Validate user
//        validateUser(authRequest);
//
//        // Find user
//        User user = employeesRepo.findByEmail(authRequest.email());
//        if (user != null) {
//            return ResponseEntity.badRequest().body("User already exists");
//        }
//
//        // Switch user type to create
//        return switch (type) {
//            case ROLE_CLIENT -> {
//                Employees client = new Employees();
//                client.setEmail(authRequest.email());
//                client.setPassword(authRequest.password());
//                client.setRole(type);
//                employeesRepo.save(client);
//                yield ResponseEntity.status(HttpStatus.CREATED).body("Client created");
//            }
//            default -> ResponseEntity.badRequest().body("Invalid user type");
//        };
//    }

//    public ResponseEntity<?> login(UserDTO.AuthRequest authRequest) {
//        // Validate user
//        validateUser(authRequest);
//
//        // Find admin
//        User admin = employeesRepo.findByEmail(authRequest.email());
//        if (admin == null) {
//            return ResponseEntity.badRequest().body("Admin not found");
//        }
//
//        return new ResponseEntity<>(authenticateUser(authRequest), HttpStatus.OK);
//    }

}