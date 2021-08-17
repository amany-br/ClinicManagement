package com.proxym.clinicmanagement.service;

import com.proxym.clinicmanagement.entities.Doctor;
import com.proxym.clinicmanagement.entities.Rdv;
import com.proxym.clinicmanagement.entities.User;

import java.util.List;

public interface RdvService {
    List<Rdv> readAll() ;

    Rdv create(Rdv R );

    Rdv update(Rdv R );


    void delete(Integer id);

    Rdv readById(Integer id);

}
