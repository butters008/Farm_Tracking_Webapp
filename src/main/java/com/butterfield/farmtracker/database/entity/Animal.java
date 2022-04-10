package com.butterfield.farmtracker.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "animal_id1")
    private String animalId1;

    @Column(name = "animal_id2")
    private String animalId2;

    @Column(name = "animal_type")
    private String animalType;

    @Column(name = "herd_status")
    private String herdStatus;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "date_of_death")
    private Date dateOfDeath;
}
