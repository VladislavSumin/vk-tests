package ru.falseteam.vktests.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.falseteam.vktests.PageNotFoundException;
import ru.falseteam.vktests.entity.Task;
import ru.falseteam.vktests.entity.TestQuestion;
import ru.falseteam.vktests.entity.User;
import ru.falseteam.vktests.repository.TaskRepository;
import ru.falseteam.vktests.repository.TestQuestionRepository;
import ru.falseteam.vktests.repository.TestRepository;

import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TestQuestionRepository testQuestionRepository;
    private final ScheduledExecutorService scheduledExecutorService;

    private final Map<Long, TaskExecution> taskExecutions = new ConcurrentHashMap<>();

    @Autowired
    public TaskService(TaskRepository taskRepository, TestQuestionRepository testQuestionRepository) {
        this.taskRepository = taskRepository;
        this.testQuestionRepository = testQuestionRepository;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    @PreDestroy
    public void stop() {
        //TODO подумать над этим
        scheduledExecutorService.shutdownNow();
    }

    public String getView(Model model, User user) {
        if (taskExecutions.get(user.getId()) == null) {
            Iterable<Task> tasks = taskRepository.findAllByGroupAndEndTimeAfter(user.getGroup(), new Date());
            model.addAttribute("tasks", tasks);
            return "taskList";
        }
        return "redirect:/task/execution";
    }

    public String startTask(Long task_id, User user) {
        if (taskExecutions.get(user.getId()) != null) throw new PageNotFoundException();
        Task task = taskRepository.findById(task_id).orElseThrow(PageNotFoundException::new);
        if (task.getEndTime().before(new Date())) throw new PageNotFoundException();
        new TaskExecution(user, task);
        return "redirect:/task/execution";
    }

    public String getQuestion(Model model, User user) {
        TaskExecution taskExecution = taskExecutions.get(user.getId());
        if (taskExecution == null)
            return "redirect:/task";


        model.addAttribute("question", taskExecution.getQuestion());
        return "taskExecution";
    }

    private class TaskExecution implements Runnable {
        List<Long> questions_id = new LinkedList<>();
        long user_id;
        long task_id;

        TaskExecution(User user, Task task) {
            user_id = user.getId();
            task_id = task.getId();

            task.getTest().getQuestions().forEach(testQuestion -> questions_id.add(testQuestion.getId()));
            Collections.shuffle(questions_id, new Random());

            taskExecutions.put(user.getId(), this);
            //TODO удаление через 10 минут
        }

        @Override
        public void run() {

        }

        TestQuestion getQuestion() {
            return testQuestionRepository.findById(questions_id.get(0)).orElseThrow(RuntimeException::new);
        }
    }
}
