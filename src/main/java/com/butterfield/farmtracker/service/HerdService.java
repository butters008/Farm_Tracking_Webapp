package com.butterfield.farmtracker.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HerdService {
    //This is to process dates and if a date comes in null, it gets sent back as null
    public LocalDate processDates(String date){
        if(date == null || date == ""){
            return null;
        }
        else{
            LocalDate dateParsed = LocalDate.parse(date);
            return dateParsed;
        }
    }
}
