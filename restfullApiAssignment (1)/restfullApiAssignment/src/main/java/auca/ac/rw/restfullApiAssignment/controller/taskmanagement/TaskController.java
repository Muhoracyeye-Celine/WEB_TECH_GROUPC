package auca.ac.rw.restfullApiAssignment.controller.taskmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.restfullApiAssignment.model.taskmanagement.Task;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1L, "Assignment", "Finish REST API assignment", false, "HIGH", "2026-02-15"));
        tasks.add(new Task(2L, "Study", "Prepare for Web Tech exam", false, "MEDIUM", "2026-02-18"));
        tasks.add(new Task(3L, "Laundry", "Wash clothes", true, "LOW", "2026-02-10"));
    }

   
    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

   
    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return tasks.stream()
                .filter(t -> t.getTaskId().equals(taskId))
                .findFirst()
                .orElse(null);
    }


    @GetMapping("/status")
    public List<Task> getByStatus(@RequestParam boolean completed) {
        return tasks.stream()
                .filter(t -> t.isCompleted() == completed)
                .collect(Collectors.toList());
    }


    @GetMapping("/priority/{priority}")
    public List<Task> getByPriority(@PathVariable String priority) {
        return tasks.stream()
                .filter(t -> t.getPriority().equalsIgnoreCase(priority))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }


    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId().equals(taskId)) {
                tasks.set(i, updatedTask);
                return updatedTask;
            }
        }
        return null;
    }

    @PatchMapping("/{taskId}/complete")
    public String markCompleted(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setCompleted(true);
                return "Task marked as completed";
            }
        }
        return "Task not found";
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        tasks.removeIf(t -> t.getTaskId().equals(taskId));
        return "Task deleted";
    }
}
