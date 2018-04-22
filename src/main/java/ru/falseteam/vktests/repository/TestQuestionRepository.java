package ru.falseteam.vktests.repository;

import org.springframework.data.repository.CrudRepository;
import ru.falseteam.vktests.entity.Test;
import ru.falseteam.vktests.entity.TestQuestion;

public interface TestQuestionRepository extends CrudRepository<TestQuestion, Long> {
    Iterable<TestQuestion> findAllByTest(Test test);
}
