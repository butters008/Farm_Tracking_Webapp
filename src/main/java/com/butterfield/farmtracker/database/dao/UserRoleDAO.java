package com.butterfield.farmtracker.database.dao;

import com.butterfield.farmtracker.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserId(@Param("user_id") Integer userId);
}
