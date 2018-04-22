package ru.falseteam.vktests.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.TaskRepository;

@Controller
@RequestMapping("/admin/task")
@Secured("ROLE_ADMIN")
public class TaskMapper {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskMapper(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/management")
    public String getTaskManagement(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        model.addAttribute("tasks", taskRepository.findAll());

        return "taskManagement";
    }
}
