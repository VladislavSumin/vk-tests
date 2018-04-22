package ru.falseteam.vktests.repository;

import org.springframework.data.repository.CrudRepository;
import ru.falseteam.vktests.entity.Group;

public interface GroupRepository extends CrudRepository<Group, Long> {
}
