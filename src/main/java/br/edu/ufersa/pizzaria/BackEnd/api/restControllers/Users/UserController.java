package br.edu.ufersa.pizzaria.backend.api.restControllers.Users;

import br.edu.ufersa.pizzaria.backend.domain.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    // A fazer!

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/admin/create")
//    public ResponseEntity<?> createAdmin(@RequestBody @Valid UserDTO.AuthRequest authRequest) {
//        return userService.create(authRequest, RoleName.ROLE_ADMIN);
//    }
//
//    @PostMapping("/chef/create")
//    public ResponseEntity<?> createChef(@RequestBody @Valid UserDTO.AuthRequest authRequest) {
//        return userService.create(authRequest, RoleName.ROLE_CHEF);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody @Valid UserDTO.AuthRequest authRequest) {
//        return userService.login(authRequest);
//    }
//
//    @PostMapping("/client/get")
//    public ResponseEntity<?> getClient(@RequestBody @Valid UserDTO.EmailRequest emailRequest) {
//        return userService.getClient(emailRequest);
//    }
//
//    @GetMapping("/getAdmin")
//    public ResponseEntity<?> getAdmin(@RequestBody @Valid UserDTO.EmailRequest emailRequest) {
//        return userService.getAdmin(emailRequest);
//    }
}
