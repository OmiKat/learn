package com.example.SpringMongoDb.model;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Builder
@Data
@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true) // to make the Username field unique
    @NonNull
    private String userName;
    @NonNull
    private  String password;

    @DBRef
    private List<MyJournal> journalEntries = new ArrayList<>();

    private List<String> Roles;

    public User(ObjectId id, List<MyJournal> journalEntries, @NonNull String password, List<String> roles, @NonNull String userName) {
        this.id = id;
        this.journalEntries = journalEntries;
        this.password = password;
        Roles = roles;
        this.userName = userName;
    }

    public User() {
    }
}
