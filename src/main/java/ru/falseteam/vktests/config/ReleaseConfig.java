package ru.falseteam.vktests.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.falseteam.vktests.entity.Group;
import ru.falseteam.vktests.entity.Role;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.GroupRepository;
import ru.falseteam.vktests.repository.UserRepository;

//@Configuration
public class ReleaseConfig {

    @Autowired
    public void config(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            GroupRepository groupRepository) {
        Group adminGroup = Group.builder()
                .name("Администраторы")
                .role(Role.ROLE_ADMIN)
                .build();
        Group teacherGroup = Group.builder()
                .name("Преподаватели")
                .role(Role.ROLE_TEACHER)
                .build();

        groupRepository.save(adminGroup);
        groupRepository.save(teacherGroup);

        //Crate Admin user
        userRepository.save(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123"))
                        .firstName("admin")
                        .lastName("admin")
                        .soname("admin")
                        .group(adminGroup)
                        .build()
        );
    }
}