package com.tasknotes.tasknotes.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasknotes.tasknotes.user.repository.User;
import com.tasknotes.tasknotes.user.service.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserServices userServices;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(userServices.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public void addUser(@RequestBody User user){
        userServices.addUser(user);
    }

}
