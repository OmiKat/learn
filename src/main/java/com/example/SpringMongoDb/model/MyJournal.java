package com.example.SpringMongoDb.model;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;



@Getter
@Setter
@Data
@NoArgsConstructor
@Document(collection = "journalEntries")
public class MyJournal {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;

}
