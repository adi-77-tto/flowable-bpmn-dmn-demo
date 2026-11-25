package com.example.demo_flowable.controller;

import com.example.demo_flowable.service.LeaveService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    private final LeaveService service;

    public LeaveController(LeaveService service) {
        this.service = service;
    }

    @PostMapping("/apply")
    public String apply(@RequestParam String employee, @RequestParam int days) {
        return service.applyLeave(employee,days);
    }

    @GetMapping("/tasks")
    public List<Map<String, String>> tasks() {
        return service.pendingTasks();
    }

    @PostMapping("/tasks/{id}/claim")
    public String claim(@PathVariable String id, @RequestParam String user) {
        service.claim(id, user);
        return "claimed by " + user;
    }

    @PostMapping("/tasks/{id}/complete")
    public String complete(@PathVariable String id, @RequestParam boolean approved) {
        service.complete(id, approved);
        return "completed, approved=" + approved;
    }
}