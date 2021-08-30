package com.proxym.clinicmanagement.controller;

import com.proxym.clinicmanagement.entities.Category;
import com.proxym.clinicmanagement.message.request.EditRequest;
import com.proxym.clinicmanagement.message.request.LoginForm;
import com.proxym.clinicmanagement.message.request.SignUpForm;
import com.proxym.clinicmanagement.message.request.SignupDoctorForm;
import com.proxym.clinicmanagement.message.response.JwtResponse;
import com.proxym.clinicmanagement.model.Role;
import com.proxym.clinicmanagement.model.RoleName;
import com.proxym.clinicmanagement.model.User;
import com.proxym.clinicmanagement.repository.CategoryRepository;
import com.proxym.clinicmanagement.repository.RoleRepository;
import com.proxym.clinicmanagement.repository.UserRepository;
import com.proxym.clinicmanagement.security.jwt.JwtProvider;
import com.proxym.clinicmanagement.security.services.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    private final Path root = Paths.get("uploads");


    @Autowired
    FilesStorageService storageService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CategoryRepository categoryRepository;



    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;
    
    
    @Autowired
    CategoryRepository cr;
    


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/info")
    public User getCurrentUser(HttpServletRequest req) {
        Optional<User> current;
        String token = req.getHeader("authorization").replace("Bearer ", "");
        System.out.println(token);
        String username = this.jwtProvider.getUserNameFromJwtToken(token);
        current = this.userRepository.findByUsername(username);
        return current.get();

    }


    @PostMapping("/doctor/signup")
    public ResponseEntity<?> registerDcotor(@RequestBody SignupDoctorForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

   

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        user.setAddress(signUpRequest.getAddress());
        
        user.setCategory(this.categoryRepository.findById(signUpRequest.getCategory_id()).get());
        userRepository.save(user);

        HashMap res = new HashMap();
        res.put("success", true);

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

 

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        user.setAddress(signUpRequest.getAddress());
        userRepository.save(user);

        HashMap res = new HashMap();
        res.put("success", true);

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody EditRequest EditRequest) {


        // Creating user's account
        User user = this.userRepository.findById(EditRequest.getId()).get();
 

        Set<String> strRoles = EditRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "ROLE_ADMIN":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "ROLE_PM":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        user.setName(EditRequest.getName());
        user.setEmail(EditRequest.getEmail());
        System.out.println(user.getName());

        userRepository.save(user);

        HashMap res = new HashMap();
        res.put("success", true);

        return ResponseEntity.ok().body(res);
    }
    
    
    @GetMapping("/list-categories")
    public List<Category> getAll(){
    	return this.cr.findAll();
    }

 
 
}