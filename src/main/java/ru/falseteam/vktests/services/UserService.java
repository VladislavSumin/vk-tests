package ru.falseteam.vktests.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        User user = User.builder()
                .username("user")
                .password(new BCryptPasswordEncoder().encode("123"))
                .build();
        userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(username);
        if (byUsername == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return byUsername;
    }
}
