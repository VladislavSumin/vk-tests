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
import ru.falseteam.vktests.entity.Group;
import ru.falseteam.vktests.entity.Role;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.GroupRepository;
import ru.falseteam.vktests.repository.UserRepository;

import java.util.Optional;

/**
 * @author Sumin Vladislav
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class GroupMapper {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public GroupMapper(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/group/management")
    public String getGroupManagement(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        model.addAttribute("groups", groupRepository.findAll());

        return "groupManagement";
    }

    @GetMapping("/group/add")
    public String getGroupAdd(Model model, Authentication auth,
                              @RequestParam(name = "error", required = false) String error) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("error", error);

        return "groupAdd";
    }

    @PostMapping("/group/add")
    public String postGroupAdd(RedirectAttributes attributes,
                               @RequestParam(name = "name") String groupName) {
        try {
            //TODO fix log error on use this method
            groupRepository.save(
                    Group.builder()
                            .name(groupName)
                            .role(Role.ROLE_USER)
                            .build()
            );
        } catch (DataIntegrityViolationException e) {
            attributes.addAttribute("error", "Группа с именем " + groupName + " уже существует");
            return "redirect:/admin/group/add";
        }

        return "redirect:/admin/group/management";
    }

    @GetMapping("/group/info/{id}")
    public String getGroupInfo(Model model, Authentication auth,
                              @PathVariable Long id) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        Optional<Group> group = groupRepository.findById(id);
        model.addAttribute("group", group.orElseThrow(PageNotFoundException::new));

        Iterable<User> users = userRepository.findAllByGroup(group.get());
        model.addAttribute("users", users);

        return "groupInfo";
    }
}
