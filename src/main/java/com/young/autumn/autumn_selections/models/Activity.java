package com.young.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    @Id
    private ObjectId id;

    private String name;

    private String type;

    private String status;

    private int rating;
    
}
