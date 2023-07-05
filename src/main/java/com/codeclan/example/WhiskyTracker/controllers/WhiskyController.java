package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getWhiskiesByYear(
            //add "byYear" to the route, and make it not required
            @RequestParam(name = "byYear", required=false) String year,
            @RequestParam(name = "byDistillery", required = false) String distilleryName,
            @RequestParam(name = "byAge", required = false) Integer age)
    {
        //if we have a route with a 'year' query param find the whiskies
        if(year != null) {
            return new ResponseEntity<>(whiskyRepository.findByYear(Integer.parseInt(year)), HttpStatus.OK);
        } else if (distilleryName != null && age != null){
            return new ResponseEntity<>(whiskyRepository.findByDistillery_NameAndAge(distilleryName, age), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
        }
    }



}
