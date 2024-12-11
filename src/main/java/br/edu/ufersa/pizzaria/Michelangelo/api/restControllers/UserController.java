package br.edu.ufersa.pizzaria.Michelangelo.api.restControllers;

import br.edu.ufersa.pizzaria.Michelangelo.api.dto.UserDTO;
import br.edu.ufersa.pizzaria.Michelangelo.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<?> createAdmin(@RequestBody @Valid UserDTO.AuthRequest authRequest) {
        return userService.createAdmin(authRequest);
    }

    @GetMapping("/getAdmin")
    public ResponseEntity<?> getAdmin(@RequestBody @Valid UserDTO.EmailRequest emailRequest) {
        return userService.getAdmin(emailRequest);
    }
}
