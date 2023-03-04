package com.butterfield.farmtracker.database.dao;

import com.butterfield.farmtracker.database.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCalfDAO extends JpaRepository<UserCalf, Long> {
    List<UserCalf> findByUserId(@Param("userId") User user);

//    UserCalf findByCalfId(Integer calfId);
    UserCalf findByCalfId(@Param("calfId") Calf animal);
}
