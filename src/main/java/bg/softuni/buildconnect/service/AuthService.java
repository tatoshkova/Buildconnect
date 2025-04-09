package bg.softuni.buildconnect.service;

import bg.softuni.buildconnect.dto.LoginDTO;
import bg.softuni.buildconnect.dto.RegistrationDTO;

public interface AuthService {
    void register(RegistrationDTO registrationDTO);

    String login(LoginDTO loginDTO);
}
