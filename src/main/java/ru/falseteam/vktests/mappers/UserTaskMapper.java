package ru.falseteam.vktests.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.services.TaskService;

@Controller
@RequestMapping("/task")
public class UserTaskMapper {
    private final TaskService taskService;

    @Autowired
    public UserTaskMapper(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTask(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);
        return taskService.getView(model, user);
    }

    @PostMapping
    public String postTask(@RequestParam(name = "task_id") Long task_id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return taskService.startTask(task_id, user);
    }

    @GetMapping("/execution")
    public String getTaskExecution(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        return taskService.getQuestion(model, user);
    }
}
