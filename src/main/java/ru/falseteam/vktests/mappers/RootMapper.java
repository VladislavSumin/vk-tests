package ru.falseteam.vktests.mappers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.falseteam.vktests.entity.User;

@Controller
@RequestMapping("/")
public class RootMapper {

    @GetMapping("/")
    public String getIndex(Model model, Authentication auth) {
//        User user = (User) auth.getPrincipal();
//        model.addAttribute("user", user);
        return "redirect:/task";
    }

    @GetMapping("/login")
    public String getLogin(
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout,
            Model model) {

        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);

        return "login";
    }
}
