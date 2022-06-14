package com.butterfield.farmtracker.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

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

    @NotEmpty(message = "Cannot be blank")
    @Column(name = "animal_id1")
    private String animalId1;

    @Column(name = "animal_id2")
    private String animalId2;

    @Column(name = "animal_type")
    private String animalType;

    @NotEmpty(message = "Cannot be blank")
    @Column(name = "breed")
    private String breed;

    @NotEmpty(message = "Cannot be blank")
    @Column(name = "herd_status")
    private String herdStatus;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_death")
    private LocalDate dateOfDeath;

    @Column(name = "bought_from")
    private String boughtFrom;

    @Column(name = "bought_date")
    private LocalDate boughtDate;

    @Column(name = "animal_image")
    private String animalImage;

    @OneToMany(mappedBy = "cow", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<ParentCalf> parentCow;

    @OneToMany(mappedBy = "bull", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<ParentCalf> parentBull;

    @OneToMany(mappedBy = "animalId", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<UserAnimal> userAnimals;
}
