package com.butterfield.farmtracker.controller;

import com.butterfield.farmtracker.database.dao.*;
import com.butterfield.farmtracker.database.entity.Animal;
import com.butterfield.farmtracker.database.entity.ParentCalf;
import com.butterfield.farmtracker.database.entity.User;
import com.butterfield.farmtracker.database.entity.UserAnimal;
import com.butterfield.farmtracker.formBean.HerdFormBean;
import com.butterfield.farmtracker.security.SecurityService;
import com.butterfield.farmtracker.service.ErrorService;
import com.butterfield.farmtracker.service.HerdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
//import org.apache.commons.io.FileUtils;
import java.time.LocalDate;
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
    private SecurityService securityService = new SecurityService();

    @Autowired
    private HerdService herdService = new HerdService();

    @Autowired
    private ErrorService errorService = new ErrorService();


    /*
    * TODO: Cow Revamp
    *  1) Complete Error Binding for ADDING and EDITING Animal
    *  2) Refactor and extract methods when needed for ADDING and EDITING
    *  3) Clean up code
    *  4) Add comments
    *  5) Add picture for Animal
    *  6) Refactor List
    *    - Have it filter Cows, Bulls, and Calves
    *    - Have it filter by Calf and DOB (year and/or month)
    *  7) Put all methods that are not in controllers, into service class
    * */

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
        List<UserAnimal> userAnimals =  userAnimalDAO.findByUserId(userLoggedIn);
        response.addObject("herd", userAnimals);

        return response;
    }

    //Displays the cow's information on the page when clicked from list
    @RequestMapping(value = "/herd/herdinfo", method = RequestMethod.GET)
    public ModelAndView getCowsById1(@RequestParam("cowId") String cowId, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        ModelAndView response = new ModelAndView();

        //Incase there are two animals with the same first ID belonging to different users
        User user = securityService.getLoggedInUser();
        Animal animal = new Animal();
        List<UserAnimal> animals = userAnimalDAO.findByUserId(user);
        for (UserAnimal userAnimal: animals) {
                if(userAnimal.getAnimalId().getAnimalId1().equals(cowId)){
                    animal = userAnimal.getAnimalId();
                log.info(animal.toString());
                }
        }

        List<ParentCalf> parentCalves = parentCalfDAO.findAllByCowId(animal.getId());
        log.info(parentCalves.toString());

        response.addObject("calves", parentCalves);
        response.addObject("herd", animal);
        response.setViewName("herd/herdinfo");
        return response;

    }



    //Adding new animal to the DB
    @RequestMapping(value = "/herd/submitAnimal", method = RequestMethod.POST)
    public ModelAndView submitAnimal(@Valid HerdFormBean form,
                                     BindingResult bindingResult,
                                     @RequestParam(value="herdPic", required = false) MultipartFile file
    ) throws Exception {
        ModelAndView response = new ModelAndView();

        //Getting the info of the user that logged in
        User userLoggedIn = securityService.getLoggedInUser();

        if(bindingResult.hasErrors()){
            log.info("We are inside the funtion");

            List<String> errorMessages = new ArrayList<>();

            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }

            response.addObject("bindingResult", bindingResult);
            response.addObject("herd", form);
            response.setViewName("herd/herdinfo");
            return response;
        }//End of error handing

        //And extra check to make sure no one is bypassing login
        if (userLoggedIn == null) {
            response.setViewName("redirect:/index");
        }
        else{
            //Creating the animal object
            Animal animal = herdService.addAnimalToDB(form);
            animal.setAnimalImage(form.getAnimalImage());

            if(form.getAnimalImage().equals(null)){
                File targetFile = new File("d:/pics/" + file.getOriginalFilename());
                FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);
            }

            //Saving the animal to the DB
            herdDAO.save(animal);

            //Creating a new userAnimal and submitting this to DB
            UserAnimal userAnimal = new UserAnimal(userLoggedIn,animal);

            userAnimalDAO.save(userAnimal);
            response.setViewName("herd/herdinfo");

        }
        return response;
    }

    @RequestMapping(value = "/herd/updateAnimal/{aID}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updateAnimal( @PathVariable("aID") Integer aID,
            @Valid HerdFormBean form, BindingResult bindingResult,
            @RequestParam("dateOfBirth") String dob, @RequestParam("dateOfDeath") String dod,
            @RequestParam("boughtDate") String bDate, @RequestParam(value="animalImage", required = false) MultipartFile file
    ) throws Exception {
        log.info("Before response");
        ModelAndView response = new ModelAndView();
        log.info("Form bean brought in" + form.toString());

        if(bindingResult.hasErrors()){
            log.info("We are inside the funtion");

            List errorList = errorService.errorList(bindingResult);

            response.addObject("bindingResult", errorList);
            response.addObject("herd", form);
            response.setViewName("herd/herdinfo");
            return response;
        }//End of error handing

        Animal animal = herdDAO.findById(aID);
        animal = animalObjectInfo(form, dob, dod, bDate, animal, herdService);
        try{
            if(animal.getAnimalImage() == null){
                log.debug("FORM BEAN: " + form.getAnimalImage());
                log.debug("FILE INFO: " + file.getOriginalFilename());
                log.debug("File Information: " + file.getOriginalFilename() + " " + file.getSize());

                File targetFile = new File("D:/pics/" + file.getOriginalFilename());
                FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);

            }
            else if(animal.getAnimalImage() != null){
                //File targetFile = ;
            }
        }
        catch(Exception e){
            log.debug("Expection Thrown: ==> " + e);
        }

        animal.setAnimalImage(form.getAnimalImage());
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
        UserAnimal userAnimalBegone = userAnimalDAO.findByAnimalId(animalBegone);
        userAnimalDAO.delete(userAnimalBegone);
        herdDAO.delete(animalBegone);

        response.setViewName("redirect:/index");
        return response;
    }


}
