package ru.falseteam.vktests.repository;


import org.springframework.data.repository.CrudRepository;
import ru.falseteam.vktests.entity.TestQuestionAnswer;

public interface TestQuestionAnswerRepository extends CrudRepository<TestQuestionAnswer, Long> {
}
