package com.butterfield.farmtracker.controller;

import com.butterfield.farmtracker.database.dao.*;
import com.butterfield.farmtracker.database.entity.*;
import com.butterfield.farmtracker.formBean.HerdFormBean;
import com.butterfield.farmtracker.security.SecurityService;
import com.butterfield.farmtracker.service.ErrorService;
import com.butterfield.farmtracker.service.HerdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
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
    private CalfDAO calfDAO;

    @Autowired
    private ParentCalfDAO parentCalfDAO;

    @Autowired
    private UserCalfDAO userCalfDAO;

    @Autowired
    private SecurityService securityService = new SecurityService();

    @Autowired
    private HerdService herdService = new HerdService();

    @Autowired
    private ErrorService errorService = new ErrorService();

    //The initial get for addAnimal jsp page
    @RequestMapping(value = "/herd/addAnimal", method = RequestMethod.GET)
    public ModelAndView addAnimalInital() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("herd/herdinfo");
        return response;
    }

    //This is for the page where it lists all the animals
    @RequestMapping(value = "/herd/list", method = RequestMethod.GET)
    public ModelAndView listAllCows() throws Exception {
        ModelAndView response = new ModelAndView();

        User userLoggedIn = securityService.getLoggedInUser();
        List<UserAnimal> userAnimals = userAnimalDAO.findByUserId(userLoggedIn);
        //Adding Calves in here for right now
        List<UserCalf> userCalves = userCalfDAO.findByUserId(userLoggedIn);
        response.addObject("calf", userCalves);

        response.addObject("herd", userAnimals);

        return response;
    }

    //DO NOT TOUCH
    //Displays the cow's information on the page when clicked from list
    @RequestMapping(value = "/herd/herdinfo", method = RequestMethod.GET)
    public ModelAndView getCowsById1(@RequestParam("cowId") String cowId, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        ModelAndView response = new ModelAndView();

        //Incase there are two animals with the same first ID belonging to different users
        User user = securityService.getLoggedInUser();
        Animal animal = new Animal();
        List<UserAnimal> animals = userAnimalDAO.findByUserId(user);
        for (UserAnimal userAnimal : animals) {
            if (userAnimal.getAnimalId().getAnimalId1().equals(cowId)) {
                animal = userAnimal.getAnimalId();
                log.info(animal.toString());
            }
        }
        List<ParentCalf> parentCalves = parentCalfDAO.findAllByCowId(animal.getId());

        response.addObject("calves", parentCalves);
        response.addObject("herd", animal);
        response.setViewName("herd/herdinfo");
        return response;

    }

    //This new function will do both add new and edit existing cow - Make code less redundant
    @RequestMapping(value = "/herd/herdSubmit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView submitHerdRequest(@Valid HerdFormBean form,
                                          BindingResult bindingResult,
                                          @RequestParam(value = "file", required = false) MultipartFile file)
            throws Exception {
        //Getting the basic info
        ModelAndView response = new ModelAndView();
        User user = securityService.getLoggedInUser();

        //Error Checking
        if (bindingResult.hasErrors()) {
            log.info("We are inside the Error Function funtion");

            //Creating an empty array for messages and returning the messages
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }

            log.info(errorMessages.toString());

            response.addObject("bindingResult", bindingResult);
            response.addObject("herd", form);
            response.setViewName("herd/herdinfo");
            return response;
        }

        //Checking to see if animal has existing ID and if not; create a new animal object
        Animal animal = herdDAO.findById(form.getId());
        log.debug("Animal ID: " + form.getId());
        if (form.getId() == null) {
            animal = new Animal();
        }

        //Adding information from form to object
        animal = herdService.addAnimalToDB(form);

        //Saving the animal to the DB
        herdDAO.save(animal);

        //If connection already exist, skip
//        List<UserAnimal> userAnimalList = userAnimalDAO.findByUserId(user);
        UserAnimal connection = userAnimalDAO.findByUserIdAndAnimalId(user, animal);
        if (connection == null) {
            connection = new UserAnimal(user, animal);
            userAnimalDAO.save(connection);
        }
        response.setViewName("redirect:/herd/list");
        return response;
    }

    //DELETE function
    @RequestMapping(value = "/herd/delete/{aID}", method = RequestMethod.GET)
    public ModelAndView deleteAnimal(@PathVariable("aID") Integer aID) throws Exception {
        ModelAndView response = new ModelAndView();

        Animal animalBegone = herdDAO.findById(aID);
        UserAnimal userAnimalBegone = userAnimalDAO.findByAnimalId(animalBegone);
        userAnimalDAO.delete(userAnimalBegone);
        herdDAO.delete(animalBegone);

        response.setViewName("redirect:/index");
        return response;
    }


}
