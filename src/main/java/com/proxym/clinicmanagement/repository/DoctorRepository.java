package com.proxym.clinicmanagement.repository;

import com.proxym.clinicmanagement.entities.Doctor;
import com.proxym.clinicmanagement.entities.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository  extends JpaRepository<Doctor,Integer> {
}
