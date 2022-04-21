package com.butterfield.farmtracker.controller;

import com.butterfield.farmtracker.database.dao.UserDAO;
import com.butterfield.farmtracker.database.dao.UserRoleDAO;
import com.butterfield.farmtracker.database.entity.User;
import com.butterfield.farmtracker.database.entity.UserRole;
import com.butterfield.farmtracker.formBean.RegisterFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
//@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleDAO userRoleDAO;

    //Need to have a simple get for the page to load
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        return response;
    }


    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/login");


        return response;
    }

    @RequestMapping(value = "/user/registerSubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerSubmit(@Valid RegisterFormBean bean, BindingResult bindingResult) throws Exception {

        ModelAndView response = new ModelAndView();

        //Annotation Error Handling
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            log.info(errorMessages.toString());
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", bean);
            response.setViewName("user/register");
            return response;
        }//End of Error handling

        log.info(bean.toString());

        User user = new User();
        user.setEmail(bean.getEmail());
        user.setFirstName(bean.getFirstName());
        user.setLastName(bean.getLastName());
        String password = passwordEncoder.encode(bean.getPassword());
        user.setPassword(password);


        //Saving user to DB
        userDAO.save(user);

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setUserRole("USER");
        userRoleDAO.save(userRole);

        response.setViewName("redirect:/index");
        return response;
    }

    /*
    * TODO: This method will be revamped for profile page on updating
    *  user information. Some info will probably be left as unchangeable.
    * */
//    @GetMapping(value = "/user/edit/{userId}")
//    public ModelAndView editUser(@PathVariable("userId") Integer userId) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("user/register");
//
//        User user = userDAO.findById(userId);
//
//        RegisterFormBean form = new RegisterFormBean();
//
//        form.setId(user.getId());
//        form.setEmail(user.getEmail());
//        form.setFirstName(user.getFirstName());
//        form.setLastName(user.getLastName());
//        form.setPassword(user.getPassword());
//        form.setPasswordConfirm(user.getPassword());
//
//        response.addObject("form", form);
//
//        return response;
//    }

}
