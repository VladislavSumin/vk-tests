package ru.falseteam.vktests.repository;

import org.springframework.data.repository.CrudRepository;
import ru.falseteam.vktests.entity.Group;
import ru.falseteam.vktests.entity.User;

import java.util.Optional;

/**
 * @author Sumin Vladislav
 * @version 0.3
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Iterable<User> findAllByGroup(Group group);
}
