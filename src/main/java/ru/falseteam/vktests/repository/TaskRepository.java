package ru.falseteam.vktests.repository;

import org.springframework.data.repository.CrudRepository;
import ru.falseteam.vktests.entity.Group;
import ru.falseteam.vktests.entity.Task;

import java.util.Date;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findAllByGroupAndEndTimeAfter(Group group, Date endTime);
}
