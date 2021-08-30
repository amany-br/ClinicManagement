package com.proxym.clinicmanagement.controller;

import com.proxym.clinicmanagement.model.User;
import com.proxym.clinicmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/filter")
public class FilterController {

    @Autowired
    UserRepository ur;


    @GetMapping("/search")
    public List<User> serachByCategory(@RequestParam( required  = false ) String c){
        if (c != null){
            long id_category = Long.parseLong(c);

            List<User> tmp = new ArrayList<User>();

            for (User t:ur.findAll() ) {
                if(t.getCategory() != null){
                    if (t.getCategory().getId() == id_category){
                        tmp.add(t);
                    }
                }

            }

            return tmp;
        }

        return ur.findAll();

    }
    @GetMapping(path="/doctor/{id}")
    public User get(@PathVariable Long id )
    {
        return ur.findById(id).get();
    }
}
