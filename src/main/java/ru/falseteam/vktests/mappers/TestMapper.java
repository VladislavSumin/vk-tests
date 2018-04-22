package ru.falseteam.vktests.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.falseteam.vktests.PageNotFoundException;
import ru.falseteam.vktests.entity.Test;
import ru.falseteam.vktests.entity.TestQuestion;
import ru.falseteam.vktests.entity.TestQuestionAnswer;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.TestQuestionAnswerRepository;
import ru.falseteam.vktests.repository.TestQuestionRepository;
import ru.falseteam.vktests.repository.TestRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/test")
@Secured("ROLE_ADMIN")
public class TestMapper {
    private final TestRepository testRepository;
    private final TestQuestionRepository testQuestionRepository;
    private final TestQuestionAnswerRepository testQuestionAnswerRepository;

    @Autowired
    public TestMapper(TestRepository testRepository, TestQuestionRepository testQuestionRepository, TestQuestionAnswerRepository testQuestionAnswerRepository) {
        this.testRepository = testRepository;
        this.testQuestionRepository = testQuestionRepository;
        this.testQuestionAnswerRepository = testQuestionAnswerRepository;
    }

    @GetMapping("/management")
    public String getTestManagement(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        Iterable<Test> tests = testRepository.findAll();
        model.addAttribute("tests", tests);

        return "testManagement";
    }

    @GetMapping("/add")
    public String getTestAdd(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        return "testAdd";
    }

    @PostMapping("/add")
    public String postTestAdd(
            RedirectAttributes attributes,
            @RequestParam(name = "name") String testName,
            @RequestParam(name = "time_limit") Long timeLimit) {
        try {
            //TODO fix log error on use this method
            testRepository.save(
                    Test.builder()
                            .name(testName)
                            .timeLimit(timeLimit * 60 * 1000)
                            .build()
            );
        } catch (DataIntegrityViolationException e) {
            attributes.addAttribute("error", "Тест с именем " + testName + " уже существует");
            return "redirect:/admin/test/add";
        }

        return "redirect:/admin/test/management";
    }

    @GetMapping("/info/{id}")
    public String getGroupInfo(Model model, Authentication auth,
                               @PathVariable Long id) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        Test test = testRepository.findById(id).orElseThrow(PageNotFoundException::new);
        model.addAttribute("test", test);

        return "testInfo";
    }

    @GetMapping("/add_question")
    public String getTestQuestionAdd(
            Model model, Authentication auth,
            @RequestParam(name = "test_id") Long testId) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        Test test = testRepository.findById(testId).orElseThrow(PageNotFoundException::new);
        model.addAttribute("test", test);

        return "testAddQuestion";
    }

    @PostMapping("/add_question")
    public String postTestQuestionAdd(
            @RequestParam(name = "test_id") Long testId,
            @RequestParam(name = "question") String question,
            @RequestParam(name = "answer_1_is_right", required = false, defaultValue = "0") Boolean answer1IsRight,
            @RequestParam(name = "answer_1") String answer1,
            @RequestParam(name = "answer_2_is_right", required = false, defaultValue = "0") Boolean answer2IsRight,
            @RequestParam(name = "answer_2", required = false) String answer2,
            @RequestParam(name = "answer_3_is_right", required = false, defaultValue = "0") Boolean answer3IsRight,
            @RequestParam(name = "answer_3", required = false) String answer3,
            @RequestParam(name = "answer_4_is_right", required = false, defaultValue = "0") Boolean answer4IsRight,
            @RequestParam(name = "answer_4", required = false) String answer4) {

        Test test = testRepository.findById(testId).orElseThrow(PageNotFoundException::new);

        TestQuestion question_ = TestQuestion.builder()
                .question(question)
                .test(test)
                .build();

        Set<TestQuestionAnswer> answers = new HashSet<>();

        if (answer1.length() == 0) {
            //TODO поправить
            throw new PageNotFoundException();
        }
        answers.add(TestQuestionAnswer.builder()
                .answer(answer1)
                .isRight(answer1IsRight)
                .question(question_)
                .build());
        if (answer2.length() > 0)
            answers.add(TestQuestionAnswer.builder()
                    .answer(answer2)
                    .isRight(answer2IsRight)
                    .question(question_)
                    .build());
        if (answer3.length() > 0)
            answers.add(TestQuestionAnswer.builder()
                    .answer(answer3)
                    .isRight(answer3IsRight)
                    .question(question_)
                    .build());
        if (answer4.length() > 0)
            answers.add(TestQuestionAnswer.builder()
                    .answer(answer4)
                    .isRight(answer4IsRight)
                    .question(question_)
                    .build());

        //TODO добавить проверку на количество ответов и тд
        testQuestionRepository.save(question_);
        answers.forEach(testQuestionAnswerRepository::save);

        return "redirect:/admin/test/info/" + testId;
    }
}
