package com.proxym.clinicmanagement.service;

import com.proxym.clinicmanagement.repository.UserRepository;
import com.proxym.clinicmanagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
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
    public void delete(Integer id){
        userRepository.deleteById(id);
    }
    public User readById(Integer id){
        return userRepository.findById(id).get();
    }


}
