package com.proxym.clinicmanagement.repository;

import com.proxym.clinicmanagement.entities.Admin;
import com.proxym.clinicmanagement.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
