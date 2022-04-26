package com.butterfield.farmtracker.controller;

import com.butterfield.farmtracker.database.dao.ParentCalfDAO;
import com.butterfield.farmtracker.database.dao.UserAnimalDAO;
import com.butterfield.farmtracker.database.dao.UserDAO;
import com.butterfield.farmtracker.database.entity.Animal;
import com.butterfield.farmtracker.database.entity.ParentCalf;
import com.butterfield.farmtracker.database.entity.User;
import com.butterfield.farmtracker.database.entity.UserAnimal;
import com.butterfield.farmtracker.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class IndexController {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserAnimalDAO userAnimalDAO;

    @Autowired
    private ParentCalfDAO parentCalfDAO;

    @Autowired
    SecurityService securityService = new SecurityService();

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();

        User user = securityService.getLoggedInUser();
        List<UserAnimal> animalList = userAnimalDAO.findByUserId(user);
        List<UserAnimal> bullList = new ArrayList<>();
        List<UserAnimal> cowList = new ArrayList<>();


        for (UserAnimal animal: animalList) {
            if(animal.getAnimalId().getAnimalType().equals("bull")) {
                bullList.add(animal);
            }
            else if(animal.getAnimalId().getAnimalType().equals("cow")){
                cowList.add(animal);
            }
        }

        Integer herdSize = cowList.size();
        Integer calfSize = 0;
        Integer bullSize = bullList.size();

        for (UserAnimal animal: animalList) {
            Animal hybridAnimal = animal.getAnimalId();
            List<ParentCalf> animals = parentCalfDAO.findAllByCowId(hybridAnimal.getId());
            if(animals == null){
                calfSize += 0;
            }
            else{
                calfSize += animals.size();
            }
        }

        if(herdSize == null){
            herdSize = 0;
        }

        response.addObject("user", user);
        response.addObject("herdSize", herdSize);
        response.addObject("calfSize", calfSize);
        response.addObject("bullSize", bullSize);
        return response;
    }
}
