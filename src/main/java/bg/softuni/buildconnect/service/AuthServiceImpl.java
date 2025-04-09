package bg.softuni.buildconnect.service;

import bg.softuni.buildconnect.dto.LoginDTO;
import bg.softuni.buildconnect.dto.RegistrationDTO;
import bg.softuni.buildconnect.entity.Role;
import bg.softuni.buildconnect.entity.User;
import bg.softuni.buildconnect.repository.RoleRepository;
import bg.softuni.buildconnect.repository.UserRepository;
import bg.softuni.buildconnect.service.AuthService;
import bg.softuni.buildconnect.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(RegistrationDTO registrationDTO) {
        Optional<User> existing = userRepository.findByEmail(registrationDTO.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Потребителят вече съществува!");
        }

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Ролята USER не съществува!"));

        User user = new User();
        user.setEmail(registrationDTO.getEmail());
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setRole(role);

        userRepository.save(user);
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );

        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Грешен имейл или парола."));

        return jwtService.generateToken(user);
    }
}
