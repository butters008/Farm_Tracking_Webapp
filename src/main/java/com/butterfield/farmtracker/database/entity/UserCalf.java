package com.butterfield.farmtracker.database.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_calves")
public class UserCalf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calf_id")
    private Calf calfId;

    public UserCalf(User userId, Calf calfId){
        this.userId = userId;
        this.calfId = calfId;
    }

    public UserCalf() {

    }
}
