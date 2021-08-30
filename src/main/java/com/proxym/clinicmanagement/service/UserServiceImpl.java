package com.proxym.clinicmanagement.service;


import com.proxym.clinicmanagement.model.User;
import com.proxym.clinicmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> readAll(){
        return userRepository.findAll();
    }
    public User create(User u ){
        return userRepository.save(u);
    }
    public  User update(User u ){
        return userRepository.save(u);
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }
    public User readById(Long id){
        return userRepository.findById(id).get();
    }


}