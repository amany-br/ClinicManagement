package com.proxym.clinicmanagement.controller;

import com.proxym.clinicmanagement.entities.Notifications;
import com.proxym.clinicmanagement.entities.Rdv;
import com.proxym.clinicmanagement.entities.Times;
import com.proxym.clinicmanagement.message.request.MeetingRequestForm;
import com.proxym.clinicmanagement.model.ResponseJson;
import com.proxym.clinicmanagement.model.User;
import com.proxym.clinicmanagement.repository.NotificationsRepository;
import com.proxym.clinicmanagement.repository.RdvRepository;
import com.proxym.clinicmanagement.repository.TimesRepository;
import com.proxym.clinicmanagement.repository.UserRepository;
import com.proxym.clinicmanagement.security.jwt.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/meetings")
public class RdvController {

	@Autowired
	RdvRepository rdvRepository;
	
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    UserRepository userRepository;
	
    @Autowired
    TimesRepository timesRepository;
    
    @Autowired
    NotificationsRepository notificationsRepository;
	
	@PostMapping("/add")
	public ResponseJson add(@RequestBody MeetingRequestForm body,HttpServletRequest req) {
    	Optional<User> current;
        String token = req.getHeader("authorization").replace("Bearer ", "");
        System.out.println(token);
        String username = this.jwtProvider.getUserNameFromJwtToken(token);
        current = this.userRepository.findByUsername(username);
        
        
        User user =  current.get();

        
		Rdv rdv = new Rdv();
		
		
		List<Rdv> rdvs = this.rdvRepository.findAll();
		
		for(Rdv tmp:rdvs) {
			if(tmp.getUser().getId() == user.getId() && tmp.getTime().getId() == body.getTime_id()) {
				return new ResponseJson(false,"rendez-vous déja ajouté");
			} 
		}
		
		rdv.setId_doctor(body.getId_doctor());
		rdv.setMessage(body.getMessage());
		rdv.setUser(user);
		rdv.setTime( timesRepository.findById(body.getTime_id()).get()  );
		
		this.rdvRepository.save(rdv);
		
		Notifications n = new Notifications();
		n.setTitle("Rendez-vous");
		n.setMessage("Vous avaez une nouvelle demande de rendez vous de ".concat(user.getUsername()));
		n.setSeen(false);
		n.setUser(this.userRepository.findById(body.getId_doctor()).get());
		
		this.notificationsRepository.save(n);
		
		
		
		return new ResponseJson(true,"rendez-vous ajouter avec success");
	}
	
	
	@GetMapping("/list")
	public List<Rdv> getMyMeetingRequest(HttpServletRequest req) {
    	Optional<User> current;
        String token = req.getHeader("authorization").replace("Bearer ", "");
        System.out.println(token);
        String username = this.jwtProvider.getUserNameFromJwtToken(token);
        current = this.userRepository.findByUsername(username);
        
        User me = current.get();
        
        return this.rdvRepository.findByUser(me);
		
	}
	
	@GetMapping("/clients/list")
	public List<Rdv> getMyClientsMeetingRequest(HttpServletRequest req) {
    	Optional<User> current;
        String token = req.getHeader("authorization").replace("Bearer ", "");
        System.out.println(token);
        String username = this.jwtProvider.getUserNameFromJwtToken(token);
        current = this.userRepository.findByUsername(username);
        
        User me = current.get();
        
        List<Rdv> all =  this.rdvRepository.findAll();
        
        List<Rdv> res =  new ArrayList<Rdv>();
        
        for(Rdv r:all) {
        	if(r.getId_doctor() == me.getId() ) {
        		res.add(r);
        	}
        }
        
        return res;
        
		
	}
	
	
	@GetMapping("/accept/{id}")
	public Rdv accept( @PathVariable long id) {
    	 
		Rdv r = this.rdvRepository.findById(id).get();
		
		Times t = r.getTime();
		
		t.setStatus(1);
		
		this.timesRepository.save(t);
		
		r.setStatus(1);
		
		
		
		Notifications n = new Notifications();
		n.setTitle("Rendez-vous");
		n.setMessage("votre demande de rendez vous est accepter");
		n.setSeen(false);
		n.setUser(r.getUser());
		
		this.notificationsRepository.save(n);
		
		
		return this.rdvRepository.save(r);
		
	}
	
	
	@GetMapping("/refuse/{id}")
	public Rdv refuse( @PathVariable long id) {
    	 
		Rdv r = this.rdvRepository.findById(id).get();
		r.setStatus(2);
		
		
		Notifications n = new Notifications();
		n.setTitle("Rendez-vous");
		n.setMessage("votre demande de rendez vous est refuser");
		n.setSeen(false);
		n.setUser(r.getUser());
		
		this.notificationsRepository.save(n);
		
		
		return this.rdvRepository.save(r);
		
	}
	
	
 
}
