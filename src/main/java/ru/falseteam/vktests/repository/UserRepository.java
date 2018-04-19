package ru.falseteam.vktests.repository;

import org.springframework.data.repository.CrudRepository;
import ru.falseteam.vktests.entity.User;

/**
 * @author Sumin Vladislav
 * @version 0.1
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
