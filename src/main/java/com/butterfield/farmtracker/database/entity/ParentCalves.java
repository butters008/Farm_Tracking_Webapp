package com.butterfield.farmtracker.database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "parent_calves")
public class ParentCalves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cow_id")
    private Animal cow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bull_id")
    private Animal bull;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calf_id")
    private Calf calf;
}
