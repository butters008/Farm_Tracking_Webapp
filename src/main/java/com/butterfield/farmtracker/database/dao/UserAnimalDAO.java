package com.butterfield.farmtracker.database.dao;

import com.butterfield.farmtracker.database.entity.Animal;
import com.butterfield.farmtracker.database.entity.User;
import com.butterfield.farmtracker.database.entity.UserAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnimalDAO extends JpaRepository<UserAnimal, Long> {
    List<UserAnimal> findByUserId(@Param("userId") User user);
    UserAnimal findByAnimalId(@Param("animalId") Animal animal);
}
