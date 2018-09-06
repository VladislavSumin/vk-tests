package ru.falseteam.vktests.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.falseteam.vktests.AccessDennyException;
import ru.falseteam.vktests.PageNotFoundException;
import ru.falseteam.vktests.PasswordGenerator;
import ru.falseteam.vktests.Translit;
import ru.falseteam.vktests.entity.Group;
import ru.falseteam.vktests.entity.Role;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.GroupRepository;
import ru.falseteam.vktests.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
public class UserMapper {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(
            GroupRepository groupRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/add")
    public String getUserAdd(
            Model model, Authentication auth,
            @RequestParam(name = "group_id") Long groupId) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        Optional<Group> group = groupRepository.findById(groupId);
        model.addAttribute("group", group.orElseThrow(PageNotFoundException::new));

        return "userAdd";
    }

    @PostMapping("/generate_password")
    @ResponseBody
    public String postUserGeneratePassword(@RequestParam(name = "group_id") Long groupId,
                                           Authentication auth) {
        StringBuilder sb = new StringBuilder();
        Group group = groupRepository.findById(groupId).orElseThrow(PageNotFoundException::new);
        if (group.getRole() == Role.ROLE_ADMIN) throw new AccessDennyException();
        if (((User) auth.getPrincipal()).getGroup().getRole() == Role.ROLE_TEACHER && group.getRole() == Role.ROLE_TEACHER)
            throw new AccessDennyException();
        userRepository.findAllByGroup(group).forEach(user -> {
            String password = PasswordGenerator.generatePassword(6);
            sb.append(user.getLastName()).append(' ')
                    .append(user.getFirstName()).append(' ')
                    .append(user.getSoname()).append(' ')
                    .append(user.getUsername()).append(' ')
                    .append(password).append("<br>");
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        });
        return sb.toString();
    }

    @PostMapping("/add")
    public String postUserAdd(
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "soname") String soname,
            @RequestParam(name = "group_id") Long groupId,
            Authentication auth) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(PageNotFoundException::new);

        if (group.getRole() == Role.ROLE_ADMIN) throw new AccessDennyException();
        if (((User) auth.getPrincipal()).getGroup().getRole() == Role.ROLE_TEACHER && group.getRole() == Role.ROLE_TEACHER)
            throw new AccessDennyException();

        String username = Translit.cyr2lat(lastName);
        User user = User.builder()
                .username(username)
                .lastName(lastName)
                .firstName(firstName)
                .soname(soname)
                .group(group)
                .password("password_not_set")
                .build();
        int addNumber = 1;
        while (true) {
            try {
                //TODO fix log error on use this method
                userRepository.save(user);
                break;
            } catch (DataIntegrityViolationException e) {
                user.setUsername(username + addNumber++);
            }
        }

        return "redirect:/admin/group/info/" + groupId;
    }
}
