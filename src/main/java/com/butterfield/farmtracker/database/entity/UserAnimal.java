package com.butterfield.farmtracker.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_animals")
public class UserAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id", updatable = false, insertable = false)
    private Integer userId;

    @Column(name = "animal_id", updatable = false, insertable = false)
    private Integer animalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public UserAnimal(Integer userId, Integer animalId){
        this.userId = userId;
        this.animalId = animalId;
    }

    public UserAnimal(){
        this.userId = null;
        this.animalId = null;
    }
}
