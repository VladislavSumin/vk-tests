package ru.falseteam.vktests.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.falseteam.vktests.entity.*;
import ru.falseteam.vktests.repository.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DebugConfiguration {

    @Autowired
    void createUsersAndGroups(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            GroupRepository groupRepository) {

        Group adminGroup = Group.builder()
                .name("Администраторы")
                .role(Role.ROLE_ADMIN)
                .build();
        Group teacherGroup = Group.builder()
                .name("Преподаватели")
                .role(Role.ROLE_TEACHER)
                .build();
        Group userGroup = Group.builder()
                .name("Пользователи")
                .role(Role.ROLE_USER)
                .build();

        groupRepository.save(adminGroup);
        groupRepository.save(teacherGroup);
        groupRepository.save(userGroup);

        //Crate Admin user
        userRepository.save(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123"))
                        .firstName("Иван")
                        .lastName("Иванов")
                        .soname("Иванович")
                        .group(adminGroup)
                        .build()
        );

        //Crate Teacher
        userRepository.save(
                User.builder()
                        .username("teacher")
                        .password(passwordEncoder.encode("123"))
                        .firstName("teacher")
                        .lastName("teacher")
                        .soname("teacher")
                        .group(teacherGroup)
                        .build()
        );

        //Crate User
        userRepository.save(
                User.builder()
                        .username("user")
                        .password(passwordEncoder.encode("123"))
                        .firstName("user")
                        .lastName("user")
                        .soname("user")
                        .group(userGroup)
                        .build()
        );
    }

    @Autowired
    void createTests(
            TestRepository testRepository,
            TestQuestionRepository testQuestionRepository,
            TestQuestionAnswerRepository testQuestionAnswerRepository) {
        Test test = Test.builder()
                .name("Тест 1")
                .timeLimit(new Date(10 * 60 * 1000))
                .build();
        testRepository.save(test);

        TestQuestion question = TestQuestion.builder()
                .question("Вопрос 1")
                .test(test)
                .build();
        testQuestionRepository.save(question);

        testQuestionAnswerRepository.save(
                TestQuestionAnswer.builder()
                        .answer("Ответ 1")
                        .question(question)
                        .isRight(true)
                        .build());

        testQuestionAnswerRepository.save(
                TestQuestionAnswer.builder()
                        .answer("Ответ 2")
                        .question(question)
                        .isRight(false)
                        .build());

        testQuestionAnswerRepository.save(
                TestQuestionAnswer.builder()
                        .answer("Ответ 3")
                        .question(question)
                        .isRight(false)
                        .build());

        testQuestionAnswerRepository.save(
                TestQuestionAnswer.builder()
                        .answer("Ответ 4")
                        .question(question)
                        .isRight(false)
                        .build());



        question = TestQuestion.builder()
                .question("Вопрос 2")
                .test(test)
                .build();
        testQuestionRepository.save(question);
        testQuestionAnswerRepository.save(
                TestQuestionAnswer.builder()
                        .answer("Ответ 1")
                        .question(question)
                        .isRight(true)
                        .build());

        testQuestionAnswerRepository.save(
                TestQuestionAnswer.builder()
                        .answer("Ответ 2")
                        .question(question)
                        .isRight(false)
                        .build());

        testQuestionAnswerRepository.save(
                TestQuestionAnswer.builder()
                        .answer("Ответ 3")
                        .question(question)
                        .isRight(false)
                        .build());

        testQuestionAnswerRepository.save(
                TestQuestionAnswer.builder()
                        .answer("Ответ 4")
                        .question(question)
                        .isRight(false)
                        .build());
    }
}
