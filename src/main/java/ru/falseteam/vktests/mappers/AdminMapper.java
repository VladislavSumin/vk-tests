package ru.falseteam.vktests.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.falseteam.vktests.entity.Group;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.GroupRepository;

/**
 * @author Sumin Vladislav
 * @version 0.1
 */
@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminMapper {
    private final GroupRepository groupRepository;

    @Autowired
    public AdminMapper(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/group/management")
    public String getGroupManagement(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        model.addAttribute("groups", groupRepository.findAll());

        return "groupManagement";
    }
}
