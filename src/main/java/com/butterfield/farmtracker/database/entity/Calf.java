package com.butterfield.farmtracker.database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "calves")
public class Calf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "calf_id1")
    private String calfId1;

    @Column(name = "calf_id2")
    private String calfId2;

    @Column(name = "breed")
    private String breed;

    @Column(name = "calf_sex")
    private String calfSex;

    @Column(name = "birth_weight")
    private Integer birthWeight;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_death")
    private LocalDate dateOfDeath;

    @Column(name = "wean_weight")
    private Integer weanWeight;

    @Column(name = "wean_date")
    private LocalDate weanDate;

    @Column(name = "calf_status")
    private String calfStatus;

    @OneToMany(mappedBy = "calf", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<ParentCalf> parentCalves;

}
