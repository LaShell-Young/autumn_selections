package com.young.autumn.autumn_selections.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.bson.Document;
import org.bson.types.ObjectId;


import com.young.autumn.autumn_selections.repositories.EntertainmentRepo;
import com.young.autumn.autumn_selections.models.Entertainment;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.HashSet;
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

    /*************** UPDATE ***************/
    public Optional<Entertainment> updateWatched(ObjectId id, boolean hasWatched){
        Query query1 = new Query(Criteria.where("id").is(id));
        Update update1 = new Update();
        update1.set("hasWatched", hasWatched);
        mongoTemplate.updateFirst(query1, update1, Entertainment.class);

        return entertainmentById(id);
    }
    
    public Optional<Entertainment> updateRating(ObjectId id, int rating){
        Query query1 = new Query(Criteria.where("id").is(id));
        Update update1 = new Update();
        update1.set("rating", rating);
        mongoTemplate.updateFirst(query1, update1, Entertainment.class);

        return entertainmentById(id);
    }

    /*************** GET ***************/
    public List<Entertainment> entertainmentByGenre(List<String> genres){
        List<Entertainment> entertainment = new ArrayList<>();
        
        // query db to find all documents (records) with at least one of the specified genres
        if (genres.size() > 0){
            genres.forEach(genre -> {
                Query query = new Query();
                query.addCriteria(Criteria.where("genres").regex(genre, "i"));
                entertainment.addAll(mongoTemplate.find(query, Entertainment.class, "entertainment"));
            });
        }

        return new ArrayList<>(new HashSet<>(entertainment));
    }

    public Optional<Entertainment> entertainmentById(ObjectId id){
        return repository.findById(id);
    }
    
    public Optional<Entertainment> entertainmentByTitle(String title){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(title, "i"));
        Optional<Entertainment> entertainment = Optional.of(mongoTemplate.findOne(query, Entertainment.class, "entertainment"));

        return entertainment;
    }
    
    public List<Entertainment> entertainmentByType(String type){
        Query query = new Query();
        query.addCriteria(Criteria.where("type").regex(type, "i"));
        List<Entertainment> entertainment = mongoTemplate.find(query, Entertainment.class, "entertainment");

        return entertainment;
    }
    
    public List<Entertainment> entertainmentByRate(int rating){
        Query query = new Query();
        query.addCriteria(Criteria.where("rating").is(rating));
        List<Entertainment> entertainment = mongoTemplate.find(query, Entertainment.class, "entertainment");

        return entertainment;
    }
    
    public List<Entertainment> entertainmentByWatched(boolean hasWatched){
        Query query = new Query();
        query.addCriteria(Criteria.where("hasWatched").is(hasWatched));
        List<Entertainment> entertainment = mongoTemplate.find(query, Entertainment.class, "entertainment");

        return entertainment;
    }
}
