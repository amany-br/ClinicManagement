package com.proxym.clinicmanagement.service;

import com.proxym.clinicmanagement.entities.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> readAll() ;

    Patient create(Patient P );

    Patient update(Patient P );


    void delete(Integer id);

    Patient readById(Integer id);

}
