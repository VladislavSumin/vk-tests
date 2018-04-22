package ru.falseteam.vktests.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.falseteam.vktests.PageNotFoundException;
import ru.falseteam.vktests.entity.Test;
import ru.falseteam.vktests.entity.TestQuestion;
import ru.falseteam.vktests.entity.TestQuestionAnswer;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.services.TaskService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    @PostMapping("/execution")
    public String postTestQuestionAdd(
            Authentication auth,
            @RequestParam(name = "question_id") Long questionId,
            @RequestParam(name = "answer_1_id", required = false) Long answer1id,
            @RequestParam(name = "answer_1_is_right", required = false, defaultValue = "0") Boolean answer1IsRight,
            @RequestParam(name = "answer_2_id", required = false) Long answer2id,
            @RequestParam(name = "answer_2_is_right", required = false, defaultValue = "0") Boolean answer2IsRight,
            @RequestParam(name = "answer_3_id", required = false) Long answer3id,
            @RequestParam(name = "answer_3_is_right", required = false, defaultValue = "0") Boolean answer3IsRight,
            @RequestParam(name = "answer_4_id", required = false) Long answer4id,
            @RequestParam(name = "answer_4_is_right", required = false, defaultValue = "0") Boolean answer4IsRight) {
        Map<Long, Boolean> answers = new HashMap<>();
        if (answer1id != null) answers.put(answer1id, answer1IsRight);
        if (answer2id != null) answers.put(answer2id, answer2IsRight);
        if (answer3id != null) answers.put(answer3id, answer3IsRight);
        if (answer4id != null) answers.put(answer4id, answer4IsRight);

        User user = (User) auth.getPrincipal();

        return taskService.questionAnswer(user, questionId, answers);
    }
}
