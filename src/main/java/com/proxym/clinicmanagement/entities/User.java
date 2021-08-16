package com.proxym.clinicmanagement.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name ="utilisateur" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int phone;
    private String email;
    private String password;
}
