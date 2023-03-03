package com.young.autumn.autumn_selections.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.young.autumn.autumn_selections.models.Activity;
import com.young.autumn.autumn_selections.services.ActivitiesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activities")
public class ActivitiesController {
    @Autowired
    ActivitiesService activitiesService;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Activity>> getAllActivities(){
        return new ResponseEntity<>(activitiesService.allActivities(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Activity>> getActivityById(@PathVariable ObjectId id){
        return new ResponseEntity<>(activitiesService.activityById(id), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Activity>> getActivityByType(@PathVariable String type){
        return new ResponseEntity<>(activitiesService.getByType(type), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<Activity>> getActivityByName(@PathVariable String name){
        return new ResponseEntity<>(activitiesService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/rate/{rating}")
    public ResponseEntity<List<Activity>> getActivityByRating(@PathVariable int rating){
        return new ResponseEntity<>(activitiesService.getByRating(rating), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Activity>> getActivityByStatus(@PathVariable String status){
        return new ResponseEntity<>(activitiesService.getByStatus(status), HttpStatus.OK);
    }
}
