package com.proxym.clinicmanagement.service;

import com.proxym.clinicmanagement.model.User;

import java.util.List;

public interface UserService {



    List<User> readAll() ;

    User create(User u );

    User update(User u );

    void delete(Long id);

    User readById(Long id);


}