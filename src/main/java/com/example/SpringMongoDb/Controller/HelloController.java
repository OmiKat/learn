package com.example.SpringMongoDb.Controller;

import com.example.SpringMongoDb.Service.UserService;
import com.example.SpringMongoDb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class HelloController {

    @Autowired
    UserService userService;


    @GetMapping
    public String hello(){
        return "this is working";
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.SaveUser(user);
    }

}
