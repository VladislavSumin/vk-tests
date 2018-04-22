package ru.falseteam.vktests.repository;

import org.springframework.data.repository.CrudRepository;
import ru.falseteam.vktests.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
