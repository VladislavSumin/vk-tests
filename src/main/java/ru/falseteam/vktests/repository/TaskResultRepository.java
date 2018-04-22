package ru.falseteam.vktests.repository;

import org.springframework.data.repository.CrudRepository;
import ru.falseteam.vktests.entity.TaskResult;
import ru.falseteam.vktests.entity.User;

public interface TaskResultRepository extends CrudRepository<TaskResult, Long> {
    Iterable<TaskResult> findAllByUser(User user);
}
