package com.butterfield.farmtracker.database.dao;

import com.butterfield.farmtracker.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findById(@Param("id") Integer id);

    User findByEmail(@Param("email") String email);

    List<User> findByFirstName(@Param("firstName") String firstName);

    List<User> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value = "SELECT u FROM User u WHERE u.password = :password")
    public List<User> getByPassword(@Param("password") String password);
}
