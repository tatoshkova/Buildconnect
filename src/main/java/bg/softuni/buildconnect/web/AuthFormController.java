package bg.softuni.buildconnect.web;

import bg.softuni.buildconnect.entity.Role;
import bg.softuni.buildconnect.entity.User;
import bg.softuni.buildconnect.repository.RoleRepository;
import bg.softuni.buildconnect.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthFormController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthFormController(UserRepository userRepository,
                              RoleRepository roleRepository,
                              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String username,
                           @RequestParam String password) {

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Missing USER role"));

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(userRole);

        userRepository.save(newUser);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

}
