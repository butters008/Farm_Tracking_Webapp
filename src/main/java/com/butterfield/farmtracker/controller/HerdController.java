package com.butterfield.farmtracker.controller;

import com.butterfield.farmtracker.database.dao.HerdDAO;
import com.butterfield.farmtracker.database.dao.UserAnimalDAO;
import com.butterfield.farmtracker.database.dao.UserDAO;
import com.butterfield.farmtracker.database.entity.Animal;
import com.butterfield.farmtracker.database.entity.User;
import com.butterfield.farmtracker.database.entity.UserAnimal;
import com.butterfield.farmtracker.formBean.HerdFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class HerdController {

    @Autowired
    private HerdDAO herdDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserAnimalDAO userAnimalDAO;

    //For right now I am going to hard code in list of cows
    @RequestMapping(value = "/herd/list", method = RequestMethod.GET)
    public ModelAndView listAllCows() throws Exception {
        ModelAndView response = new ModelAndView();

        List<Animal> animals = herdDAO.findByAnimalType("cow");
        response.addObject("cows", animals);

        return response;
    }

    //For right now I am going to hard code in list of cows
    @RequestMapping(value = "/herd/herdinfo", method = RequestMethod.GET)
    public ModelAndView getCowsById1(@RequestParam("cowId") String cowId) throws Exception {
        ModelAndView response = new ModelAndView();

        Animal animal = herdDAO.findByAnimalId1(cowId);
        log.info(animal.toString());

        response.setViewName("herd/herdinfo");
        response.addObject("cow", animal);
        return response;

    }

    @RequestMapping(value = "/herd/addAnimal", method = RequestMethod.GET)
    public ModelAndView addAnimalInital() throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("herd/addAnimal");
        return response;
    }

    @RequestMapping(value = "/herd/animal", method = RequestMethod.GET)
    public ModelAndView setupAnimalPage(@Valid HerdFormBean form) throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("herd/addAnimal");
        return response;
    }


    @RequestMapping(value = "/herd/submitAnimal", method = RequestMethod.GET)
    public ModelAndView submitAnimal(@Valid HerdFormBean form) throws Exception {
        ModelAndView response = new ModelAndView();


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userLoggedIn = userDAO.findByEmail(currentPrincipalName);


        if (userLoggedIn == null) {
            response.setViewName("redirect:/index");
        }
        else{
            //Creating the animal object
            // TODO: Finish inputing all animal data
            Animal animal = new Animal();
            animal.setAnimalId1(form.getAnimalId1());
            animal.setAnimalId2(form.getAnimalId2());
            animal.setAnimalType(form.getAnimalType());
            animal.setHerdStatus(form.getHerdStatus());

            //Saving the animal to the DB
            herdDAO.save(animal);

            //Creating a new userAnimal and submitting this to DB
//            UserAnimal userAnimal = new UserAnimal();
//            userAnimal.setUserId(userLoggedIn.getId());
//            userAnimal.setAnimalId(animal.getId());
//
//            log.info("User Information: " + userLoggedIn);
//            log.info("Trying to get user's ID: " + userLoggedIn.getId());
//            log.info("Animal Information" + animal);
//            log.info("Trying to get user's ID: " + animal.getId());
//            log.info("Grabbing animal Id from userAnimal: " + userAnimal.getAnimalId());
//            log.info("Grabbing user Id from userAnimal: " + userAnimal.getUserId());

            UserAnimal userAnimal = new UserAnimal(userLoggedIn.getId(), animal.getId());
            log.info("Grabbing userAnimal: " + userAnimal);
            userAnimal.setAnimal(animal);
            userAnimal.setUser(userLoggedIn);

            userAnimalDAO.save(userAnimal);

            //Once completed, than return to view
            response.setViewName("herd/addAnimal");

        }

//        animal.setDateOfBirth(form.getDateOfBirth());
//        animal.setDateOfDeath(form.getDateOfDeath());

        return response;

    }


}
