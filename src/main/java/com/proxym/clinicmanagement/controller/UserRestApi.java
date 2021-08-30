package com.proxym.clinicmanagement.controller;

import com.proxym.clinicmanagement.model.User;
import com.proxym.clinicmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserRestApi {

    @Autowired
    UserService userService;

    @PostMapping (path="/new-user")
    public User post(@RequestBody User u) {
        return userService.create(u);
    }
    @PutMapping(path="/edit-user")
    public User put(@RequestBody User u) {
        return userService.update(u);
    }


    @GetMapping(path="/users")
    public List<User> get() {
        return userService.readAll();

    }
    @GetMapping(path="/user/{id}")
    public User get(@PathVariable Long id )
    {
        return userService.readById(id);
    }
    @DeleteMapping(path="/remove-user/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);

    }

}