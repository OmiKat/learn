package com.example.SpringMongoDb.Service;

import com.example.SpringMongoDb.Repo.UserDB;
import com.example.SpringMongoDb.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserDB repo;

    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAll() {
       return repo.findAll();
    }


//    public void createUser(User user) {
//        repo.save(user);
//    }


    // this is only used first time other wise it will again set password
    public void SaveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        repo.save(user);
    }
    public void SaveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER" , "ADMIN"));
        repo.save(user);
    }


    public Optional<User> getEntryBYid(ObjectId id) {
        return repo.findById(id);
    }

    public void deletebyId(ObjectId id) {
        repo.deleteById(id);
    }

    public User updateUser(User entry){
        return repo.save(entry);
    }

    public User findByUserName(String userName) {
        return repo.findByuserName(userName);
    }


    public void deleteUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        repo.deleteByUserName(authentication.getName());
    }
}
