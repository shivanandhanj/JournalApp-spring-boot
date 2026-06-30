package com.shiva.Journal.model;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
@Document
@Data
@NoArgsConstructor
public class Journalmodel {
    @Id
    private ObjectId id;
     private String title;
    private String content;
    private Date createdAt;
   

}
