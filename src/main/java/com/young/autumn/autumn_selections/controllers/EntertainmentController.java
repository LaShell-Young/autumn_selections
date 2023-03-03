package com.young.autumn.autumn_selections.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.bson.types.ObjectId;

import com.young.autumn.autumn_selections.models.Entertainment;
import com.young.autumn.autumn_selections.services.EntertainmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entertainment")
public class EntertainmentController {

    @Autowired
    private EntertainmentService service;
    
    /***************** GET MAPPINGS  ***********************/
    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Entertainment>> getAllEntertainment(){
        return new ResponseEntity<List<Entertainment>>(service.allEntertainment(), HttpStatus.OK);
    }

    @GetMapping("/genre")
    @ResponseBody
    public ResponseEntity<List<Entertainment>> getByGenre(@RequestParam List<String> genres){
        return new ResponseEntity<List<Entertainment>>(service.entertainmentByGenre(genres), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Entertainment>> getById(@PathVariable ObjectId id){
        return new ResponseEntity<>(service.entertainmentById(id), HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    @ResponseBody
    public ResponseEntity<Optional<Entertainment>> getById(@PathVariable String title){
        return new ResponseEntity<>(service.entertainmentByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    @ResponseBody
    public ResponseEntity<List<Entertainment>> getByType(@PathVariable String type){
        return new ResponseEntity<>(service.entertainmentByType(type), HttpStatus.OK);
    }

    @GetMapping("/rate/{rating}")
    @ResponseBody
    public ResponseEntity<List<Entertainment>> getByType(@PathVariable int rating){
        return new ResponseEntity<>(service.entertainmentByRate(rating), HttpStatus.OK);
    }
    
    @GetMapping("/watched/{hasWatched}")
    @ResponseBody
    public ResponseEntity<List<Entertainment>> getByWatched(@PathVariable boolean hasWatched){
        return new ResponseEntity<>(service.entertainmentByWatched(hasWatched), HttpStatus.OK);
    }

    /***************** POST MAPPINGS  ***********************/
    @PostMapping("/update/watched")
    @ResponseBody
    public ResponseEntity<Optional<Entertainment>> updateWatched(
        @RequestParam(required = true) ObjectId id,
        @RequestParam(required = true) boolean hasWatched
    ){
        return new ResponseEntity<>(service.updateWatched(id, hasWatched), HttpStatus.OK);
    }
    
    @PostMapping("/update/rate")
    @ResponseBody
    public ResponseEntity<Optional<Entertainment>> updateRating(
        @RequestParam(required = true) ObjectId id,
        @RequestParam(required = true) int rating
    ){
        return new ResponseEntity<>(service.updateRating(id, rating), HttpStatus.OK);
    }
}
