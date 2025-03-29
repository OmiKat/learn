package com.example.SpringMongoDb.Controller;

import com.example.SpringMongoDb.Service.UserService;
import com.example.SpringMongoDb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService service;


    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = service.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-newAdmin")
    public void creatNewAdmin(@RequestBody User user){
        service.SaveNewUser(user);
    }

}
