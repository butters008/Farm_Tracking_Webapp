package com.butterfield.farmtracker.database.dao;

import com.butterfield.farmtracker.database.entity.ParentCalf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentCalfDAO extends JpaRepository<ParentCalf, Long> {
}
