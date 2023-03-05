package com.young.autumn.autumn_selections.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.young.autumn.autumn_selections.repositories.ActivitiesRepo;
import com.young.autumn.autumn_selections.models.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

@Service
public class ActivitiesService {
    @Autowired
    ActivitiesRepo activitiesRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    String[] statuses = {"pending", "in-progess", "done"};
    List<String> statusList = Arrays.asList(statuses);

/*************** UPDATE ***************/
    public Optional<Activity> updateStatus(ObjectId id, String status){
        if (statusList.contains(status.toLowerCase())){
            Query query1 = new Query(Criteria.where("id").is(id));
            Update update1 = new Update();
            update1.set("status", status);
            mongoTemplate.updateFirst(query1, update1, Activity.class);
        }

        return activityById(id);
    }
    
    public Optional<Activity> updateRating(ObjectId id, int rating){
        if (rating <= 5 && rating >= 0){
            Query query1 = new Query(Criteria.where("id").is(id));
            Update update1 = new Update();
            update1.set("rating", rating);
            mongoTemplate.updateFirst(query1, update1, Activity.class);
        }

        return activityById(id);
    }

    /*************** GET ***************/
    public List<Activity> allActivities(){
        return activitiesRepo.findAll();
    }

    public Optional<Activity> activityById(ObjectId id){
        return activitiesRepo.findById(id);
    }

    public List<Activity> getByType(String type){
        Query query = new Query();
        query.addCriteria(Criteria.where("type").regex(type, "i"));
        List<Activity> activities = mongoTemplate.find(query, Activity.class, "activities");

        return activities;
    }

    public List<Activity> getByName(String name){
        List<Activity> activity = new ArrayList<>();

        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(name, "i"));
        activity = mongoTemplate.find(query, Activity.class, "activities");

        return activity;
    }
    
    public List<Activity> getByRating(int rating){
        Query query = new Query();
        query.addCriteria(Criteria.where("rating").is(rating));
        List<Activity> activities = mongoTemplate.find(query, Activity.class, "activities");

        return activities;
    }
    
    public List<Activity> getByStatus(String status){
        Query query = new Query();
        query.addCriteria(Criteria.where("status").regex(status, "i"));
        List<Activity> activities = mongoTemplate.find(query, Activity.class, "activities");

        return activities;
    }
}
