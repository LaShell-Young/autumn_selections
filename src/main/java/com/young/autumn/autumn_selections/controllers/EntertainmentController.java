package com.young.autumn.autumn_selections.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.autumn.autumn_selections.models.Entertainment;
import com.young.autumn.autumn_selections.services.EntertainmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entertainment")
public class EntertainmentController {

    @Autowired
    private EntertainmentService service;
    
    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Entertainment>> getAllEntertainment(){
        return new ResponseEntity<List<Entertainment>>(service.allEntertainment(), HttpStatus.OK);
    }

    @GetMapping("/genre")
    @ResponseBody
    public ResponseEntity<List<Entertainment>> getByGenre(
        @RequestParam(required = true, name = "genres") List<String> genres 
    ){
        return new ResponseEntity<List<Entertainment>>(service.entertainmentByGenre(genres), HttpStatus.OK);
    }

    // get by id

    // get by title

    // get by type

    // get by rating
}
