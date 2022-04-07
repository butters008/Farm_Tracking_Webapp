package com.butterfield.farmtracker.controller;

import com.butterfield.farmtracker.database.dao.UserDAO;
import com.butterfield.farmtracker.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class IndexController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();

        List<User> users = userDAO.findByFirstName("Keith");

        for(User user : users){
            log.info(user.toString());
        }
        log.info(users.toString());
        response.setViewName("index");
        response.addObject("userList", users);

        return response;

    }
    /*
    * Keeping for reference for right now, however, this will be changed in the
    * future when I need the index to-do more than just saying hello or index
    * */
//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public ModelAndView index() throws Exception {
//        ModelAndView response = new ModelAndView();
//
//        List<User> users = userDAO.findByFirstName("Keith");
//
//        for(User user : users){
//            log.info(user.toString());
//        }
//        log.info(users.toString());
//        response.setViewName("index");
//        response.addObject("userList", users);
//
//        return response;
//
//    }
}
