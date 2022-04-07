package com.butterfield.farmtracker.database.dao;

import com.butterfield.farmtracker.database.entity.Animal;
import com.butterfield.farmtracker.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HerdDAO extends JpaRepository<Animal, Long> {
    Animal findById(@Param("id") Integer id);
    Animal findByAnimalId1(@Param("animalId1") String animalId1);

    List<Animal> findByAnimalType(@Param("animalType") String animalType);

}
