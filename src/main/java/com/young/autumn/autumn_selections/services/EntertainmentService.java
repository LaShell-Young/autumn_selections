package com.young.autumn.autumn_selections.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.bson.Document;


import com.young.autumn.autumn_selections.repositories.EntertainmentRepo;
import com.young.autumn.autumn_selections.models.Entertainment;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntertainmentService {

    @Autowired
    private EntertainmentRepo repository;

    @Autowired
    private MongoTemplate mongoTemplate;
    
    public List<Entertainment> allEntertainment(){
        return repository.findAll();
    }

    public List<Entertainment> entertainmentByGenre(List<String> genres){
        List<Entertainment> entertainment = new ArrayList<>();

        // query db to find all documents (records) with at least one of the specified genres
        if (genres.size() > 0){
            Query query = new Query();
            query.addCriteria(Criteria.where("genre").in(genres));
            entertainment = mongoTemplate.find(query, Entertainment.class, "entertainment");
        }

        return entertainment;
    }
}
