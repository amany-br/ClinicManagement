package com.proxym.clinicmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="Rendez_vous" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rdv {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    private Date date;
    private String StartHour;
    private RdvStatus status;
}
