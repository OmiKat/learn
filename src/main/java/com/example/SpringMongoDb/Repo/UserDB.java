package com.example.SpringMongoDb.Repo;



import com.example.SpringMongoDb.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDB extends MongoRepository<User, ObjectId>{

    User findByuserName(String username);

    void deleteByUserName(String name);
}
