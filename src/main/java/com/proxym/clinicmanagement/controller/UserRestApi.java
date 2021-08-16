package com.proxym.clinicmanagement.controller;

import com.proxym.clinicmanagement.entities.User;
import com.proxym.clinicmanagement.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestApi {
    @Autowired
    UserServiceImp UserService;

    @PostMapping(path="/new-user")
    public User post(@RequestBody User u) {
        return UserService.create(u);
    }
    @PutMapping(path="/edit-user")
    public User put(@RequestBody User u) {
        return UserService.update(u);
    }


    @GetMapping(path="/users")
    public List<User> get() {
        return UserService.readAll();

    }
    @GetMapping(path="/user/{id}")
    public User get(@PathVariable Long id )
    {
        return UserService.readById(id);
    }
    @DeleteMapping(path="/remove-user/{id}")
    public void delete(@PathVariable Long id) {
        UserService.delete(id);

    }

}

