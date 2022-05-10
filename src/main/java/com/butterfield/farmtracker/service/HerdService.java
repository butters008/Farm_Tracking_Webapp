package com.butterfield.farmtracker.service;

import com.butterfield.farmtracker.database.dao.HerdDAO;
import com.butterfield.farmtracker.database.dao.UserAnimalDAO;
import com.butterfield.farmtracker.database.entity.Animal;
import com.butterfield.farmtracker.database.entity.UserAnimal;
import com.butterfield.farmtracker.formBean.HerdFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
            cows.add(userAnimal.getAnimalId());
        }}));
        return cows;
    }

    public List<Animal> getUserHerdBullList(List<UserAnimal> userAnimals){
        List<Animal> bulls = new ArrayList<>();
        userAnimals.forEach( (userAnimal -> {
            if(userAnimal.getAnimalId().getAnimalType().equals("bull")){
                bulls.add(userAnimal.getAnimalId());
            }}));
        return bulls;
    }

    public Animal addAnimalToDB(@Valid HerdFormBean form){
        Animal animal = new Animal();
        animal.setAnimalId1(form.getAnimalId1());
        animal.setAnimalId2(form.getAnimalId2());
        animal.setAnimalType(form.getAnimalType());
        animal.setBreed(form.getBreed());
        animal.setHerdStatus(form.getHerdStatus());
        animal.setBoughtFrom(form.getBoughtFrom());
        animal.setDateOfBirth(form.getDateOfBirth());
        animal.setDateOfDeath(form.getDateOfDeath());
        animal.setBoughtDate(form.getBoughtDate());
        return animal;
    }
}
