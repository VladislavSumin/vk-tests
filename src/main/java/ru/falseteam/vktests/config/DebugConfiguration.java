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

        Group adminGroup = Group.builder()
                .name("Администраторы")
                .role(Role.ADMIN)
                .build();
        Group teacherGroup = Group.builder()
                .name("Преподаватели")
                .role(Role.TEACHER)
                .build();
        Group userGroup = Group.builder()
                .name("Пользователи")
                .role(Role.USER)
                .build();

        groupRepository.save(adminGroup);
        groupRepository.save(teacherGroup);
        groupRepository.save(userGroup);

        //Crate Admin user
        userRepository.save(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123"))
                        .firstName("Иван")
                        .lastName("Иванов")
                        .soname("Иванович")
                        .group(adminGroup)
                        .build()
        );

        //Crate Teacher
        userRepository.save(
                User.builder()
                        .username("teacher")
                        .password(passwordEncoder.encode("123"))
                        .firstName("teacher")
                        .lastName("teacher")
                        .soname("teacher")
                        .group(teacherGroup)
                        .build()
        );

        //Crate User
        userRepository.save(
                User.builder()
                        .username("user")
                        .password(passwordEncoder.encode("123"))
                        .firstName("user")
                        .lastName("user")
                        .soname("user")
                        .group(userGroup)
                        .build()
        );
    }
}
