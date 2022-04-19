package com.butterfield.farmtracker.database.dao;

import com.butterfield.farmtracker.database.entity.Calf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CalfDAO extends JpaRepository<Calf, Long> {
        Calf findById(@Param("id") Integer id);
}
