package com.butterfield.farmtracker.controller;

import com.butterfield.farmtracker.database.dao.HerdDAO;
import com.butterfield.farmtracker.database.dao.UserAnimalDAO;
import com.butterfield.farmtracker.database.dao.UserDAO;
import com.butterfield.farmtracker.database.entity.Animal;
import com.butterfield.farmtracker.database.entity.User;
import com.butterfield.farmtracker.database.entity.UserAnimal;
import com.butterfield.farmtracker.formBean.HerdFormBean;
import com.butterfield.farmtracker.security.SecurityService;
import com.butterfield.farmtracker.service.HerdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private SecurityService securityService = new SecurityService();

    @Autowired
    private HerdService herdService = new HerdService();


    @RequestMapping(value = "/herd/list", method = RequestMethod.GET)
    public ModelAndView listAllCows() throws Exception {
        ModelAndView response = new ModelAndView();

        User userLoggedIn = securityService.getLoggedInUser();

        List<UserAnimal> userAnimals =  userAnimalDAO.findByUserId(userLoggedIn);
        response.addObject("herd", userAnimals);

        return response;
    }

    //For right now I am going to hard code in list of cows
    @RequestMapping(value = "/herd/herdinfo", method = RequestMethod.GET)
    public ModelAndView getCowsById1(@RequestParam("cowId") String cowId) throws Exception {
        ModelAndView response = new ModelAndView();

        Animal animal = herdDAO.findByAnimalId1(cowId);

        response.setViewName("herd/herdinfo");
        response.addObject("herd", animal);
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


    @RequestMapping(value = "/herd/submitAnimal", method = RequestMethod.POST)
    public ModelAndView submitAnimal(
            @Valid HerdFormBean form,
            @RequestParam("dateOfBirth") String dob,
            @RequestParam("dateOfDeath") String dod,
            @RequestParam("boughtDate") String bDate)
            throws Exception {
        ModelAndView response = new ModelAndView();

        //Getting the info of the user that logged in
        User userLoggedIn = securityService.getLoggedInUser();

        if (userLoggedIn == null) {
            response.setViewName("redirect:/index");
        }
        else{
            //Creating the animal object
            Animal animal = new Animal();

            animal =animalObjectInfo(form, dob, dod, bDate, animal, herdService);

            //Saving the animal to the DB
            herdDAO.save(animal);

            //Creating a new userAnimal and submitting this to DB
            UserAnimal userAnimal = new UserAnimal();

            userAnimal.setUserId(userLoggedIn);
            userAnimal.setAnimalId(animal);

//            UserAnimal userAnimal = new UserAnimal(userLoggedIn.getId(), animal.getId());

            log.info("User Information: " + userLoggedIn);

            log.info("Animal Information" + animal);
//            log.info("Grabbing animal Id from userAnimal: " + userAnimal.getAnimalId());
//            log.info("Grabbing user Id from userAnimal: " + userAnimal.getUserId());

            log.info("Grabbing userAnimal: " + userAnimal);
            log.info(userAnimal.toString());


            userAnimalDAO.save(userAnimal);


            log.info(userAnimal.toString());
            //Once completed, than return to view
            response.setViewName("herd/addAnimal");

        }
        return response;
    }

    @RequestMapping(value = "/herd/updateAnimal/{aID}", method = RequestMethod.POST)
    public ModelAndView updateAnimal( @PathVariable("aID") Integer aID,
            @Valid HerdFormBean form, @RequestParam("breed") String breed,
            @RequestParam("dateOfBirth") String dob, @RequestParam("dateOfDeath") String dod,
            @RequestParam("boughtDate") String bDate) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("Form coming in" + form.toString());
        log.info("path variable: " +aID);

        Animal animal = herdDAO.findById(aID);
        log.info("Before Update: " + animal.toString());

        animal = animalObjectInfo(form, dob, dod, bDate, animal, herdService);
        log.info("After Update: " + animal.toString());
        herdDAO.save(animal);

        response.setViewName("redirect:/herd/list");
        return response;
    }

    private static Animal animalObjectInfo(@Valid HerdFormBean form, @RequestParam("dateOfBirth") String dob, @RequestParam("dateOfDeath") String dod, @RequestParam("boughtDate") String bDate, Animal animal, HerdService herdService) {
        animal.setAnimalId1(form.getAnimalId1());
        animal.setAnimalId2(form.getAnimalId2());
        animal.setAnimalType(form.getAnimalType());
        animal.setBreed(form.getBreed());
        animal.setHerdStatus(form.getHerdStatus());
        animal.setBoughtFrom(form.getBoughtFrom());
        animal.setDateOfBirth(herdService.processDates(dob));
        animal.setDateOfDeath(herdService.processDates(dod));
        animal.setBoughtDate(herdService.processDates(bDate));
        return animal;
    }


    @RequestMapping(value = "/herd/delete/{aID}", method = RequestMethod.GET)
    public ModelAndView deleteAnimal(@PathVariable("aID") Integer aID) throws Exception {
        ModelAndView response = new ModelAndView();

        Animal animalBegone = herdDAO.findById(aID);
//        log.info(animalBegone.toString());
        UserAnimal userAnimalBegone = userAnimalDAO.findByAnimalId(animalBegone);
//        log.info(userAnimalBegone.toString());
        userAnimalDAO.delete(userAnimalBegone);
//        herdDAO.delete(animalBegone);

        response.setViewName("redirect:/index");
        return response;
    }


}
