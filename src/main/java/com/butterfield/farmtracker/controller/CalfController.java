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
import org.springframework.web.bind.annotation.*;
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
        //This is the list of cows for empty cow objects
        List<Animal> cows = herdDAO.findByAnimalType("cow");
        List<Animal> bulls = herdDAO.findByAnimalType("bull");

        //Adding the object to page
        response.addObject("cows", cows);
        response.addObject("bulls", bulls);

        return response;
    }

    /*
    * Reason I can do this is because of the ./calfInfo?calfId=
    * The ? is telling the @Request Param to start looking and I am telling
    * Springboot that the param is "calfId" which than looks at the value
    * which is right after the =
    *
    * So in short (param => ?calfId) (={calfId} <= value of param)
    *
    * (PSA) You don't see the {}number @RequestMapping,
    *  but you see it in the tag from jsp page
     * */
    @RequestMapping(value = "/herd/calfUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView updateInfo(@RequestParam("calfId") String calfId) throws Exception {
        ModelAndView response = new ModelAndView();


        Calf calf = calfDAO.findById(Integer.parseInt(calfId));
        ParentCalf parentCalf = parentCalfDAO.findByCalfId(calf.getId());

        //This is the list of cows for empty cow objects
        List<Animal> cows = herdDAO.findByAnimalType("cow");
        List<Animal> bulls = herdDAO.findByAnimalType("bull");

        //Adding the object to page
        response.addObject("cows", cows);
        response.addObject("bulls", bulls);
        response.addObject("calf", calf);
        response.addObject("parentCalf", parentCalf);

        response.setViewName("herd/calfInfo");
        return response;
    }

    //Stupid I know but for right now...it works
    // TODO: Combine the two methods into one
    @RequestMapping(value = "/herd/UpdateCalf", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView updateCalf(@RequestParam("calfId") String calfId,
                                   @Valid CalfFormBean form,
                                   @RequestParam("dateOfBirth") String dob) throws Exception {
        ModelAndView response = new ModelAndView();


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

        calfDAO.save(calf);

        parentCalf.setBull(bull);
        parentCalf.setCow(cow);

        parentCalfDAO.save(parentCalf);

        response.setViewName("redirect:/herd/calfUpdate?calfId="+calf.getId());
        return response;
    }

    @RequestMapping(value = "/herd/addNewCalf", method = RequestMethod.POST)
    public ModelAndView addNewCalf(@Valid CalfFormBean form,
                                   @RequestParam("dateOfBirth") String dob

    ) throws Exception {
        ModelAndView response = new ModelAndView();

        //Making the objects
        Animal cow = herdDAO.findById(Integer.parseInt(form.getMother()));
        Animal bull = herdDAO.findById(Integer.parseInt(form.getFather()));
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
        *  -Abandoned- Make optional data work
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
        calf.setCalfSex(form.getCalfSex());
        calf.setBreed(form.getBreed());

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

}
