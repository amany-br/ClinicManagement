package com.proxym.clinicmanagement.service;

import com.proxym.clinicmanagement.entities.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> readAll() ;

    Doctor create(Doctor D );

    Doctor update(Doctor D );


    void delete(Integer id);

    Doctor readById(Integer id);

}
