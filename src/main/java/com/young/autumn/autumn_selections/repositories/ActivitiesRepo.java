package com.young.autumn.autumn_selections.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.young.autumn.autumn_selections.models.Activity;

import org.bson.types.ObjectId;

@Repository
public interface ActivitiesRepo extends MongoRepository<Activity, ObjectId> {
    
}
