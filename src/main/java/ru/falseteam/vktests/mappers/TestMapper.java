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
import ru.falseteam.vktests.entity.*;
import ru.falseteam.vktests.repository.TestQuestionRepository;
import ru.falseteam.vktests.repository.TestRepository;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/admin/test")
@Secured("ROLE_ADMIN")
public class TestMapper {
    private final TestRepository testRepository;
    private final TestQuestionRepository testQuestionRepository;

    @Autowired
    public TestMapper(TestRepository testRepository, TestQuestionRepository testQuestionRepository) {
        this.testRepository = testRepository;
        this.testQuestionRepository = testQuestionRepository;
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
                            .timeLimit(new Date(timeLimit * 60 * 1000))
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
        Iterable<TestQuestion> questions = testQuestionRepository.findAllByTest(test);
        model.addAttribute("questions", questions);


        return "testInfo";
    }
}
