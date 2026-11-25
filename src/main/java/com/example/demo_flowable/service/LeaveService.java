package com.example.demo_flowable.service;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeaveService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public LeaveService(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    public String applyLeave(String employee, int days) {
        Map<String, Object> vars = new HashMap<>();
        vars.put("employee", employee);
        vars.put("days", days);
        return runtimeService.startProcessInstanceByKey("leaveRequest", vars).getId();
    }

    public List<Map<String, String>> pendingTasks() {
        return taskService.createTaskQuery()
                .taskCandidateGroupIn(List.of("managers", "hrs"))
                .list()
                .stream()
                .map(t -> Map.of("id", t.getId(), "name", t.getName()))
                .toList();
    }

    public void claim(String taskId, String user) {
        taskService.claim(taskId, user);
    }

    public void complete(String taskId, boolean approved) {
        taskService.complete(taskId, Map.of("approved", approved));
    }
}