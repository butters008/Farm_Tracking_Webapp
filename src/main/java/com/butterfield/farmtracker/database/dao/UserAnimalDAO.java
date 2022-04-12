package com.butterfield.farmtracker.database.dao;

import com.butterfield.farmtracker.database.entity.UserAnimal;
import com.butterfield.farmtracker.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnimalDAO extends JpaRepository<UserAnimal, Long> {
}
