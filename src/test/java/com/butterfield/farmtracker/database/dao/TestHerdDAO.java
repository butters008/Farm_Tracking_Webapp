package com.butterfield.farmtracker.database.dao;

import com.butterfield.farmtracker.database.entity.Animal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TestHerdDAO {

    @Autowired
    private HerdDAO herdDAO;

//    @BeforeAll
//    public static void setUp(){
//    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void createAnimalTest(){
        //expected
        Animal expected = new Animal();
//        expected.setId(1);

        expected.setAnimalId1("Y123");
        expected.setAnimalId2("1234");
        expected.setAnimalType("cow");
        expected.setBreed("Red Angus");
        expected.setHerdStatus("Active");

        herdDAO.save(expected);

        Assertions.assertThat(expected.getId()).isGreaterThan(0);
    }



    @Test
    @Order(2)
    @Rollback(value = false)
    public void readAnimalTest(){
        Animal expected = herdDAO.findByAnimalId1("Y123");
        Assertions.assertThat(expected.getAnimalId1()).isEqualTo("Y123");
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void updateAnimalTest(){
        Animal updateExpected = herdDAO.findByAnimalId1("Y123");
        updateExpected.setBreed("Black Angus");
        herdDAO.save(updateExpected);
        Assertions.assertThat(updateExpected.getBreed()).isEqualTo("Black Angus");
    }

    @Test
    @Order(4)
//    @Rollback(value = false)
    public void deleteAnimalTest(){
//        Animal deleteExpected = herdDAO.findById(69);
        Animal deleteExpected = herdDAO.findByAnimalId1("Y123");
        herdDAO.delete(deleteExpected);
        Assertions.assertThat(herdDAO.findById(deleteExpected.getId())).isEqualTo(null);
    }

}
