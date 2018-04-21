package ru.falseteam.vktests.repository;

import org.springframework.data.repository.CrudRepository;
import ru.falseteam.vktests.entity.Test;

public interface TestRepository extends CrudRepository<Test, Long> {
}
