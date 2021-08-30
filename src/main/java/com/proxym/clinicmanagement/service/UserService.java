package com.proxym.clinicmanagement.security.services;

import com.proxym.clinicmanagement.model.User;

import java.util.List;

public interface UserService {



    List<User> readAll() ;

    User create(User u );

    User update(User u );


    void delete(Integer id);

    User readById(Integer id);


}