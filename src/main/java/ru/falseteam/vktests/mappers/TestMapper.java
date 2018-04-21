package ru.falseteam.vktests.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.falseteam.vktests.entity.Group;
import ru.falseteam.vktests.entity.Role;
import ru.falseteam.vktests.entity.Test;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.TestRepository;

import java.util.Date;

@Controller
@RequestMapping("/admin/test")
@Secured("ROLE_ADMIN")
public class TestMapper {
    private final TestRepository testRepository;

    @Autowired
    public TestMapper(TestRepository testRepository) {
        this.testRepository = testRepository;
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
}
