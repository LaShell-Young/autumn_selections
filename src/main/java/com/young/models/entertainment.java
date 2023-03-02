package com.young.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Document(collection = "entertainment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class entertainment {
    
    @Id
    private ObjectId id;
    private String type;
    private String title;
    private String poster;
    private String trailerLink;
    private List<String> genre;
    private int rating;

}
