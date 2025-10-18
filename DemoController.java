package com.ibmnj.client.controller;

import com.ibmnj.client.model.User;
import com.ibmnj.client.service.RestClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class DemoController {

    private final RestClientService service;

    public DemoController(RestClientService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return service.getUserById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        service.updateUser(id, user);
        return "User updated successfully!";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return "User deleted successfully!";
    }
}
