package ru.falseteam.vktests.mappers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.falseteam.vktests.entity.User;

/**
 * @author Sumin Vladislav
 * @version 0.1
 */
@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminMapper {
    @GetMapping("/group/management")
    public String getGroupManagement(Model model, Authentication auth) {
        auth.getAuthorities().forEach(System.out::println);
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);
        return "groupManagement";
    }
}
