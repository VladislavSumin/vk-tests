package ru.falseteam.vktests.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.falseteam.vktests.entity.Test;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.TestRepository;

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
        tests.forEach(test -> System.out.println(test.getTimeLimit()));
        return "testManagement";
    }
}
