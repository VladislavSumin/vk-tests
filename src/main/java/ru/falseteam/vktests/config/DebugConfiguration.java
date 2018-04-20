package ru.falseteam.vktests.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.falseteam.vktests.entity.Group;
import ru.falseteam.vktests.entity.Role;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.GroupRepository;
import ru.falseteam.vktests.repository.UserRepository;

import java.util.Collections;

@Configuration
public class DebugConfiguration {

    @Autowired
    public DebugConfiguration(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            GroupRepository groupRepository) {

        Group group = Group.builder()
                .name("Admins")
                .role("ADMIN")
                .build();
        groupRepository.save(group);

        //Crate User
        userRepository.save(
                User.builder()
                        .username("user")
                        .password(passwordEncoder.encode("123"))
                        .authorities(Collections.singletonList(Role.USER))
                        .firstName("user")
                        .lastName("user")
                        .soname("user")
                        .group(group)
                        .build()
        );

        //Crate Admin user
        userRepository.save(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123"))
                        .authorities(Collections.singletonList(Role.ADMIN))
                        .firstName("admin")
                        .lastName("admin")
                        .soname("admin")
                        .group(group)
                        .build()
        );
    }
}
