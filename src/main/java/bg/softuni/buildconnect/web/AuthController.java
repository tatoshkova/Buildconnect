package bg.softuni.buildconnect.web;

import bg.softuni.buildconnect.dto.RegistrationDTO;
import bg.softuni.buildconnect.dto.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationDTO registrationDTO) {
        authService.register(registrationDTO);
        return ResponseEntity.ok("Регистрацията е успешна.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) {
        String jwt = authService.login(loginDTO);
        return ResponseEntity.ok(jwt);
    }
}
