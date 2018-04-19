package ru.falseteam.vktests.repository;

import org.springframework.data.repository.CrudRepository;
import ru.falseteam.vktests.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
