package com.butterfield.farmtracker.service;

import com.butterfield.farmtracker.database.dao.HerdDAO;
import com.butterfield.farmtracker.database.dao.UserAnimalDAO;
import com.butterfield.farmtracker.database.entity.Animal;
import com.butterfield.farmtracker.database.entity.UserAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HerdService {

    @Autowired
    private HerdDAO herdDAO;

    @Autowired
    private UserAnimalDAO userAnimalDAO;

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

    public String debugString(UserAnimal userAnimal) {
        return "UserAnimal{" +
                "userId=" + userAnimal.getUserId() +
                ", animalId=" + userAnimal.getAnimalId() +
                '}';
    }

    public List<Animal> getUserHerdCowList(List<UserAnimal> userAnimals){
        List<Animal> cows = new ArrayList<>();
        userAnimals.forEach( (userAnimal -> {
            if(userAnimal.getAnimalId().getAnimalType().equals("cow")){
//            Animal cow = userAnimal.findById(userAnimal.getAnimalId().getId());
            cows.add(userAnimal.getAnimalId());
        }}));
        return cows;
    }

    public List<Animal> getUserHerdBullList(List<UserAnimal> userAnimals){
        List<Animal> bulls = new ArrayList<>();
        userAnimals.forEach( (userAnimal -> {
            if(userAnimal.getAnimalId().getAnimalType().equals("bull")){
//            Animal cow = userAnimal.findById(userAnimal.getAnimalId().getId());
                bulls.add(userAnimal.getAnimalId());
            }}));
        return bulls;
    }
}
