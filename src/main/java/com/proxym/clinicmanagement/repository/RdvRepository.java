package com.proxym.clinicmanagement.repository;

import com.proxym.clinicmanagement.entities.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdvRepository extends JpaRepository<Rdv,Integer> {
}
