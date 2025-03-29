package com.example.SpringMongoDb.Repo;


import com.example.SpringMongoDb.model.MyJournal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalDB extends MongoRepository<MyJournal, ObjectId>{

}
