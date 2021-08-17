package com.proxym.clinicmanagement.service;

import com.proxym.clinicmanagement.entities.Patient;
import com.proxym.clinicmanagement.entities.User;
import com.proxym.clinicmanagement.repository.PatientRepository;
import com.proxym.clinicmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository PatientRepository;

    public List<Patient> readAll(){
        return PatientRepository.findAll();
    }
    public Patient create(Patient P ){
        return PatientRepository.save(P);
    }
    public  Patient update(Patient P ){
        return PatientRepository.save(P);
    }
    public void delete(Integer id){
        PatientRepository.deleteById(id);
    }
    public Patient readById(Integer id){
        return PatientRepository.findById(id).get();
    }

}
