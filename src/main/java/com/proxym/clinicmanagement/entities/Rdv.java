package com.proxym.clinicmanagement.entities;

import com.proxym.clinicmanagement.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name ="rendez_vous" )
 
public class Rdv {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    private LocalDateTime date_sys = LocalDateTime.now();
    
    private long status = 0;
    
    private long id_doctor;
    
    private String message;
     
    @ManyToOne
    @JoinColumn(nullable = true,name="users_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(nullable = true,name="times_id")
    private Times time;
    
    

	public Times getTime() {
		return time;
	}

	public void setTime(Times time) {
		this.time = time;
	}

 

 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDate_sys() {
		return date_sys;
	}

	public void setDate_sys(LocalDateTime date_sys) {
		this.date_sys = date_sys;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getId_doctor() {
		return id_doctor;
	}

	public void setId_doctor(long id_doctor) {
		this.id_doctor = id_doctor;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

 

	public Rdv() {
		super();
	}
    
    
    
    
 

}
