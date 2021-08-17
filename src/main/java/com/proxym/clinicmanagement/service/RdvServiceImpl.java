package com.proxym.clinicmanagement.service;

import com.proxym.clinicmanagement.entities.Rdv;
import com.proxym.clinicmanagement.entities.User;
import com.proxym.clinicmanagement.repository.RdvRepository;
import com.proxym.clinicmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RdvServiceImpl implements RdvService {
    @Autowired
    RdvRepository RdvRepository;

    public List<Rdv> readAll() {
        return RdvRepository.findAll();
    }

    public Rdv create(Rdv R) {
        return RdvRepository.save(R);
    }

    public Rdv update(Rdv R) {
        return RdvRepository.save(R);
    }

    public void delete(Integer id) {
        RdvRepository.deleteById(id);
    }

    public Rdv readById(Integer id) {
        return RdvRepository.findById(id).get();
    }

}
