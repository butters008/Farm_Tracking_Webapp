package com.butterfield.farmtracker.database.dao;

import com.butterfield.farmtracker.database.entity.Animal;
import com.butterfield.farmtracker.database.entity.Calf;
import com.butterfield.farmtracker.database.entity.ParentCalf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;


import java.time.LocalDate;

@DataJpaTest
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TestCalfDAO {

    @Autowired
    private CalfDAO calfDAO;

    @Autowired
    private HerdDAO herdDAO;

    @Autowired
    private ParentCalfDAO parentCalfDAO;


    static Animal testCow = new Animal();

    static Animal testBull = new Animal();

    ParentCalf testParentCalf = new ParentCalf();


//    @BeforeEach
//    public void setup(){
//        testCow.setAnimalId1("cow-testCalf");
//        testCow.setAnimalType("cow");
//        testCow.setHerdStatus("active");
//        testBull.setAnimalId1("bull-testCalf");
//        testBull.setAnimalType("bull");
//        testBull.setHerdStatus("active");
//
//        testParentCalf.setCow(testCow);
//        testParentCalf.setBull(testBull);
//
//    }

    @ParameterizedTest
    @Order(1)
    @Rollback(value = false)
    @CsvSource({"Y123", "1234", "Male", "2022-04-19"})
    public void createCalfTest(String cid1, String cid2, String sex, String dob){
        //expected
        Calf expected = new Calf();
//        expected.setId(1);

        expected.setCalfId1(cid1);
        expected.setCalfId2(cid2);
        expected.setCalfSex(sex);
        expected.setDateOfBirth(LocalDate.parse(dob));

        Calf result = calfDAO.save(expected);
//        testParentCalf.setCalf(expected);
//        parentCalfDAO.save(testParentCalf);

        Assertions.assertEquals(cid1, result.getCalfId1());
    }

}
