package ru.falseteam.vktests.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.falseteam.vktests.PageNotFoundException;
import ru.falseteam.vktests.entity.*;
import ru.falseteam.vktests.repository.TaskRepository;
import ru.falseteam.vktests.repository.TaskResultRepository;
import ru.falseteam.vktests.repository.TestQuestionRepository;
import ru.falseteam.vktests.repository.UserRepository;

import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskResultRepository taskResultRepository;
    private final TestQuestionRepository testQuestionRepository;
    private final ScheduledExecutorService scheduledExecutorService;

    private final Map<Long, TaskExecution> taskExecutions = new ConcurrentHashMap<>();

    @Autowired
    public TaskService(
            TaskRepository taskRepository,
            TestQuestionRepository testQuestionRepository,
            UserRepository userRepository,
            TaskResultRepository taskResultRepository) {
        this.taskRepository = taskRepository;
        this.testQuestionRepository = testQuestionRepository;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.userRepository = userRepository;
        this.taskResultRepository = taskResultRepository;
    }

    @PreDestroy
    public void stop() {
        //TODO подумать над этим
        scheduledExecutorService.shutdownNow();
    }

    private List<Task> getPossibleTasks(User user) {
        List<Task> tasks = new LinkedList<>();
        Iterable<Task> tasks_ = taskRepository.findAllByGroupAndEndTimeAfter(user.getGroup(), new Date());
        tasks_.iterator().forEachRemaining(tasks::add);
        taskResultRepository.findAllByUser(user).forEach(taskResult -> tasks.remove(taskResult.getTask()));
        return tasks;
    }

    public String getView(Model model, User user) {
        if (taskExecutions.get(user.getId()) == null) {
            model.addAttribute("tasks", getPossibleTasks(user));
            return "taskList";
        }
        return "redirect:/task/execution";
    }

    public String startTask(Long task_id, User user) {
        if (taskExecutions.get(user.getId()) != null) throw new PageNotFoundException();
        List<Task> possibleTasks = getPossibleTasks(user);
        Task task = possibleTasks
                .stream()
                .filter(task1 -> task1.getId() == task_id)
                .findFirst()
                .orElseThrow(PageNotFoundException::new);
        if (task.getEndTime().before(new Date())) throw new PageNotFoundException();
        new TaskExecution(user, task);
        return "redirect:/task/execution";
    }

    public String getQuestion(Model model, User user) {
        TaskExecution taskExecution = taskExecutions.get(user.getId());
        if (taskExecution == null)
            return "redirect:/task";


        model.addAttribute("question", taskExecution.getQuestion());
        model.addAttribute("countQuestion", taskExecution.countQuestion);
        model.addAttribute("answeredQuestion",
                taskExecution.countQuestion - taskExecution.questions_id.size());
        model.addAttribute("rightAnswers", taskExecution.rightAnswers);
        model.addAttribute("time", taskExecution.endTime);
        return "taskExecution";
    }

    public String questionAnswer(User user, long questionId, Map<Long, Boolean> answers) {
        TaskExecution taskExecution = taskExecutions.get(user.getId());
        if (taskExecution == null) return "redirect:/test";
        Long questionId_ = taskExecution.questions_id.get(0);
        if (!questionId_.equals(questionId)) throw new PageNotFoundException();

        TestQuestion testQuestion = testQuestionRepository.findById(questionId).orElseThrow(PageNotFoundException::new);
        Set<TestQuestionAnswer> answers1 = testQuestion.getAnswers();

        answers.entrySet().removeIf(e -> !e.getValue());
        answers1.removeIf(a -> !a.isRight());

        if (answers.size() == answers1.size()) {
            AtomicBoolean err = new AtomicBoolean(false);
            answers1.forEach(testQuestionAnswer -> {
                if (answers.get(testQuestionAnswer.getId()) == null)
                    err.set(true);
            });
            if (!err.get()) taskExecution.rightAnswers++;
        }
        taskExecution.questions_id.remove(0);
        if (taskExecution.questions_id.size() == 0) {
            taskExecution.finish();
            return "redirect:/task";
        }

        return "redirect:/task/execution";
    }

    private class TaskExecution {
        List<Long> questions_id = new LinkedList<>();
        int countQuestion;
        int rightAnswers = 0;
        long userId;
        long taskId;
        Date endTime;
        boolean finished = false;

        TaskExecution(User user, Task task) {
            userId = user.getId();
            taskId = task.getId();

            task.getTest().getQuestions().forEach(testQuestion -> questions_id.add(testQuestion.getId()));
            Collections.shuffle(questions_id, new Random());
            countQuestion = questions_id.size();

            long time1 = System.currentTimeMillis() + task.getTest().getTimeLimit();
            long time2 = task.getEndTime().getTime();

            endTime = new Date(time1 < time2 ? time1 : time2);

            scheduledExecutorService.schedule(this::finish, endTime.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            taskExecutions.put(user.getId(), this);
        }

        TestQuestion getQuestion() {
            return testQuestionRepository.findById(questions_id.get(0)).orElseThrow(RuntimeException::new);
        }

        synchronized void finish() {
            if (finished) return;
            finished = true;

            User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
            Task task = taskRepository.findById(taskId).orElseThrow(RuntimeException::new);
            taskResultRepository.save(
                    TaskResult.builder()
                            .user(user)
                            .task(task)
                            .countQuestions(countQuestion)
                            .rightAnswers(rightAnswers)
                            .build()
            );
            taskExecutions.remove(user.getId());
        }
    }
}
