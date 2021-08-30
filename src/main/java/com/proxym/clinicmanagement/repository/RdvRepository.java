package com.proxym.clinicmanagement.repository;

import com.proxym.clinicmanagement.entities.Rdv;
import com.proxym.clinicmanagement.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdvRepository extends JpaRepository<Rdv,Long> {
	public List<Rdv> findByUser(User user); 
	
	
}
