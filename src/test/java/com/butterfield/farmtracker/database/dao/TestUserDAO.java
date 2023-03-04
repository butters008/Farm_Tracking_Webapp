package com.butterfield.farmtracker.database.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TestUserDAO {

//    @Autowired
//    private UserDAO userDAO;
//
//    @Test
//    @Order(1)
//    @Rollback(value = false)
//    public void createUserTest(){
//
//        User expected = new User();
//
//        expected.setFirstName("TestBoy");
//        expected.setLastName("BooBoo");
//        expected.setEmail("boog@boog.com");
//        expected.setPassword("password");
//
//        userDAO.save(expected);
//
//        Assertions.assertThat(expected.getId()).isGreaterThan(0);
//    }
//
//
//
//    @Test
//    @Order(2)
//    @Rollback(value = false)
//    public void readUserTest(){
//        List<User> resultList = userDAO.findByFirstNameAndLastName("TestBoy", "BooBoo");
//        for (User user:resultList) {
//            if(user.getFirstName().equals("TestBoy") && user.getLastName().equals("BooBoo")){
//                assert true;
//            }
//        }
//    }
//
//    @Test
//    @Order(3)
//    @Rollback(value = false)
//    public void updateUserTest(){
//        List<User> resultList = userDAO.findByFirstNameAndLastName("TestBoy", "BooBoo");
//        for (User user:resultList) {
//            if(user.getFirstName().equals("TestBoy") && user.getLastName().equals("BooBoo")){
//                user.setEmail("new@email.com");
//                userDAO.save(user);
//                Assertions.assertThat(user.getEmail()).isEqualTo("new@email.com");
//            }
//        }
//    }
//
//    @Test
//    @Order(4)
////    @Rollback(value = false)
//    public void deleteUserTest(){
//
//        User user = userDAO.findByEmail("new@email.com");
//        userDAO.delete(user);
//
////        List<User> resultList = userDAO.findByFirstNameAndLastName("TestBoy", "BooBoo");
////        for (User user:resultList) {
////            log.info("Inside loop");
////
////            if(user.getFirstName().equals("TestBoy") && user.getLastName().equals("BooBoo")){
////                checkAfter =  userDAO.findById(user.getId());
////                userDAO.delete(user);
////            }
////        }
//        Assertions.assertThat(user).isEqualTo(null);
//    }
}
