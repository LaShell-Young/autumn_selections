package com.young.autumn.autumn_selections.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.young.autumn.autumn_selections.models.Entertainment;

import org.bson.types.ObjectId;

@Repository
public interface EntertainmentRepo extends MongoRepository<Entertainment, ObjectId> {
    
}
