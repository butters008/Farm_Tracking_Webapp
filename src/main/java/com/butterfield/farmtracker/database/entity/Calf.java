package com.butterfield.farmtracker.database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
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

    @Column(name = "birth_weight")
    private Integer birthWeight;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "date_of_death")
    private Date dateOfDeath;

    @Column(name = "wean_weight")
    private Integer weanWeight;

    @Column(name = "wean_date")
    private Date weanDate;

    @Column(name = "calf_status")
    private String calfStatus;

    @OneToMany(mappedBy = "calf", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<ParentCalves> parentCalves;
}
