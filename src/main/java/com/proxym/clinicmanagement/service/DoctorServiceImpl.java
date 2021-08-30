package com.proxym.clinicmanagement.service;

import com.proxym.clinicmanagement.entities.Doctor;
import com.proxym.clinicmanagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorRepository DoctorRepository;

    public List<Doctor> readAll(){
        return DoctorRepository.findAll();
    }
    public Doctor create(Doctor D ){
        return DoctorRepository.save(D);
    }
    public  Doctor update(Doctor D ){
        return DoctorRepository.save(D);
    }
    public void delete(Integer id){
        DoctorRepository.deleteById(id);
    }
    public Doctor readById(Integer id){
        return DoctorRepository.findById(id).get();
    }

}
