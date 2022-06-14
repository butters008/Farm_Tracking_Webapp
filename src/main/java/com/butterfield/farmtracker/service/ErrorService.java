package com.butterfield.farmtracker.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Service
public class ErrorService {
    public List errorList(BindingResult bindingResult){
        List<String> errorMessages = new ArrayList<>();

        for (ObjectError error : bindingResult.getAllErrors()) {
            errorMessages.add(error.getDefaultMessage());
        }
        return errorMessages;
    }
}
