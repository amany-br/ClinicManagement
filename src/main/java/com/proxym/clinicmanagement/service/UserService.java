package com.proxym.clinicmanagement.service;

import com.proxym.clinicmanagement.entities.User;
import com.proxym.clinicmanagement.repository.UserRepository;

import java.util.List;

public interface UserService {



    List<User> readAll() ;

     User create(User u );

     User update(User u );


    void delete(Integer id);

     User readById(Integer id);


}
