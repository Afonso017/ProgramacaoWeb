package br.edu.ufersa.pizzaria.Michelangelo.api.restControllers;

import br.edu.ufersa.pizzaria.Michelangelo.api.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping("/createAdmin")
    public void createAdmin(@RequestBody UserDTO.AuthRequest authRequest) {

    }

}
