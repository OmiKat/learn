package com.example.SpringMongoDb.Controller;


import com.example.SpringMongoDb.Service.UserService;
import com.example.SpringMongoDb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.SaveUser(user);
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        //security context holder contains all the security config we are using it to get username...
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();


        User userinDB = userService.findByUserName(userName);
        userinDB.setUserName(user.getUserName());
        userinDB.setPassword(user.getPassword());
        userService.SaveUser(userinDB);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
