package ru.falseteam.vktests.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.falseteam.vktests.entity.Role;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.UserRepository;

import java.util.Collections;

@Configuration
public class DebugConfiguration {

    @Autowired
    public DebugConfiguration(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository) {

        //Crate User
        userRepository.save(
                User.builder()
                        .username("user")
                        .password(passwordEncoder.encode("123"))
                        .authorities(Collections.singletonList(Role.USER))
                        .build()
        );
    }
}
