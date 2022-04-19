package com.butterfield.farmtracker.database.dao;

import com.butterfield.farmtracker.database.entity.Animal;
import com.butterfield.farmtracker.database.entity.ParentCalf;
import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentCalfDAO extends JpaRepository<ParentCalf, Long> {
    List<ParentCalf> findAllByCowId(@Param("cow_id") Integer cowId);
    ParentCalf findByCalfId(@Param("calf_id") Integer calfId);
}
