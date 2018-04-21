package ru.falseteam.vktests.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.falseteam.vktests.PageNotFoundException;
import ru.falseteam.vktests.entity.Group;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.GroupRepository;
import ru.falseteam.vktests.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
@Secured("ROLE_ADMIN")
public class UserMapper {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserMapper(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/add")
    public String getUserAdd(Model model, Authentication auth,
                             @RequestParam(name = "group_id") Long groupId,
                             @RequestParam(name = "error", required = false) String error) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        Optional<Group> group = groupRepository.findById(groupId);
        model.addAttribute("group", group.orElseThrow(PageNotFoundException::new));

        model.addAttribute("error", error);

        return "userAdd";
    }
}
