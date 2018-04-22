package ru.falseteam.vktests.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.falseteam.vktests.PageNotFoundException;
import ru.falseteam.vktests.entity.*;
import ru.falseteam.vktests.repository.GroupRepository;
import ru.falseteam.vktests.repository.TaskRepository;
import ru.falseteam.vktests.repository.TestRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/admin/task")
@Secured("ROLE_ADMIN")
public class TaskMapper {
    private final TaskRepository taskRepository;

    private final GroupRepository groupRepository;
    private final TestRepository testRepository;


    @Autowired
    public TaskMapper(TaskRepository taskRepository, GroupRepository groupRepository, TestRepository testRepository) {
        this.taskRepository = taskRepository;
        this.groupRepository = groupRepository;
        this.testRepository = testRepository;
    }

    @GetMapping("/management")
    public String getTaskManagement(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        model.addAttribute("tasks", taskRepository.findAll());

        return "taskManagement";
    }

    @GetMapping("/add")
    public String getTaskAdd(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("tests", testRepository.findAll());

        return "taskAdd";
    }

    @PostMapping("/add")
    public String postTaskAdd(
            @RequestParam(name = "group_id") Long groupId,
            @RequestParam(name = "test_id") Long testId,
            @RequestParam(name = "end_time") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endTime) {
        Group group = groupRepository.findById(groupId).orElseThrow(PageNotFoundException::new);
        Test test = testRepository.findById(testId).orElseThrow(PageNotFoundException::new);
        taskRepository.save(
                Task.builder()
                        .group(group)
                        .test(test)
                        .endTime(endTime)
                        .build()
        );
        return "redirect:/admin/task/management";
    }
}
