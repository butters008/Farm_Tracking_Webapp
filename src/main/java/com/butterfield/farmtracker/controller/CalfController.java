package com.butterfield.farmtracker.controller;

import com.butterfield.farmtracker.database.dao.CalfDAO;
import com.butterfield.farmtracker.database.dao.HerdDAO;
import com.butterfield.farmtracker.database.dao.ParentCalfDAO;
import com.butterfield.farmtracker.database.entity.*;
import com.butterfield.farmtracker.formBean.CalfFormBean;
import com.butterfield.farmtracker.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
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
    private SecurityService securityService = new SecurityService();

    @RequestMapping(value = "/herd/calfInfo", method = RequestMethod.GET)
    public ModelAndView calfInfo() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("herd/calfInfo");

        //Having to send over a list of cows and bulls to page right away
        //This is for the mother and father id
        List<Animal> cows = herdDAO.findByAnimalType("cow");
        List<Animal> bulls = herdDAO.findByAnimalType("bull");

        response.addObject("cows", cows);
        response.addObject("bulls", bulls);


        return response;
    }

    /*
    * TODO: Figure out what to do with the following
    *  1) The optional data, which includes
    *     - Wean Date
    *     - Wean Weight
    *     - Date of Death
    *     - Bought From
    *     - Bought Date
    *  -X- Figure out how to dynamically call parents
    *  3) Where to make list (Currently thinking of printing on mom page)
    * */
    //Adding a calf the the DB
    @RequestMapping(value = "/herd/addNewCalf", method = RequestMethod.POST)
    public ModelAndView addNewCalf(@Valid CalfFormBean form,
                                   @RequestParam("dateOfBirth") String dob

    ) throws Exception {
        ModelAndView response = new ModelAndView();

        //Making the objects
        Animal bull = herdDAO.findById(Integer.parseInt(form.getMother()));
        Animal cow = herdDAO.findById(Integer.parseInt(form.getFather()));
        Calf calf = new Calf();
        ParentCalf herdConnection = new ParentCalf();

        //Logged form bean to make sure data is coming over
        log.info(form.toString());

        //Logging to make sure that I am getting the Cow and Bull Object
        log.info(cow.toString());
        log.info(bull.toString());

        /*
        * TODO: Thing we need todo to fix calf
        *  -X- Add calf birthweight to DB
        *  -X- Add entry for Breed
        *  -X- Add Sex into DB
        *  -X- Make the rest of required data work
        *  4) Make optional data work
        *  -X- Fix how to retrieve mom
        *  -X- Fix how to retrieve dad
        *  -X- Fix broken function of saving calf
        *  8) Figure out how to make list of calves
        *  9) Figure out how to delete calf
        *  10) Figure out how to update calf
        *  -X- Figure out how to get parent_calves to save
        *  11) Going to have list of calves be printed out in cow page
        * */
        //Filling the calf object
        calf.setCalfId1(form.getCalfId1());
        calf.setCalfId2(form.getCalfId2());
        calf.setDateOfBirth(LocalDate.parse(dob));
        calf.setBirthWeight(form.getBirthWeight());

        //adding cow, bull, and calf to connection
        herdConnection.setCow(cow);
        herdConnection.setBull(bull);
        herdConnection.setCalf(calf);

        //Saving the connection to DB
        parentCalfDAO.save(herdConnection);

        response.setViewName("redirect:/herd/calfInfo");
        return response;
    }
}
