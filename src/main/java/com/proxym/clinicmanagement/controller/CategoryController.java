package com.proxym.clinicmanagement.controller;

import com.proxym.clinicmanagement.entities.Category;
import com.proxym.clinicmanagement.model.User;
import com.proxym.clinicmanagement.repository.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
@Controller
public class CategoryController {


    @Autowired
    CategoryRepository cr;

    @PostMapping(path="/add")
    public Category post(@RequestBody Category c) {
        return cr.save(c);
    }
    
    @GetMapping("/list")
    public List<Category> getAll(){
    	return this.cr.findAll();
    }

}
