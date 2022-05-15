package com.butterfield.farmtracker.controller;

import com.butterfield.farmtracker.database.dao.CalfDAO;
import com.butterfield.farmtracker.database.dao.HerdDAO;
import com.butterfield.farmtracker.database.dao.ParentCalfDAO;
import com.butterfield.farmtracker.database.dao.UserAnimalDAO;
import com.butterfield.farmtracker.database.entity.*;
import com.butterfield.farmtracker.formBean.CalfFormBean;
import com.butterfield.farmtracker.security.SecurityService;
import com.butterfield.farmtracker.service.HerdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class CalfController {

    @Autowired
    private CalfDAO calfDAO;

    @Autowired
    private HerdDAO herdDAO;

    @Autowired
    private ParentCalfDAO parentCalfDAO;

    @Autowired
    private UserAnimalDAO userAnimalDAO;

    @Autowired
    private SecurityService securityService = new SecurityService();

    @Autowired
    private HerdService herdService = new HerdService();


    @RequestMapping(value = "/herd/calfInfo", method = RequestMethod.GET)
    public ModelAndView calfInfo() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("herd/calfInfo");

        User user = securityService.getLoggedInUser();
        List<UserAnimal> userAnimals = userAnimalDAO.findByUserId(user);

        //This is the list of cows for empty cow objects
        List<Animal> cows = herdService.getUserHerdCowList(userAnimals);
        List<Animal> bulls = herdService.getUserHerdBullList(userAnimals);

        //Adding the object to page
        response.addObject("cows", cows);
        response.addObject("bulls", bulls);

        return response;
    }


    @RequestMapping(value = "/herd/calfUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView updateInfo(@RequestParam("calfId") String calfId) throws Exception {
        ModelAndView response = new ModelAndView();


        Calf calf = calfDAO.findById(Integer.parseInt(calfId));
        ParentCalf parentCalf = parentCalfDAO.findByCalfId(calf.getId());

        User user = securityService.getLoggedInUser();
        List<UserAnimal> userAnimals = userAnimalDAO.findByUserId(user);

        //This is the list of cows for empty cow objects
        List<Animal> cows = herdService.getUserHerdCowList(userAnimals);
        List<Animal> bulls = herdService.getUserHerdBullList(userAnimals);

        //Adding the object to page
        response.addObject("cows", cows);
        response.addObject("bulls", bulls);
        response.addObject("calf", calf);
        response.addObject("parentCalf", parentCalf);

        response.setViewName("herd/calfInfo");
        return response;
    }

    @RequestMapping(value = "/herd/UpdateCalf", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView updateCalf(@RequestParam("calfId") String calfId,
                                   @Valid CalfFormBean form,
                                   BindingResult bindingResult,
                                   @RequestParam("dateOfBirth") String dob) throws Exception {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            log.info(errorMessages.toString());

            User user = securityService.getLoggedInUser();
            List<UserAnimal> userAnimals = userAnimalDAO.findByUserId(user);

            //This is the list of cows for empty cow objects
            List<Animal> cows = herdService.getUserHerdCowList(userAnimals);
            List<Animal> bulls = herdService.getUserHerdBullList(userAnimals);
            response.addObject("cows", cows);
            response.addObject("bulls", bulls);

            response.addObject("bindingResult", bindingResult);
            response.addObject("calf", form);
            response.setViewName("herd/calfInfo");
            return response;
        }//End of Error handling

        Calf calf = calfDAO.findById(Integer.parseInt(calfId));
        Animal cow = herdDAO.findById(Integer.parseInt(form.getMother()));
        Animal bull = herdDAO.findById(Integer.parseInt(form.getFather()));
        ParentCalf parentCalf = parentCalfDAO.findByCalfId(calf.getId());

        calf.setCalfId1(form.getCalfId1());
        calf.setCalfId2(form.getCalfId2());
        calf.setBreed(form.getBreed());
        calf.setCalfSex(form.getCalfSex());
        calf.setDateOfBirth(LocalDate.parse(dob));
        calf.setBirthWeight(form.getBirthWeight());
        calf.setWeanWeight(form.getWeanWeight());
        calf.setWeanDate(form.getWeanDate());

        calfDAO.save(calf);

        parentCalf.setBull(bull);
        parentCalf.setCow(cow);

        parentCalfDAO.save(parentCalf);

        response.setViewName("redirect:/herd/calfUpdate?calfId=" + calf.getId());
        return response;
    }

    @RequestMapping(value = "/herd/addNewCalf", method = RequestMethod.POST)
    public ModelAndView addNewCalf(@Valid CalfFormBean form,
                                   BindingResult bindingResult,
                                   @RequestParam("dateOfBirth") String dob,
                                   @RequestParam(value="dateOfWean", required = false) String dow) throws Exception {
        ModelAndView response = new ModelAndView();

        //Making the objects
        Animal cow = herdDAO.findById(Integer.parseInt(form.getMother()));
        Animal bull = herdDAO.findById(Integer.parseInt(form.getFather()));
        Calf calf = new Calf();
        ParentCalf herdConnection = new ParentCalf();

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            log.info(errorMessages.toString());

            User user = securityService.getLoggedInUser();
            List<UserAnimal> userAnimals = userAnimalDAO.findByUserId(user);

            //This is the list of cows for empty cow objects
            List<Animal> cows = herdService.getUserHerdCowList(userAnimals);
            List<Animal> bulls = herdService.getUserHerdBullList(userAnimals);
            response.addObject("cows", cows);
            response.addObject("bulls", bulls);

            response.addObject("bindingResult", bindingResult);
            response.addObject("calf", form);
            response.setViewName("herd/calfInfo");
            return response;
        }//End of Error handling

        //Filling the calf object
        calf.setCalfId1(form.getCalfId1());
        calf.setCalfId2(form.getCalfId2());
        calf.setDateOfBirth(LocalDate.parse(dob));
        calf.setBirthWeight(form.getBirthWeight());
        calf.setCalfSex(form.getCalfSex());
        calf.setBreed(form.getBreed());
        calf.setWeanWeight(form.getWeanWeight());
        calf.setWeanDate(form.getWeanDate());

        //Saving to DB
        calfDAO.save(calf);

        //adding cow, bull, and calf to connection
        herdConnection.setCow(cow);
        herdConnection.setBull(bull);
        herdConnection.setCalf(calf);

        //Saving the connection to DB
        parentCalfDAO.save(herdConnection);

        response.setViewName("redirect:/herd/calfInfo");
        return response;
    }

    @RequestMapping(value = "/herd/deleteCalf/{cID}", method = RequestMethod.GET)
    public ModelAndView deleteCalf(@PathVariable("cID") Integer cID) throws Exception {
        ModelAndView response = new ModelAndView();

        Calf calfBegone = calfDAO.findById(cID);
        ParentCalf parentCalfBegone = parentCalfDAO.findByCalfId(calfBegone.getId());
        parentCalfDAO.delete(parentCalfBegone);
        calfDAO.delete(calfBegone);

        response.setViewName("redirect:/index");
        return response;
    }


}
